package io.github.piotrkozuch.pm.repositories;

import io.github.piotrkozuch.pm.models.Address;
import io.github.piotrkozuch.pm.models.GPSLocation;
import io.github.piotrkozuch.pm.models.Measurement;
import io.github.piotrkozuch.pm.models.MeasurementStation;

import java.util.Random;

import static io.github.piotrkozuch.pm.models.Measurement.MeasurementBuilder.aMeasurement;
import static java.time.Instant.now;
import static java.util.UUID.randomUUID;

public interface MeasurementStationsTestData {

    default MeasurementStation.MeasurementStationBuilder aMeasurementStation() {
        final var measurementStation = MeasurementStation.MeasurementStationBuilder.aMeasurementStation()
            .id(randomUUID())
            .name("Szkoła podstawowa nr 1 imienia KEN")
            .address(anAddress().build())
            .gpsLocation(aGpsLocation().build())
            .createdDate(now())
            .updatedDate(now());

        return measurementStation;
    }

    default Address.AddressBuilder anAddress() {
        final var builder = Address.AddressBuilder.anAddress()
            .city("Kraków")
            .postcode("30-392")
            .street("Dr Jana Piltza 31");

        return builder;
    }

    default GPSLocation.GPSLocationBuilder aGpsLocation() {
        final var random = new Random();
        final var builder = GPSLocation.GPSLocationBuilder.aGPSLocation()
            .latitude(random.nextDouble())
            .longitude(random.nextDouble());

        return builder;
    }

    default Measurement.MeasurementBuilder aMeasurementFor(MeasurementStation measurementStation) {
        final var random = new Random();
        final var measurement = aMeasurement()
            .id(randomUUID())
            .measurementStation(measurementStation)
            .createdDate(now())
            .humidityAvg(random.nextDouble())
            .pm10Avg(random.nextDouble())
            .pm25Avg(random.nextDouble())
            .pressureAvg(random.nextDouble())
            .temperatureAvg(random.nextDouble());

        return measurement;
    }
}
