package io.github.piotrkozuch.pm.actions;

import io.github.piotrkozuch.pm.repositories.MeasurementStationsRepository;
import io.github.piotrkozuch.pm.models.MeasurementStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static io.github.piotrkozuch.pm.utils.Checks.checkRequired;
import static java.lang.String.format;

@Component
public class StoreMeasurementsAction {

    //TODO: Define action interface and test this class

    public static class Params {

        public final MeasurementStation measurementStation;

        public Params(MeasurementStation measurementStation) {
            this.measurementStation = checkRequired("measurementStation", measurementStation);
        }
    }

    private MeasurementStationsRepository repository;

    @Autowired
    public StoreMeasurementsAction(MeasurementStationsRepository repository) {
        this.repository = checkRequired("repository", repository);
    }

    @Transactional
    public UUID execute(Params params) {
        validate(params);

        return repository.findByName(params.measurementStation.getName())
            .map(station -> updateMeasurementStation(params, station))
            .orElseGet(() -> save(params.measurementStation));
    }

    private void validate(Params params) {
        if (params.measurementStation.getMeasurements().isEmpty()) {
            throw new IllegalStateException(format("Empty measurements for %s", params.measurementStation.getName()));
        }
    }

    private UUID save(MeasurementStation params) {
        return repository.save(params).getId();
    }

    private UUID updateMeasurementStation(Params params, MeasurementStation station) {
        params.measurementStation.getMeasurements()
            .forEach(measurement -> {
                measurement.setMeasurementStation(station);
                station.addMeasurement(measurement);
            });
        return save(station);
    }
}
