package io.github.piotrkozuch.integration.esa;

import io.github.piotrkozuch.db.model.Address;
import io.github.piotrkozuch.db.model.GPSLocation;
import io.github.piotrkozuch.db.model.Measurement;
import io.github.piotrkozuch.db.model.MeasurementStation;
import io.github.piotrkozuch.integration.SmogDataConverter;
import io.github.piotrkozuch.integration.esa.response.MeasurementData;
import io.github.piotrkozuch.integration.esa.response.School;
import io.github.piotrkozuch.integration.esa.response.SmogData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.time.Instant.now;
import static java.util.UUID.randomUUID;

@Component
public class ESASmogDataConverter implements SmogDataConverter<SmogData> {

    private static final Logger LOG = LoggerFactory.getLogger(ESASmogDataConverter.class);

    @Override
    public MeasurementStation convert(SmogData smogData) {
        final var measurementStation = measurementStationFrom(smogData.school());
        final var measurement = measurementsFrom(measurementStation, smogData.data());

        measurement.ifPresent(measurementStation::addMeasurement);
        return measurementStation;
    }

    private Optional<Measurement> measurementsFrom(MeasurementStation station, MeasurementData data) {
        if (data.isEmpty()) {
            LOG.warn("Skipping empty ESA measurement data!");
            return Optional.empty();
        }

        final var measurement = new Measurement();
        measurement.setId(randomUUID());
        measurement.setCreatedDate(now());
        measurement.setMeasurementStation(station);
        data.temperatureAvg().ifPresent(measurement::setTemperatureAvg);
        data.pressureAvg().ifPresent(measurement::setPressureAvg);
        data.pm25Avg().ifPresent(measurement::setPm25Avg);
        data.pm10Avg().ifPresent(measurement::setPm10Avg);
        data.humidityAvg().ifPresent(measurement::setHumidityAvg);
        return Optional.of(measurement);
    }

    private MeasurementStation measurementStationFrom(School school) {
        final var station = new MeasurementStation();
        station.setId(randomUUID());
        station.setCreatedDate(now());
        station.setUpdatedDate(now());
        station.setName(school.name());

        final var gpsLocation = new GPSLocation();
        gpsLocation.setLatitude(school.latitude());
        gpsLocation.setLongitude(school.longitude());
        station.setGpsLocation(gpsLocation);

        final var address = new Address();
        address.setCity(school.city());
        address.setPostcode(school.postcode());
        school.street().ifPresent(address::setStreet);
        station.setAddress(address);

        return station;
    }
}
