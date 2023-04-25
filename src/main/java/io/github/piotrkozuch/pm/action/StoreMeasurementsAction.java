package io.github.piotrkozuch.pm.action;

import io.github.piotrkozuch.pm.model.MeasurementStation;
import io.github.piotrkozuch.pm.repositorie.MeasurementStationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static io.github.piotrkozuch.pm.util.Checks.checkRequired;
import static java.lang.String.format;

@Component
public class StoreMeasurementsAction implements Action<StoreMeasurementsAction.Params, UUID> {

    public record Params(MeasurementStation measurementStation) {

        public Params(MeasurementStation measurementStation) {
            this.measurementStation = checkRequired("measurementStation", measurementStation);
        }
    }

    private MeasurementStationsRepository repository;

    @Autowired
    public StoreMeasurementsAction(MeasurementStationsRepository repository) {
        this.repository = checkRequired("repository", repository);
    }

    @Override
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
