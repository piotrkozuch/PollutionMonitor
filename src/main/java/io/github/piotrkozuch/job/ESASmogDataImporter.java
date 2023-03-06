package io.github.piotrkozuch.job;

import io.github.piotrkozuch.db.MeasurementStationsRepository;
import io.github.piotrkozuch.db.model.Address;
import io.github.piotrkozuch.db.model.GPSLocation;
import io.github.piotrkozuch.db.model.Measurement;
import io.github.piotrkozuch.db.model.MeasurementStation;
import io.github.piotrkozuch.integration.esa.ESAClient;
import io.github.piotrkozuch.integration.esa.response.MeasurementData;
import io.github.piotrkozuch.integration.esa.response.School;
import io.github.piotrkozuch.integration.esa.response.SmogDataPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

import static io.github.piotrkozuch.utils.Checks.checkRequired;
import static java.time.Instant.now;
import static java.util.UUID.randomUUID;

@Component
public class ESASmogDataImporter {

    private final ESAClient esaClient;
    private final MeasurementStationsRepository repository;

    @Autowired
    public ESASmogDataImporter(ESAClient esaClient, MeasurementStationsRepository repository) {
        this.esaClient = checkRequired("esaClient", esaClient);
        this.repository = checkRequired("repository", repository);
    }

    public void importSmogData() {
        iterateOverAllSmogDataPages(smogDataPage ->
            smogDataPage.smogData().forEach(smogData -> {
                final var measurementStation = measurementStationFrom(smogData.school());
                final var measurement = measurementsFrom(measurementStation, smogData.data());

                measurementStation.addMeasurement(measurement);
                save(measurementStation);
            })
        );
    }

    @Transactional
    private void save(MeasurementStation measurementStation) {
        repository.save(measurementStation);
    }

    private Measurement measurementsFrom(MeasurementStation station, MeasurementData data) {
        final var measurement = new Measurement();
        measurement.setId(randomUUID());
        measurement.setCreatedDate(now());
        measurement.setMeasurementStation(station);
        measurement.setTemperatureAvg(data.temperatureAvg());
        measurement.setPressureAvg(data.pressureAvg());
        measurement.setPm25Avg(data.pm25Avg());
        measurement.setPm10Avg(data.pm10Avg());
        measurement.setHumidityAvg(data.humidityAvg());
        return measurement;
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

    private void iterateOverAllSmogDataPages(Consumer<SmogDataPage> smogDataPageConsumer) {
        var pageNumber = -1;
        while (true) {
            final var smogDataPage = esaClient.getSmogDataPage(++pageNumber);

            smogDataPageConsumer.accept(smogDataPage);

            if (!smogDataPage.hasNextPage()) {
                break;
            }
        }
    }
}
