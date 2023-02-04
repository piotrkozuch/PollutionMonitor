package io.github.piotrkozuch.db.model;

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

@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    private UUID id;

    @Column(nullable = false)
    private double humidityAvg;

    @Column(nullable = false)
    private double pressureAvg;

    @Column(nullable = false)
    private double temperatureAvg;

    @Column(name = "pm10_avg", nullable = false)
    private double pm10Avg;

    @Column(name = "pm25_avg", nullable = false)
    private double pm25Avg;

    @Column(nullable = false)
    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_station_id", referencedColumnName = "id")
    private MeasurementStation measurementStation;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getHumidityAvg() {
        return humidityAvg;
    }

    public void setHumidityAvg(double humidityAvg) {
        this.humidityAvg = humidityAvg;
    }

    public double getPressureAvg() {
        return pressureAvg;
    }

    public void setPressureAvg(double pressureAvg) {
        this.pressureAvg = pressureAvg;
    }

    public double getTemperatureAvg() {
        return temperatureAvg;
    }

    public void setTemperatureAvg(double temperatureAvg) {
        this.temperatureAvg = temperatureAvg;
    }

    public double getPm10Avg() {
        return pm10Avg;
    }

    public void setPm10Avg(double pm10Avg) {
        this.pm10Avg = pm10Avg;
    }

    public double getPm25Avg() {
        return pm25Avg;
    }

    public void setPm25Avg(double pm25Avg) {
        this.pm25Avg = pm25Avg;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public MeasurementStation getMeasurementStation() {
        return measurementStation;
    }

    public void setMeasurementStation(MeasurementStation measurementStation) {
        this.measurementStation = measurementStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Double.compare(that.humidityAvg, humidityAvg) == 0 && Double.compare(that.pressureAvg, pressureAvg) == 0 && Double.compare(that.temperatureAvg, temperatureAvg) == 0 && Double.compare(that.pm10Avg, pm10Avg) == 0 && Double.compare(that.pm25Avg, pm25Avg) == 0 && Objects.equals(id, that.id) && Objects.equals(createdDate, that.createdDate) && Objects.equals(measurementStation, that.measurementStation);
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
            ", measurementStation=" + measurementStation +
            '}';
    }
}
