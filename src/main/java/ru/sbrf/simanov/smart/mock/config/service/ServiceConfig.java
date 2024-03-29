package ru.sbrf.simanov.smart.mock.config.service;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbrf.simanov.smart.mock.repository.SmartMockRepository;
import ru.sbrf.simanov.smart.mock.service.SmartMockCacheService;
import ru.sbrf.simanov.smart.mock.service.SmartMockService;
import ru.sbrf.simanov.smart.mock.service.SmartMockServiceImpl;
import ru.sbrf.simanov.smart.mock.service.processor.SmartMockProcessor;
import ru.sbrf.simanov.smart.mock.service.processor.TimeoutSmartMockProcessor;

import java.util.Collections;
import java.util.List;

/**
 * created by simanov-an on 2019-08-29
 */
@Configuration
public class ServiceConfig
{
    private final CacheManager cacheManager;
    private final SmartMockRepository smartMockRepository;

    public ServiceConfig(CacheManager cacheManager, SmartMockRepository smartMockRepository)
    {
        this.cacheManager = cacheManager;
        this.smartMockRepository = smartMockRepository;
    }

    @Bean
    public SmartMockService smartMockService()
    {
        return new SmartMockCacheService(cacheManager,
                new SmartMockServiceImpl(smartMockRepository, smartMockProcessors()));
    }

    private List<SmartMockProcessor> smartMockProcessors()
    {
        return Collections.unmodifiableList(Collections.singletonList(new TimeoutSmartMockProcessor()));
    }
}
