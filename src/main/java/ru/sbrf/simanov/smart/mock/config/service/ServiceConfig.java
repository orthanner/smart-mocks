package ru.sbrf.simanov.smart.mock.config.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbrf.simanov.smart.mock.repository.SmartMockRepository;
import ru.sbrf.simanov.smart.mock.service.SmartMockCacheService;
import ru.sbrf.simanov.smart.mock.service.SmartMockService;
import ru.sbrf.simanov.smart.mock.service.SmartMockServiceImpl;

/**
 * created by simanov-an on 2019-08-29
 */
@Configuration
public class ServiceConfig
{
    private final SmartMockRepository smartMockRepository;

    public ServiceConfig(SmartMockRepository smartMockRepository)
    {
        this.smartMockRepository = smartMockRepository;
    }

    @Bean
    public SmartMockService smartMockService()
    {
        return new SmartMockCacheService(new SmartMockServiceImpl(smartMockRepository));
    }
}
