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

    public static class SmogDataBuilder {

        private School school;
        private MeasurementData data;
        private String timestamp;

        private SmogDataBuilder() {

        }

        public static SmogDataBuilder builder() {
            return new SmogDataBuilder();
        }

        public SmogData build() {
            return new SmogData(school, data, timestamp);
        }

        public SmogDataBuilder school(School school) {
            this.school = school;
            return this;
        }

        public SmogDataBuilder data(MeasurementData data) {
            this.data = data;
            return this;
        }

        public SmogDataBuilder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }
    }
}
