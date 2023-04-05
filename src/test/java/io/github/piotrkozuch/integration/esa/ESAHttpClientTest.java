package io.github.piotrkozuch.integration.esa;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ESAHttpClientTest implements ESAIntegrationTestData {

    @RegisterExtension
    static final WireMockExtension wireMockServer = WireMockExtension.newInstance()
        .options(wireMockConfig().dynamicPort())
        .build();

    @Autowired
    ESAClient esaClient;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("esa-integration.url", wireMockServer::baseUrl);
    }

    @Test
    void should_get_and_return_smog_data_page() {
        // given
        stubGetSmogDataPage(wireMockServer, 0);

        // when
        var page = esaClient.getSmogDataPage(0);

        // then
        assertThat(page).isNotNull();
        assertThat(page.smogData().size()).isEqualTo(1);
        assertThat(page.hasNextPage()).isTrue();
        assertThat(page.pagesTotal()).isEqualTo(1);

        var smogData = page.smogData().stream().findFirst().orElseThrow();
        assertThat(smogData.timestamp()).isEqualTo("2023-02-15 21:05:55");

        var school = smogData.school();
        assertThat(school.name()).isEqualTo("SZKOŁA PODSTAWOWA IM. JANA PAWŁA II W RACŁAWICACH ŚLĄSKICH");
        assertThat(school.postcode()).isEqualTo("48-250");
        assertThat(school.city()).isEqualTo("RACŁAWICE ŚLĄSKIE");
        assertThat(school.longitude()).isEqualTo(17.771528);
        assertThat(school.latitude()).isEqualTo(50.311402);
        assertThat(school.street()).hasValue("UL. ZWYCIĘSTWA");

        var data = smogData.data();
        assertThat(data.humidityAvg()).hasValue(92.825);
        assertThat(data.pressureAvg()).hasValue(1010.6333333333333);
        assertThat(data.temperatureAvg()).hasValue(1.375);
        assertThat(data.pm10Avg()).hasValue(88.40833333333335);
        assertThat(data.pm25Avg()).hasValue(61.23333333333333);
    }

}