package io.github.piotrkozuch.pm.repositories;

import io.github.piotrkozuch.pm.models.MeasurementStation;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface CustomMeasurementStationRepository {

    Optional<MeasurementStation> findByIdWithMeasurements(UUID id, Instant startDate, Instant endDate);

}
