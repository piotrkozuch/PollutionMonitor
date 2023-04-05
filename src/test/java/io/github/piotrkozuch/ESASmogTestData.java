package io.github.piotrkozuch;

import io.github.piotrkozuch.integration.esa.response.MeasurementData;
import io.github.piotrkozuch.integration.esa.response.School;
import io.github.piotrkozuch.integration.esa.response.SmogData;
import io.github.piotrkozuch.integration.esa.response.SmogDataPage;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface ESASmogTestData {

    default SmogDataPage.SmogDataPageBuilder aSmogDataPage() {
        return SmogDataPage.SmogDataPageBuilder.builder()
            .pagesTotal(1L)
            .hasNextPage(false)
            .smogData(List.of());
    }

    default SmogData.SmogDataBuilder aSmogData() {
        final var random = new Random();
        final var measurementData = aMeasurementData(random);
        final var school = aSchool().build();

        return SmogData.SmogDataBuilder.builder()
            .data(measurementData)
            .school(school)
            .timestamp("2023-02-15 21:05:55");
    }

    default School.SchoolBuilder aSchool() {
        final var random = new Random();
        return School.SchoolBuilder.builder()
            .name("School")
            .postcode("30-392")
            .city("City")
            .latitude(random.nextDouble())
            .longitude(random.nextDouble())
            .street(Optional.of("Street"));
    }

    default MeasurementData aMeasurementData(Random random) {
        return new MeasurementData(
            Optional.of(random.nextDouble()),
            Optional.of(random.nextDouble()),
            Optional.of(random.nextDouble()),
            Optional.of(random.nextDouble()),
            Optional.of(random.nextDouble())
        );
    }
}
