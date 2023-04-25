package io.github.piotrkozuch.pm.repositorie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MeasurementStationsRepositoryTest implements MeasurementStationsTestData {

    @Autowired
    private MeasurementStationsRepository repository;

    @Test
    void should_save_station() {
        // given
        var station = aMeasurementStation().build();
        var measurement = aMeasurementFor(station).build();
        station.setMeasurements(List.of(measurement));

        // when
        repository.save(station);

        // then
        var result = repository.findByIdWithMeasurements(
            station.getId(),
            measurement.getCreatedDate().minusSeconds(1),
            measurement.getCreatedDate().plusSeconds(1)
        );

        assertThat(result).isPresent();
        assertThat(result.get().getMeasurements().size()).isEqualTo(1);
        assertTrue(result.get().getMeasurements().stream().anyMatch(m -> m.getId().equals(measurement.getId())));
    }
}