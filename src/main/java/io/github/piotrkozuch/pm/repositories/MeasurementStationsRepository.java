package io.github.piotrkozuch.pm.repositories;

import io.github.piotrkozuch.pm.models.MeasurementStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MeasurementStationsRepository extends JpaRepository<MeasurementStation, UUID>, CustomMeasurementStationRepository {

    Optional<MeasurementStation> findByName(String name);
}
