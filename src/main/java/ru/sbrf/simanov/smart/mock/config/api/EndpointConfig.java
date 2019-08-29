package ru.sbrf.simanov.smart.mock.config.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.sbrf.simanov.smart.mock.api.processor.SmartMockRqProcessor;
import ru.sbrf.simanov.smart.mock.api.processor.SmartMockRqProcessorImpl;
import ru.sbrf.simanov.smart.mock.config.service.ServiceConfig;

/**
 * created by sbrf-simanov-an on 20.11.2018 - 15:35
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "ru.sbrf.simanov.smart.mock.api.endpoint" })
public class EndpointConfig
{
    private final ServiceConfig serviceConfig;

    public EndpointConfig(ServiceConfig serviceConfig)
    {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public SmartMockRqProcessor smartMockRqProcessor()
    {
        return new SmartMockRqProcessorImpl(serviceConfig.smartMockService());
    }
}
