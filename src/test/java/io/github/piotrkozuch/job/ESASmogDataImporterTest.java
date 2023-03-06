package io.github.piotrkozuch.job;

import io.github.piotrkozuch.ESASmogTestData;
import io.github.piotrkozuch.db.MeasurementStationsRepository;
import io.github.piotrkozuch.integration.esa.ESAClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
class ESASmogDataImporterTest implements ESASmogTestData {

    @Autowired
    private MeasurementStationsRepository repository;
    private ESAClient esaClient = mock(ESAClient.class);
    private ESASmogDataImporter esaSmogDataImporter;

    @BeforeEach
    void setup() {
        repository.deleteAll();
        esaSmogDataImporter = new ESASmogDataImporter(esaClient, repository);
    }

    @Test
    void should_import_all_data() {
        // given
        var page0SmogData = aSmogData().build();

        var page0 = aSmogDataPage()
            .smogData(List.of(page0SmogData))
            .hasNextPage(true)
            .pagesTotal(2L)
            .build();

        var page1SmogData = aSmogData().build();
        var page1 = aSmogDataPage()
            .smogData(List.of(page1SmogData))
            .hasNextPage(false)
            .pagesTotal(2L)
            .build();

        given(esaClient.getSmogDataPage(0)).willReturn(page0);
        given(esaClient.getSmogDataPage(1)).willReturn(page1);

        // when
        esaSmogDataImporter.importSmogData();

        // then
        var stations = repository.findAll();
        assertThat(stations.size()).isEqualTo(2);

        stations.forEach(station -> {
            var stationWithMeasurements = repository.findByIdWithMeasurements(
                station.getId(),
                station.getCreatedDate().minusSeconds(7200),
                station.getCreatedDate().plusSeconds(10)
            );

           assertThat(stationWithMeasurements).isPresent();
           assertThat(stationWithMeasurements.get().getMeasurements().size()).isEqualTo(1);
        });
    }

}