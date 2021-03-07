package ru.otus.spring.config;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.mongodb.MongoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.changelog.InitMongoDBDataChangeLog;

@Configuration
@EnableMongoRepositories("ru.otus.spring.repository")
@RequiredArgsConstructor
public class MongockConfig {

    @Bean
    public Mongock mongock(MongoProps mongoProps, MongoClient mongoClient) {
        return new SpringMongockBuilder(mongoClient, mongoProps.getDatabase(),
                InitMongoDBDataChangeLog.class.getPackageName())
                .build();
    }
}
