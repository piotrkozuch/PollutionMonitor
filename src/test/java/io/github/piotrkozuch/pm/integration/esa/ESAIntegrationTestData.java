package io.github.piotrkozuch.pm.integration.esa;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

public interface ESAIntegrationTestData {

    default void stubGetSmogDataPage(WireMockExtension wireMockServer, int page) {
        var response = """
            {
              "smog_data": [
                {
                  "school": {
                    "name": "SZKOŁA PODSTAWOWA IM. JANA PAWŁA II W RACŁAWICACH ŚLĄSKICH",
                    "street": "UL. ZWYCIĘSTWA",
                    "post_code": "48-250",
                    "city": "RACŁAWICE ŚLĄSKIE",
                    "longitude": "17.771528",
                    "latitude": "50.311402"
                  },
                  "data": {
                    "humidity_avg": 92.825,
                    "pressure_avg": 1010.6333333333333,
                    "temperature_avg": 1.375,
                    "pm10_avg": 88.40833333333335,
                    "pm25_avg": 61.23333333333333
                  },
                  "timestamp": "2023-02-15 21:05:55"
                }
              ],
              "it_has_next_page": true,
              "pages_total": 1
            }
            """;

        wireMockServer.stubFor(
            get("/api/v1/smog/" + page)
                .willReturn(aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withBody(response)
                )
        );
    }
}
