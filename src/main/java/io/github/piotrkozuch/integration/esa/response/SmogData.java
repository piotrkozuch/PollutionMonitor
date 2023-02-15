package io.github.piotrkozuch.integration.esa.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static io.github.piotrkozuch.utils.Checks.checkRequired;

public record SmogData(School school,
                       MeasurementData data,
                       String timestamp) {

    @JsonCreator
    public SmogData(@JsonProperty("school") School school,
                    @JsonProperty("data") MeasurementData data,
                    @JsonProperty("timestamp") String timestamp) {
        this.school = checkRequired("school", school);
        this.data = checkRequired("data", data);
        this.timestamp = checkRequired("timestamp", timestamp);
    }
}
