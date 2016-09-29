package ru.microbyte.java;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfig {

    @Bean
//    @Scope(value="prototype")
    RandomNumber getRandomNumberBean() {
        return new RandomNumber();
    }

    @Bean
    RandomNumberHolder getRandomNumberHolder() {
        return new RandomNumberHolder() {

            @Override
            RandomNumber getRandomNumber() {
                return getRandomNumberBean();
            }
        };
    }
}
