package ru.sbrf.simanov.smart.mock.config.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.sbrf.simanov.smart.mock.config.property.PropertyConfig;


@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class HibernateConfig
{
    private static final String ENTITY_PACKAGE = "ru.sbrf.simanov.smart.mock.entity";

    private final PropertyConfig propertyConfig;
    private final DataSourceHolder dataSourceHolder;

    public HibernateConfig(PropertyConfig propertyConfig, DataSourceHolder dataSourceHolder)
    {
        this.propertyConfig = propertyConfig;
        this.dataSourceHolder = dataSourceHolder;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourceHolder.dataSource());
        em.setPackagesToScan(ENTITY_PACKAGE);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(propertyConfig.hibernateProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager()
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
