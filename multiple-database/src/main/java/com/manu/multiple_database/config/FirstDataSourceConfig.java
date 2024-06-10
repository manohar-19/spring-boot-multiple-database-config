package com.manu.multiple_database.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "firstEntityManagerFactory",
        transactionManagerRef = "firstTransactionManager",
        basePackages = {"com.manu.multiple_database.all_database.first_database.first_repository"}
)
public class FirstDataSourceConfig {
    @Value("${first.datasource.url}")
    private String dbUrl;

    @Value("${first.datasource.username}")
    private String dbUsername;

    @Value("${first.datasource.password}")
    private String dbPassword;

    @Primary
    @Bean(name = "firstDatasource")
    public DataSource firstDatasource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(dbUrl);
        ds.setUsername(dbUsername);
        ds.setPassword(dbPassword);

        return ds;
    }

    @Primary
    @Bean(name = "firstEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("firstDatasource") DataSource dataSource) {
        Map<String,String> prop = new HashMap<>();
        prop.put("hibernate.hbm2ddl.auto","update");

        return builder.dataSource(dataSource)
                .packages("com.manu.multiple_database.all_database.first_database.first_model")
                .persistenceUnit("first")
                .properties(prop)
                .build();
    }
    @Primary
    @Bean(name = "firstTransactionManager")
    public PlatformTransactionManager firstTransactionManager(
            @Qualifier("firstEntityManagerFactory") EntityManagerFactory firstEntityManagerFactory
    ) {
        return new JpaTransactionManager(firstEntityManagerFactory);
    }
}
