package ru.pogodinegor.vacationcalc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /**
     * Создает экземпляр RestTemplate для использования в приложении.
     * RestTemplate предоставляет методы для взаимодействия с HTTP серверами.
     *
     * @return Новый экземпляр RestTemplate.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
