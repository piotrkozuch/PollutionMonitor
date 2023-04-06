package io.github.piotrkozuch.pm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import static io.github.piotrkozuch.pm.utils.Checks.checkRequired;

@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    private UUID id;

    private Double humidityAvg;

    private Double pressureAvg;

    private Double temperatureAvg;

    @Column(name = "pm10_avg")
    private Double pm10Avg;

    @Column(name = "pm25_avg")
    private Double pm25Avg;

    @Column(nullable = false)
    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_station_id", referencedColumnName = "id")
    private MeasurementStation measurementStation;

    public Measurement() {

    }

    private Measurement(MeasurementBuilder builder) {
        this.id = checkRequired("id", builder.id);
        this.humidityAvg = builder.humidityAvg;
        this.pressureAvg = builder.pressureAvg;
        this.temperatureAvg = builder.temperatureAvg;
        this.pm10Avg = builder.pm10Avg;
        this.pm25Avg = builder.pm25Avg;
        this.createdDate = checkRequired("createdDate", builder.createdDate);
        this.measurementStation = checkRequired("measurementStation", builder.measurementStation);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = checkRequired("id", id);
    }

    public Double getHumidityAvg() {
        return humidityAvg;
    }

    public void setHumidityAvg(Double humidityAvg) {
        this.humidityAvg = humidityAvg;
    }

    public Double getPressureAvg() {
        return pressureAvg;
    }

    public void setPressureAvg(Double pressureAvg) {
        this.pressureAvg = pressureAvg;
    }

    public Double getTemperatureAvg() {
        return temperatureAvg;
    }

    public void setTemperatureAvg(Double temperatureAvg) {
        this.temperatureAvg = temperatureAvg;
    }

    public Double getPm10Avg() {
        return pm10Avg;
    }

    public void setPm10Avg(Double pm10Avg) {
        this.pm10Avg = pm10Avg;
    }

    public Double getPm25Avg() {
        return pm25Avg;
    }

    public void setPm25Avg(Double pm25Avg) {
        this.pm25Avg = pm25Avg;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = checkRequired("createdDate", createdDate);
    }

    public MeasurementStation getMeasurementStation() {
        return measurementStation;
    }

    public void setMeasurementStation(MeasurementStation measurementStation) {
        this.measurementStation = checkRequired("measurementStation", measurementStation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Double.compare(that.humidityAvg, humidityAvg) == 0
            && Double.compare(that.pressureAvg, pressureAvg) == 0
            && Double.compare(that.temperatureAvg, temperatureAvg) == 0
            && Double.compare(that.pm10Avg, pm10Avg) == 0
            && Double.compare(that.pm25Avg, pm25Avg) == 0 &&
            Objects.equals(id, that.id) &&
            Objects.equals(createdDate, that.createdDate)
            && Objects.equals(measurementStation, that.measurementStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, humidityAvg, pressureAvg, temperatureAvg, pm10Avg, pm25Avg, createdDate, measurementStation);
    }

    @Override
    public String toString() {
        return "Measurement{" +
            "id=" + id +
            ", humidityAvg=" + humidityAvg +
            ", pressureAvg=" + pressureAvg +
            ", temperatureAvg=" + temperatureAvg +
            ", pm10Avg=" + pm10Avg +
            ", pm25Avg=" + pm25Avg +
            ", createdDate=" + createdDate +
            '}';
    }

    public static class MeasurementBuilder {

        private UUID id;
        private Double humidityAvg;
        private Double pressureAvg;
        private Double temperatureAvg;
        private Double pm10Avg;
        private Double pm25Avg;
        private Instant createdDate;
        private MeasurementStation measurementStation;

        private MeasurementBuilder() {

        }

        public Measurement build() {
            return new Measurement(this);
        }

        public static MeasurementBuilder aMeasurement() {
            return new MeasurementBuilder();
        }

        public MeasurementBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public MeasurementBuilder humidityAvg(Double humidityAvg) {
            this.humidityAvg = humidityAvg;
            return this;
        }

        public MeasurementBuilder pressureAvg(Double pressureAvg) {
            this.pressureAvg = pressureAvg;
            return this;
        }

        public MeasurementBuilder temperatureAvg(Double temperatureAvg) {
            this.temperatureAvg = temperatureAvg;
            return this;
        }

        public MeasurementBuilder pm10Avg(Double pm10Avg) {
            this.pm10Avg = pm10Avg;
            return this;
        }

        public MeasurementBuilder pm25Avg(Double pm25Avg) {
            this.pm25Avg = pm25Avg;
            return this;
        }

        public MeasurementBuilder createdDate(Instant createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public MeasurementBuilder measurementStation(MeasurementStation measurementStation) {
            this.measurementStation = measurementStation;
            return this;
        }
    }
}
