package ru.microbyte.simple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean(name = "wow")
    Quotes getWowQuotes() {
        WowQuotes wowQuotes = new WowQuotes();
        wowQuotes.setMessage("wow");
        return wowQuotes;
    }
}
