package io.github.piotrkozuch.pm.response.converter;

import io.github.piotrkozuch.pm.model.Measurement;
import io.github.piotrkozuch.pm.response.MeasurementResponse;

import java.util.List;

import static io.github.piotrkozuch.pm.util.Checks.checkRequired;

public class MeasurementResponseConverter {

    public static List<MeasurementResponse> measurementsResponse(List<Measurement> measurements) {
        return measurements.stream()
            .map(MeasurementResponseConverter::measurementResponse)
            .toList();
    }

    public static MeasurementResponse measurementResponse(Measurement measurement) {
        checkRequired("measurement", measurement);

        return new MeasurementResponse(
            measurement.getId(),
            measurement.getHumidityAvg(),
            measurement.getPressureAvg(),
            measurement.getTemperatureAvg(),
            measurement.getPm10Avg(),
            measurement.getPm25Avg(),
            measurement.getCreatedDate(),
            measurement.getMeasurementStation().getId()
        );
    }
}
