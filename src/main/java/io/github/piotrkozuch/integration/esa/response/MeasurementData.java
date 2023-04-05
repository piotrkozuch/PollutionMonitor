package io.github.piotrkozuch.integration.esa.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public record MeasurementData(Optional<Double> humidityAvg,
                              Optional<Double> pressureAvg,
                              Optional<Double> temperatureAvg,
                              Optional<Double> pm10Avg,
                              Optional<Double> pm25Avg) {

    @JsonCreator
    public MeasurementData(@JsonProperty("humidity_avg") Optional<Double> humidityAvg,
                           @JsonProperty("pressure_avg") Optional<Double> pressureAvg,
                           @JsonProperty("temperature_avg") Optional<Double> temperatureAvg,
                           @JsonProperty("pm10_avg") Optional<Double> pm10Avg,
                           @JsonProperty("pm25_avg") Optional<Double> pm25Avg) {
        this.humidityAvg = humidityAvg;
        this.pressureAvg = pressureAvg;
        this.temperatureAvg = temperatureAvg;
        this.pm10Avg = pm10Avg;
        this.pm25Avg = pm25Avg;
    }

    public boolean isEmpty() {
        return humidityAvg.isEmpty()
            && pressureAvg.isEmpty()
            && temperatureAvg.isEmpty()
            && pm10Avg.isEmpty()
            && pm25Avg.isEmpty();
    }
}
