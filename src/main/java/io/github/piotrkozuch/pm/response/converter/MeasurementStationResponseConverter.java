package io.github.piotrkozuch.pm.response.converter;

import io.github.piotrkozuch.pm.model.Address;
import io.github.piotrkozuch.pm.model.GPSLocation;
import io.github.piotrkozuch.pm.model.MeasurementStation;
import io.github.piotrkozuch.pm.response.AddressResponse;
import io.github.piotrkozuch.pm.response.GPSLocationResponse;
import io.github.piotrkozuch.pm.response.MeasurementStationResponse;

import java.util.List;

import static io.github.piotrkozuch.pm.util.Checks.checkRequired;

public class MeasurementStationResponseConverter {

    public static List<MeasurementStationResponse> measurementStationsResponse(List<MeasurementStation> measurementStations) {
        return measurementStations.stream()
            .map(MeasurementStationResponseConverter::measurementStationResponse)
            .toList();
    }

    public static MeasurementStationResponse measurementStationResponse(MeasurementStation measurementStation) {
        checkRequired("measurementStation", measurementStation);

        return new MeasurementStationResponse(
            measurementStation.getId(),
            measurementStation.getName(),
            addressResponse(measurementStation.getAddress()),
            gpsLocation(measurementStation.getGpsLocation()),
            measurementStation.getCreatedDate(),
            measurementStation.getUpdatedDate()
        );
    }

    public static GPSLocationResponse gpsLocation(GPSLocation gpsLocation) {
        checkRequired("gpsLocation", gpsLocation);

        return new GPSLocationResponse(
            gpsLocation.getLatitude(),
            gpsLocation.getLongitude()
        );
    }

    private static AddressResponse addressResponse(Address address) {
        checkRequired("address", address);

        return new AddressResponse(
            address.getCity(),
            address.getPostcode(),
            address.getStreet()
        );
    }
}
