package io.github.piotrkozuch.config;

import io.github.piotrkozuch.integration.esa.ESAClient;
import io.github.piotrkozuch.integration.esa.ESAHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Configuration
public class IntegrationConfig {

    @Bean
    public ESAClient esaClient() {
        return new ESAHttpClient(new RestTemplate(), URI.create("https://public-esa.ose.gov.pl"));
    }

}
