package io.github.piotrkozuch.db;

import io.github.piotrkozuch.db.model.MeasurementStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MeasurementStationsRepository extends JpaRepository<MeasurementStation, UUID>, CustomMeasurementStationRepository {

}
