package io.github.piotrkozuch.db;

import io.github.piotrkozuch.db.model.MeasurementStation;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface CustomMeasurementStationRepository {

    Optional<MeasurementStation> findByIdWithMeasurements(UUID id, Instant startDate, Instant endDate);

}
