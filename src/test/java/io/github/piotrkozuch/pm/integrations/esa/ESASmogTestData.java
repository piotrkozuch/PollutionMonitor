package io.github.piotrkozuch.pm.integrations.esa;

import io.github.piotrkozuch.pm.integrations.esa.response.MeasurementData;
import io.github.piotrkozuch.pm.integrations.esa.response.School;
import io.github.piotrkozuch.pm.integrations.esa.response.SmogData;
import io.github.piotrkozuch.pm.integrations.esa.response.SmogDataPage;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface ESASmogTestData {

    default SmogDataPage.SmogDataPageBuilder aSmogDataPage() {
        return SmogDataPage.SmogDataPageBuilder.aSmogDataPage()
            .pagesTotal(1L)
            .hasNextPage(false)
            .smogData(List.of());
    }

    default SmogData.SmogDataBuilder aSmogData() {
        final var random = new Random();
        final var measurementData = aMeasurementData(random);
        final var school = aSchool().build();

        return SmogData.SmogDataBuilder.aSmogData()
            .data(measurementData)
            .school(school)
            .timestamp("2023-02-15 21:05:55");
    }

    default School.SchoolBuilder aSchool() {
        final var random = new Random();
        return School.SchoolBuilder.aSchool()
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
