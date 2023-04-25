package io.github.piotrkozuch.pm.repositorie;

import io.github.piotrkozuch.pm.model.Address;
import io.github.piotrkozuch.pm.model.GPSLocation;
import io.github.piotrkozuch.pm.model.Measurement;
import io.github.piotrkozuch.pm.model.MeasurementStation;

import java.util.Random;

import static io.github.piotrkozuch.pm.model.Measurement.MeasurementBuilder.aMeasurement;
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
        final var measurement = aMeasurement()
            .id(randomUUID())
            .measurementStation(measurementStation)
            .createdDate(now())
            .humidityAvg(10.0)
            .pm10Avg(13.234)
            .pm25Avg(15.888)
            .pressureAvg(900.230)
            .temperatureAvg(24.32);

        return measurement;
    }
}
