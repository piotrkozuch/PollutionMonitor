package io.github.piotrkozuch.pm.controller;

import io.github.piotrkozuch.pm.response.MeasurementResponse;
import io.github.piotrkozuch.pm.response.MeasurementStationResponse;
import io.github.piotrkozuch.pm.service.MeasurementStationsService;
import io.github.piotrkozuch.pm.util.Checks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static io.github.piotrkozuch.pm.response.converter.MeasurementResponseConverter.measurementsResponse;
import static io.github.piotrkozuch.pm.response.converter.MeasurementStationResponseConverter.measurementStationResponse;
import static io.github.piotrkozuch.pm.response.converter.MeasurementStationResponseConverter.measurementStationsResponse;

@RestController
@RequestMapping("/measurement-stations")
public class MeasurementStationsController {

    private final MeasurementStationsService service;

    @Autowired
    public MeasurementStationsController(MeasurementStationsService service) {
        this.service = Checks.checkRequired("service", service);
    }

    @GetMapping
    public List<MeasurementStationResponse> getAll() {
        return measurementStationsResponse(service.getAll());
    }

    @GetMapping("/{id}")
    public MeasurementStationResponse getById(@PathVariable UUID id) {
        return measurementStationResponse(service.getById(id));
    }

    @GetMapping("/{id}/measurements")
    public List<MeasurementResponse> getMeasurements(@PathVariable UUID id) {
        return measurementsResponse(service.getMeasurements(id));
    }

    @GetMapping("/{id}/latest-measurements")
    public List<MeasurementResponse> getLatestMeasurements(@PathVariable UUID id) {
        return measurementsResponse(service.getLatestMeasurements(id));
    }

    @GetMapping("/latest-measurements")
    public List<MeasurementResponse> getLatestMeasurements() {
        return measurementsResponse(service.getLatestMeasurements());
    }
}
