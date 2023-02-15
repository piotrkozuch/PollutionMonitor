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
}
