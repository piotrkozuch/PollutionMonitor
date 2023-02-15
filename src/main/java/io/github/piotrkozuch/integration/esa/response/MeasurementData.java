package io.github.piotrkozuch.integration.esa.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static io.github.piotrkozuch.utils.Checks.checkRequired;

public record MeasurementData(Double humidityAvg,
                              Double pressureAvg,
                              Double temperatureAvg,
                              Double pm10Avg,
                              Double pm25Avg) {

    @JsonCreator
    public MeasurementData(@JsonProperty("humidity_avg") Double humidityAvg,
                           @JsonProperty("pressure_avg") Double pressureAvg,
                           @JsonProperty("temperature_avg") Double temperatureAvg,
                           @JsonProperty("pm10_avg") Double pm10Avg,
                           @JsonProperty("pm25_avg") Double pm25Avg) {
        this.humidityAvg = checkRequired("humidityAvg", humidityAvg);
        this.pressureAvg = checkRequired("pressureAvg", pressureAvg);
        this.temperatureAvg = checkRequired("temperatureAvg", temperatureAvg);
        this.pm10Avg = checkRequired("pm10Avg", pm10Avg);
        this.pm25Avg = checkRequired("pm25Avg", pm25Avg);
    }
}
