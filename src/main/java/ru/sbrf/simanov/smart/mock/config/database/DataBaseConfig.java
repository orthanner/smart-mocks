package ru.sbrf.simanov.smart.mock.config.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * created by sbrf-simanov-an on 21.11.2018 - 18:21
 */
@Configuration
@Import({HibernateConfig.class})
public class DataBaseConfig
{ }
