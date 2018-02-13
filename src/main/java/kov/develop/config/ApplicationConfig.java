package kov.develop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

/**
 * Application configuration - call another config files, DB and ORM init.
 */

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:util.properties")
@EnableJpaRepositories("kov.develop.mvc.repository")
@ComponentScan(basePackages = "kov.develop.config")
public class ApplicationConfig {

    /**
     * @PropertySource annotation does not automatically
     * register a PropertySourcesPlaceholderConfigurer with Spring.
     * So we need to initialize this bean.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${jdbc.driverClass}")
    private String driverClass;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUserName;
    @Value("${jdbc.password}")
    private String jdbcPassword;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;
    @Value("${hibernate.format_sql}")
    private String hibernateFormatSql;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;
    @Value("{hibernate.cache.region.factory_class}")
    private String hibernateCacheRegionFactoryClass;
    @Value("{hibernate.cache.use_second_level_cache}")
    private String hibernateCacheUseSecondLevelCache;
    @Value("{hibernate.cache.use_query_cache}")
    private String hibernateCacheUseQueryCache;
    @Value("{net.sf.ehcache.configurationResourceName}")
    private String netSfEhcacheConfigurationResourceName;

    @Value("classpath:dbschema.sql")
    private Resource dbschemaSqlScript;
    @Value("classpath:test-data.sql")
    private Resource testDataSqlScript;

    @Bean(name = "dataSource")
    public DriverManagerDataSource getDriverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUserName);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(getDriverManagerDataSource());
        initializer.setDatabasePopulator(getDatabasePopulator());
        return initializer;
    }

    private DatabasePopulator getDatabasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(dbschemaSqlScript);
        populator.addScript(testDataSqlScript);
        return populator;
    }

    @Bean(name = "restTemplate")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan("kov.develop.mvc.model");
        em.setDataSource(getDriverManagerDataSource());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        ((HibernateJpaVendorAdapter)vendorAdapter).setGenerateDdl(true);
        ((HibernateJpaVendorAdapter)vendorAdapter).setShowSql(true);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", hibernateDialect);
        jpaProperties.put("hibernate.show_sql", hibernateShowSql);
        jpaProperties.put("hibernate.format_sql",hibernateFormatSql);
        jpaProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        jpaProperties.put("hibernate.cache.region.factory_class", hibernateCacheRegionFactoryClass);
        jpaProperties.put("hibernate.cache.use_second_level_cache", hibernateCacheUseSecondLevelCache);
        jpaProperties.put("hibernate.cache.use_query_cache", hibernateCacheUseQueryCache);
        jpaProperties.put("net.sf.ehcache.configurationResourceName", netSfEhcacheConfigurationResourceName);

        em.setJpaProperties(jpaProperties);
        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getLocalContainerEntityManagerFactoryBean().getObject());
        return transactionManager;
    }

}
