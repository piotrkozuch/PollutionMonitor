package io.github.piotrkozuch.pm.jobs;

import io.github.piotrkozuch.pm.integrations.esa.ESASmogDataImporter;
import io.github.piotrkozuch.pm.integrations.esa.ESASmogTestData;
import io.github.piotrkozuch.pm.actions.StoreMeasurementsAction;
import io.github.piotrkozuch.pm.repositories.MeasurementStationsRepository;
import io.github.piotrkozuch.pm.integrations.esa.ESAClient;
import io.github.piotrkozuch.pm.integrations.esa.ESASmogDataConverter;
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
    @Autowired
    private StoreMeasurementsAction storeMeasurementsAction;
    @Autowired
    private ESASmogDataConverter esaSmogDataConverter;
    private ESAClient esaClient = mock(ESAClient.class);
    private ESASmogDataImporter esaSmogDataImporter;

    @BeforeEach
    void setup() {
        repository.deleteAll();
        esaSmogDataImporter = new ESASmogDataImporter(esaClient, esaSmogDataConverter, storeMeasurementsAction);
    }

    @Test
    void should_import_all_data() {
        // given
        var school1 = aSchool()
            .name("School 1")
            .build();

        var school2 = aSchool()
            .name("School 2")
            .build();

        var page0SmogData = aSmogData()
            .school(school1)
            .build();

        var page0 = aSmogDataPage()
            .smogData(List.of(page0SmogData))
            .hasNextPage(true)
            .pagesTotal(2L)
            .build();

        var page1SmogData = aSmogData()
            .school(school2)
            .build();

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

    @Test
    void should_update_existing_data() {
        // given
        var school = aSchool()
            .name("School 1")
            .build();

        var smogData = aSmogData()
            .school(school)
            .build();

        var page = aSmogDataPage()
            .smogData(List.of(smogData))
            .hasNextPage(false)
            .pagesTotal(1L)
            .build();

        given(esaClient.getSmogDataPage(0)).willReturn(page);

        // when
        for(var i = 0; i < 2; i++) {
            esaSmogDataImporter.importSmogData();
        }

        // then
        var stations = repository.findAll();
        assertThat(stations.size()).isEqualTo(1);

        stations.forEach(station -> {
            var stationWithMeasurements = repository.findByIdWithMeasurements(
                station.getId(),
                station.getCreatedDate().minusSeconds(7200),
                station.getCreatedDate().plusSeconds(10)
            );

            assertThat(stationWithMeasurements).isPresent();
            assertThat(stationWithMeasurements.get().getMeasurements().size()).isEqualTo(2);
        });
    }

}