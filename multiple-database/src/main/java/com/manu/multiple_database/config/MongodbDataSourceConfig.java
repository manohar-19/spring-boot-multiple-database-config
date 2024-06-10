package com.manu.multiple_database.config;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration

@EnableMongoRepositories(
        basePackages = "com.manu.multiple_database.all_database.mongodb",
        mongoTemplateRef = "secondaryMongoTemplate"
)
public class MongodbDataSourceConfig  {
    @Value("${mongodb.url}")
    private String dbUrl;

    @Bean
    public MongoClient secondaryMongoClient() {
        return MongoClients.create(dbUrl);
    }

    @Bean
    public MongoDatabaseFactory secondaryMongoDatabaseFactory() {
        // Replace "testdb" with your database name
        return new SimpleMongoClientDatabaseFactory(secondaryMongoClient(), "testdb");
    }

    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() {
        return new MongoTemplate(secondaryMongoDatabaseFactory());
    }
}
