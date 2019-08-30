package ru.sbrf.simanov.smart.mock.service;

import org.springframework.cache.annotation.*;
import ru.sbrf.simanov.smart.mock.entity.SmartMock;

import java.util.List;
import java.util.Optional;

/**
 * created by simanov-an
 * Реализация сервиса заглушек с кешом
 */
@CacheConfig(cacheNames = {"smart-mocks"})
public class SmartMockCacheService implements SmartMockService
{
    private final SmartMockService delegate;

    public SmartMockCacheService(SmartMockService delegate)
    {
        this.delegate = delegate;
    }

    @Override
    @Cacheable(key = "'findByRequestName' + #requestName", sync = true)
    public Optional<List<SmartMock>> findByRequestName(String requestName)
    {
        return delegate.findByRequestName(requestName);
    }

    @Override
    @Cacheable(key = "'findById' + #id", sync = true)
    public Optional<SmartMock> findById(Long id)
    {
        return delegate.findById(id);
    }

    @Override
    @Caching(evict = {
            // TODO Подумать как очищать кеши по названию @CacheEvict(key = "'findByRequestName' + #smartMock.requestName"),
            @CacheEvict(key = "'findById' + #id")})
    public void deleteById(Long id)
    {
        delegate.deleteById(id);
    }

    @Override
    @Caching(put = {
            @CachePut(key = "'findById' + #smartMock.id")})
    public SmartMock save(SmartMock smartMock)
    {
        return delegate.save(smartMock);
    }

    @Override
    @Caching(put = {
        @CachePut(key = "'findById' + #smartMock.id")})
    public SmartMock update(Long id, SmartMock smartMock)
    {
        return delegate.update(id, smartMock);
    }

    @Override
    @Caching(put = {
            @CachePut(key = "'findById' + #smartMock.id")})
    public SmartMock create(SmartMock smartMock)
    {
        return delegate.create(smartMock);
    }

    @Override
    public Optional<SmartMock> findByRequestNameAndBody(String requestName, String requestBody)
    {
        return delegate.findByRequestNameAndBody(requestName, requestBody);
    }
}
