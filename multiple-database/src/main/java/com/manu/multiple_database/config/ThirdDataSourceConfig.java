package com.manu.multiple_database.config;

import com.manu.multiple_database.api.ApiService;
import com.manu.multiple_database.api.CredsModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class ThirdDataSourceConfig {

    @Value("${second.datasource.url}")
    private String dbUrl;

    private final ApiService apiService ;

    public ThirdDataSourceConfig(ApiService apiService) {
        this.apiService = apiService;
    }

    @Bean(name = "thirdDataSource")
    public DataSource dataSource1() {
        CredsModel creds = apiService.getCredentials("third").block();
        return DataSourceBuilder.create()
                .username(creds.getUsername())
                .password(creds.getPassword())
                .url(dbUrl)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("thirdDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("thirdDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

//    public WebMvcConfigurer corsConfig(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:8080");
//            }
//        };
//    }
}
