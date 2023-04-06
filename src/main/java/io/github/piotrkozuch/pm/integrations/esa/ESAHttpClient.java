package io.github.piotrkozuch.pm.integrations.esa;

import io.github.piotrkozuch.pm.integrations.esa.response.SmogDataPage;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static io.github.piotrkozuch.pm.utils.Checks.checkRequired;

public class ESAHttpClient implements ESAClient {

    private final RestTemplate restTemplate;
    private final URI baseUri;

    public ESAHttpClient(RestTemplate restTemplate, URI baseUri) {
        this.restTemplate = checkRequired("restTemplate", restTemplate);
        this.baseUri = checkRequired("baseUri", baseUri);
    }

    @Override
    public SmogDataPage getSmogDataPage(int pageNumber) {
        final var uri = baseUri.resolve("/api/v1/smog/" + pageNumber);
        return restTemplate.getForObject(uri, SmogDataPage.class);
    }
}
