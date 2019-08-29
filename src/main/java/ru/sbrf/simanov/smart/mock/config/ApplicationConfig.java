package ru.sbrf.simanov.smart.mock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbrf.simanov.smart.mock.config.api.EndpointConfig;
import ru.sbrf.simanov.smart.mock.config.api.SwaggerConfig;
import ru.sbrf.simanov.smart.mock.config.cache.CacheConfig;
import ru.sbrf.simanov.smart.mock.config.database.DataBaseConfig;
import ru.sbrf.simanov.smart.mock.config.property.PropertyConfig;
import ru.sbrf.simanov.smart.mock.config.service.ServiceConfig;


/**
 * created by sbrf-simanov-an on 20.11.2018 - 15:46
 */
@Configuration
@Import({PropertyConfig.class,
        DataBaseConfig.class,
        CacheConfig.class,
        ServiceConfig.class,
        EndpointConfig.class,
        SwaggerConfig.class})
public class ApplicationConfig
{ }