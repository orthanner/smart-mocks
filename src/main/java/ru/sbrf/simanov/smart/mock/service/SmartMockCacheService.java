package ru.sbrf.simanov.smart.mock.service;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import java.util.List;
import java.util.Optional;

import static ru.sbrf.simanov.smart.mock.service.SmartMockCacheService.CACHE_FIND_BY_ID_KEY;
import static ru.sbrf.simanov.smart.mock.service.SmartMockCacheService.CACHE_FIND_BY_REQUEST_NAME_KEY;

/**
 * created by simanov-an
 * Реализация сервиса заглушек с кешом
 */
@CacheConfig(cacheNames = {CACHE_FIND_BY_ID_KEY, CACHE_FIND_BY_REQUEST_NAME_KEY})
public class SmartMockCacheService implements SmartMockService
{
    static final String CACHE_FIND_BY_ID_KEY = "smart-mocks-findById";

    private static final String FIND_BY_REQUEST_NAME_PREF = "findByRequestName";
    static final String CACHE_FIND_BY_REQUEST_NAME_KEY = "smart-mocks-" + FIND_BY_REQUEST_NAME_PREF;


    private final CacheManager cacheManager;
    private final SmartMockService delegate;

    public SmartMockCacheService(CacheManager cacheManager, SmartMockService delegate)
    {
        this.cacheManager = cacheManager;
        this.delegate = delegate;
    }

    @Override
    @Cacheable(cacheNames = CACHE_FIND_BY_REQUEST_NAME_KEY, key = "#requestName", sync = true)
    public List<SmartMock> findByRequestName(String requestName)
    {
        return delegate.findByRequestName(requestName);
    }

    @Override
    @Cacheable(cacheNames = CACHE_FIND_BY_ID_KEY, key = "#id.toString()", sync = true)
    public Optional<SmartMock> findById(Long id)
    {
        return delegate.findById(id);
    }

    @Override
    @CacheEvict(cacheNames = CACHE_FIND_BY_ID_KEY, key = "#id.toString()")
    public void deleteById(Long id)
    {
        Optional<SmartMock> smartMock = delegate.findById(id);
        if (smartMock.isPresent())
        {
            delegate.deleteById(id);
            evictFromCacheFindByRequestName(smartMock.get().getRequestName());
        }
        else
        {
            throw new IllegalArgumentException(String.format("Smart mock with id=%s not found!", id));
        }
    }

    @Override
    @CachePut(cacheNames = CACHE_FIND_BY_ID_KEY, key = "#smartMock.id.toString()")
    public SmartMock save(SmartMock smartMock)
    {
        SmartMock result = delegate.save(smartMock);
        evictFromCacheFindByRequestName(result.getRequestName());
        return result;
    }

    @Override
    @CachePut(cacheNames = CACHE_FIND_BY_ID_KEY, key = "#id.toString()")
    public SmartMock update(Long id, SmartMock smartMock)
    {
        SmartMock result = delegate.update(id, smartMock);
        evictFromCacheFindByRequestName(result.getRequestName());
        return result;
    }

    @Override
    @CachePut(cacheNames = CACHE_FIND_BY_ID_KEY, key = "#smartMock.id.toString()")
    public SmartMock create(SmartMock smartMock)
    {
        SmartMock result = delegate.create(smartMock);
        evictFromCacheFindByRequestName(result.getRequestName());
        return result;
    }

    @Override
    public Optional<SmartMock> findByRequestNameAndBody(String requestName, String requestBody)
    {
        return delegate.findByRequestNameAndBody(requestName, requestBody);
    }

    private void evictFromCacheFindByRequestName(String cacheKey)
    {
        cacheManager.getCache(SmartMockCacheService.CACHE_FIND_BY_REQUEST_NAME_KEY).evict(cacheKey);
    }
}
