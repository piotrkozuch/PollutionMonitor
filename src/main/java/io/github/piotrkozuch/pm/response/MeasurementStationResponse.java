package io.github.piotrkozuch.pm.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.UUID;

import static io.github.piotrkozuch.pm.util.Checks.checkRequired;

public record MeasurementStationResponse(UUID id,
                                         String name,
                                         AddressResponse address,
                                         @JsonProperty("gps_location")
                                         GPSLocationResponse gpsLocation,
                                         @JsonProperty("created_date")
                                         Instant createdDate,
                                         @JsonProperty("updated_date")
                                         Instant updatedDate) {

    public MeasurementStationResponse {
        checkRequired("id", id);
        checkRequired("name", name);
        checkRequired("address", address);
        checkRequired("gpsLocation", gpsLocation);
        checkRequired("createdDate", createdDate);
        checkRequired("updatedDate", updatedDate);
    }
}
