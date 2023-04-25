package io.github.piotrkozuch.pm.integration.esa;

import io.github.piotrkozuch.pm.integration.SmogDataConverter;
import io.github.piotrkozuch.pm.integration.esa.response.MeasurementData;
import io.github.piotrkozuch.pm.integration.esa.response.School;
import io.github.piotrkozuch.pm.integration.esa.response.SmogData;
import io.github.piotrkozuch.pm.model.Measurement;
import io.github.piotrkozuch.pm.model.MeasurementStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static io.github.piotrkozuch.pm.model.Address.AddressBuilder.anAddress;
import static io.github.piotrkozuch.pm.model.GPSLocation.GPSLocationBuilder.aGPSLocation;
import static io.github.piotrkozuch.pm.model.Measurement.MeasurementBuilder.aMeasurement;
import static io.github.piotrkozuch.pm.model.MeasurementStation.MeasurementStationBuilder.aMeasurementStation;
import static java.time.Instant.now;
import static java.util.UUID.randomUUID;

@Component
public class ESASmogDataConverter implements SmogDataConverter<SmogData> {

    private static final Logger LOG = LoggerFactory.getLogger(ESASmogDataConverter.class);

    @Override
    public MeasurementStation convert(SmogData smogData) {
        final var measurementStation = measurementStationFrom(smogData.school()).build();
        final var measurement = measurementsFrom(measurementStation, smogData.data());

        measurement.ifPresent(measurementStation::addMeasurement);
        return measurementStation;
    }

    private Optional<Measurement> measurementsFrom(MeasurementStation station, MeasurementData data) {
        if (data.isEmpty()) {
            LOG.warn("Skipping empty ESA measurement data!");
            return Optional.empty();
        }

        final var measurement = aMeasurement()
            .id(randomUUID())
            .createdDate(now())
            .measurementStation(station);

        data.temperatureAvg().ifPresent(measurement::temperatureAvg);
        data.pressureAvg().ifPresent(measurement::pressureAvg);
        data.pm25Avg().ifPresent(measurement::pm25Avg);
        data.pm10Avg().ifPresent(measurement::pm10Avg);
        data.humidityAvg().ifPresent(measurement::humidityAvg);
        return Optional.of(measurement.build());
    }

    private MeasurementStation.MeasurementStationBuilder measurementStationFrom(School school) {
        final var station = aMeasurementStation()
            .id(randomUUID())
            .createdDate(now())
            .updatedDate(now())
            .name(school.name());

        final var gpsLocation = aGPSLocation()
            .latitude(school.latitude())
            .longitude(school.longitude())
            .build();

        station.gpsLocation(gpsLocation);

        final var address = anAddress()
            .city(school.city())
            .postcode(school.postcode());

        school.street().ifPresent(address::street);
        station.address(address.build());

        return station;
    }
}
