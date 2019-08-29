package ru.sbrf.simanov.smart.mock.config.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@Configuration
@Profile("dev-db-embedded")
public class DevEmbeddedDataConfigHolder implements DataSourceHolder
{
    @Bean
    public DataSource dataSource()
    {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(false)
                .setName("smart-mocks-h2-db")
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("config/schema.sql", "config/data.sql")
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .build();
    }
}