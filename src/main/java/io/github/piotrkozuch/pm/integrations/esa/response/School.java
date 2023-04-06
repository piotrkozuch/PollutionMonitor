package io.github.piotrkozuch.pm.integrations.esa.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.piotrkozuch.pm.utils.Checks;

import java.util.Optional;

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
        this.name = Checks.checkRequired("name", name);
        this.postcode = Checks.checkRequired("postcode", postcode);
        this.city = Checks.checkRequired("city", city);
        this.longitude = Checks.checkRequired("longitude", longitude);
        this.latitude = Checks.checkRequired("latitude", latitude);
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

        public static SchoolBuilder aSchool() {
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
