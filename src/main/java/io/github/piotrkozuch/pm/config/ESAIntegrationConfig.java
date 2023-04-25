package io.github.piotrkozuch.pm.config;

import io.github.piotrkozuch.pm.integration.esa.ESAClient;
import io.github.piotrkozuch.pm.integration.esa.ESAHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Configuration
public class ESAIntegrationConfig {

    private final String url;

    public ESAIntegrationConfig(@Value("${esa-integration.url}") String url) {
        this.url = url;
    }

    @Bean
    public ESAClient esaClient() {
        return new ESAHttpClient(new RestTemplate(), URI.create(url));
    }

}
