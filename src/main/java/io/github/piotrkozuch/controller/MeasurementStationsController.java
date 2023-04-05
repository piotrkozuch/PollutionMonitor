package io.github.piotrkozuch.controller;

import io.github.piotrkozuch.db.model.Measurement;
import io.github.piotrkozuch.db.model.MeasurementStation;
import io.github.piotrkozuch.service.MeasurementStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static io.github.piotrkozuch.utils.Checks.checkRequired;

@RestController
@RequestMapping("/measurement-stations")
public class MeasurementStationsController {

    private final MeasurementStationsService service;

    @Autowired
    public MeasurementStationsController(MeasurementStationsService service) {
        this.service = checkRequired("service", service);
    }

    @GetMapping
    public List<MeasurementStation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MeasurementStation getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/measurements")
    public List<Measurement> getMeasurements(@PathVariable UUID id) {
        return service.getMeasurements(id);
    }

    @GetMapping("/{id}/latest-measurements")
    public List<Measurement> getLatestMeasurements(@PathVariable UUID id) {
        return service.getLatestMeasurements(id);
    }

    @GetMapping("/latest-measurements")
    public List<Measurement> getLatestMeasurements() {
        return service.getLatestMeasurements();
    }
}
