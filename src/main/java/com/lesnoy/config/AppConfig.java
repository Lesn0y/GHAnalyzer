package com.lesnoy.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${github.token}")
    private String GITHUB_TOKEN;
    @Value("${github.base_url}")
    private String GITHUB_BASE_URL;

    private static final Log log = LogFactory.getLog(AppConfig.class);

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(GITHUB_BASE_URL)
                .defaultHeader("Authorization", "token " + GITHUB_TOKEN)
                .build();
    }

    @Bean
    public Properties properties() {
        try {
            return PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
        } catch (IOException e) {
            log.info("Loading application.properties failed");
            throw new RuntimeException(e);
        }
    }

}
