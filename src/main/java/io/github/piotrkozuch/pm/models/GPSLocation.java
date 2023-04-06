package io.github.piotrkozuch.pm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

import static io.github.piotrkozuch.pm.utils.Checks.checkRequired;

@Embeddable
public class GPSLocation {

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    public GPSLocation() {

    }

    private GPSLocation(GPSLocationBuilder builder) {
        this.latitude = checkRequired("latitude", builder.latitude);
        this.longitude = checkRequired("longitude", builder.longitude);
    }

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

    public static class GPSLocationBuilder {

        private Double latitude;
        private Double longitude;

        private GPSLocationBuilder() {

        }

        public GPSLocation build() {
            return new GPSLocation(this);
        }

        public static GPSLocationBuilder aGPSLocation() {
            return new GPSLocationBuilder();
        }

        public GPSLocationBuilder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public GPSLocationBuilder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }
    }
}
