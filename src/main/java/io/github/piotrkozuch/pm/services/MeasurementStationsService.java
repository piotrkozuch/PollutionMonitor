package io.github.piotrkozuch.pm.services;

import io.github.piotrkozuch.pm.models.Measurement;
import io.github.piotrkozuch.pm.models.MeasurementStation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MeasurementStationsService {

    public List<MeasurementStation> getAll() {
        return List.of();
    }

    public MeasurementStation getById(UUID id) {
        return null;
    }

    public List<Measurement> getMeasurements(UUID id) {
        return List.of();
    }

    public List<Measurement> getLatestMeasurements(UUID id) {
        return List.of();
    }

    public List<Measurement> getLatestMeasurements() {
        return List.of();
    }
}
