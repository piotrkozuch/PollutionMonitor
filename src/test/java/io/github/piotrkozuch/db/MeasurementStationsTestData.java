package io.github.piotrkozuch.db;

import io.github.piotrkozuch.db.model.Address;
import io.github.piotrkozuch.db.model.GPSLocation;
import io.github.piotrkozuch.db.model.Measurement;
import io.github.piotrkozuch.db.model.MeasurementStation;

import java.util.Random;
import java.util.UUID;

import static java.time.Instant.now;
import static java.util.UUID.randomUUID;

public interface MeasurementStationsTestData {

    default MeasurementStation aMeasurementStation() {
        final var measurementStation = new MeasurementStation();

        measurementStation.setId(randomUUID());
        measurementStation.setName("Szkoła podstawowa nr 1 imienia KEN");
        measurementStation.setAddress(anAddress());
        measurementStation.setGpsLocation(aGpsLocation());
        measurementStation.setCreatedDate(now());
        measurementStation.setUpdatedDate(now());

        return measurementStation;
    }

    default Address anAddress() {
        final var address = new Address();

        address.setCity("Kraków");
        address.setPostcode("30-392");
        address.setStreet("Dr Jana Piltza 31");

        return address;
    }

    default GPSLocation aGpsLocation() {
        final var random = new Random();
        final var gpsLocation = new GPSLocation();

        gpsLocation.setLatitude(random.nextDouble());
        gpsLocation.setLongitude(random.nextDouble());

        return gpsLocation;
    }

    default Measurement aMeasurementFor(MeasurementStation measurementStation){
        final var random = new Random();
        final var measurement = new Measurement();

        measurement.setId(randomUUID());
        measurement.setMeasurementStation(measurementStation);
        measurement.setCreatedDate(now());
        measurement.setHumidityAvg(random.nextDouble());
        measurement.setPm10Avg(random.nextDouble());
        measurement.setPm25Avg(random.nextDouble());
        measurement.setPressureAvg(random.nextDouble());
        measurement.setTemperatureAvg(random.nextDouble());

        return measurement;
    }
}
