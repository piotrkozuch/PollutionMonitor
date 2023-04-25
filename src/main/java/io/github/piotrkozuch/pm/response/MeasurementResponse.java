package io.github.piotrkozuch.pm.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.UUID;

import static io.github.piotrkozuch.pm.util.Checks.checkRequired;

public record MeasurementResponse(UUID id,
                                  @JsonProperty("humidity_avg")
                                  double humidityAvg,
                                  @JsonProperty("pressure_avg")
                                  double pressureAvg,
                                  @JsonProperty("temperature_avg")
                                  double temperatureAvg,
                                  @JsonProperty("pm10_avg")
                                  double pm10Avg,
                                  @JsonProperty("pm25_avg")
                                  double pm25Avg,
                                  @JsonProperty("created_date")
                                  Instant createdDate,
                                  @JsonProperty("measurement_station_id")
                                  UUID measurementStationId) {

    public MeasurementResponse {
        checkRequired("id", id);
        checkRequired("createdDate", createdDate);
        checkRequired("measurementStationId", measurementStationId);
    }
}
