package io.github.piotrkozuch.integration.esa.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

import static io.github.piotrkozuch.utils.Checks.checkRequired;

public record School(String name,
                     String postcode,
                     String city,
                     Double longitude,
                     Double latitude,
                     Optional<String> street) {

    @JsonCreator
    public School(@JsonProperty("name") String name,
                  @JsonProperty("post_code") String postcode,
                  @JsonProperty("city") String city,
                  @JsonProperty("longitude") Double longitude,
                  @JsonProperty("latitude") Double latitude,
                  @JsonProperty("street") Optional<String> street) {
        this.name = checkRequired("name", name);
        this.postcode = checkRequired("postcode", postcode);
        this.city = checkRequired("city", city);
        this.longitude = checkRequired("longitude", longitude);
        this.latitude = checkRequired("latitude", latitude);
        this.street = street;
    }

    public static class SchoolBuilder {

        private String name;
        private String postcode;
        private String city;
        private Double longitude;
        private Double latitude;
        private Optional<String> street;

        private SchoolBuilder() {

        }

        public static SchoolBuilder builder() {
            return new SchoolBuilder();
        }

        public School build() {
            return new School(name, postcode, city, longitude, latitude, street);
        }

        public SchoolBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SchoolBuilder postcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public SchoolBuilder city(String city) {
            this.city = city;
            return this;
        }

        public SchoolBuilder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public SchoolBuilder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public SchoolBuilder street(Optional<String> street) {
            this.street = street;
            return this;
        }
    }
}
