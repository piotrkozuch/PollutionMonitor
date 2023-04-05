package io.github.piotrkozuch.db.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static io.github.piotrkozuch.utils.Checks.checkRequired;

@Entity
@Table(name = "measurement_stations")
public class MeasurementStation {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private GPSLocation gpsLocation;

    @Column(nullable = false)
    private Instant createdDate;

    @Column(nullable = false)
    private Instant updatedDate;

    @OneToMany(
        mappedBy = "measurementStation",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Measurement> measurements = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = checkRequired("id", id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = checkRequired("name", name);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = checkRequired("address", address);
    }

    public GPSLocation getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(GPSLocation gpsLocation) {
        this.gpsLocation = checkRequired("gpsLocation", gpsLocation);
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = checkRequired("createdDate", createdDate);
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = checkRequired("updatedDate", updatedDate);
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = checkRequired("measurements", measurements);
    }

    public void addMeasurement(Measurement measurement) {
        this.measurements.add(measurement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementStation that = (MeasurementStation) o;
        return Objects.equals(id, that.id)
            && Objects.equals(name, that.name)
            && Objects.equals(address, that.address)
            && Objects.equals(gpsLocation, that.gpsLocation)
            && Objects.equals(createdDate, that.createdDate)
            && Objects.equals(updatedDate, that.updatedDate)
            && Objects.equals(measurements, that.measurements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, gpsLocation, createdDate, updatedDate, measurements);
    }

    @Override
    public String toString() {
        return "MeasurementStation{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address=" + address +
            ", gpsLocation=" + gpsLocation +
            ", createdDate=" + createdDate +
            ", updatedDate=" + updatedDate +
            '}';
    }
}
