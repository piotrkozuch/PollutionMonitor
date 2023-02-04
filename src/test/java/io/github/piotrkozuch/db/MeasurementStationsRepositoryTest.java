package io.github.piotrkozuch.db;

import io.github.piotrkozuch.db.model.MeasurementStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MeasurementStationsRepositoryTest implements MeasurementStationsTestData {

    @Autowired
    private MeasurementStationsRepository repository;

    @Test
    void should_save_station() {
        // given
        var station = aMeasurementStation();

        // when
        var persisted = repository.save(station);

        // then
        var all = repository.findAll();
        assertThat(all.size()).isEqualTo(1);
        assertThat(all.contains(persisted)).isTrue();
    }
}