package com.manu.multiple_database.config;

import com.manu.multiple_database.api.ApiService;
import com.manu.multiple_database.api.CredsModel;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondEntityManagerFactory",
        transactionManagerRef = "secondTransactionManager",
        basePackages = {"com.manu.multiple_database.all_database.second_database.second_repository"}
)
public class SecondDataSourceConfig {


    private final ApiService apiService ;

    public SecondDataSourceConfig(ApiService apiService) {
        this.apiService = apiService;
    }


    @Value("${second.datasource.url}")
    private String dbUrl;

//    @Value("${second.datasource.username}")
//    private String dbUsername;
//
//    @Value("${second.datasource.password}")
//    private String dbPassword;


    @Bean(name = "secondDatasource")
    public DataSource secondDatasource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(dbUrl);

        CredsModel creds = apiService.getCredentials("second").block();

        System.out.println(creds.toString());
        ds.setUsername(creds.getUsername());
        ds.setPassword(creds.getPassword());

        return ds;
    }

    @Bean(name = "secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("secondDatasource") DataSource dataSource) {

        return builder.dataSource(dataSource)
                .packages("com.manu.multiple_database.all_database.second_database.second_model")
                .persistenceUnit("second")
                .build();
    }

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager(
            @Qualifier("secondEntityManagerFactory") EntityManagerFactory secondEntityManagerFactory
    ) {
        return new JpaTransactionManager(secondEntityManagerFactory);
    }
}
