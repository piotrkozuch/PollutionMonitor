package io.github.piotrkozuch.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

import static io.github.piotrkozuch.utils.Checks.checkRequired;

@Embeddable
public class GPSLocation {

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = checkRequired("latitude", latitude);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = checkRequired("longitude", longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPSLocation that = (GPSLocation) o;
        return Double.compare(that.latitude, latitude) == 0
            && Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "GPSLocation{" +
            "latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
    }
}
