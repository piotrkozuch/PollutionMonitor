package io.github.piotrkozuch.pm.controller;

import io.github.piotrkozuch.pm.repositorie.MeasurementStationsTestData;
import io.github.piotrkozuch.pm.service.MeasurementStationsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MeasurementStationsController.class)
class MeasurementStationsControllerTest implements MeasurementStationsTestData {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeasurementStationsService service;

    @Test
    void should_return_all_measurement_stations() throws Exception {
        // given
        var gpsLocation = aGpsLocation()
            .longitude(0.12)
            .longitude(0.11)
            .build();

        var station = aMeasurementStation()
            .gpsLocation(gpsLocation)
            .build();

        given(service.getAll()).willReturn(List.of(station));

        // when
        mockMvc.perform(get("/measurement-stations"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id", equalTo(station.getId().toString())))
            .andExpect(jsonPath("$[0].name", equalTo(station.getName())))
            .andExpect(jsonPath("$[0].address.city", equalTo(station.getAddress().getCity())))
            .andExpect(jsonPath("$[0].address.postcode", equalTo(station.getAddress().getPostcode())))
            .andExpect(jsonPath("$[0].address.street", equalTo(station.getAddress().getStreet())))
            .andExpect(jsonPath("$[0].gps_location.latitude", equalTo(station.getGpsLocation().getLatitude())))
            .andExpect(jsonPath("$[0].gps_location.longitude", equalTo(station.getGpsLocation().getLongitude())))
            .andExpect(jsonPath("$[0].created_date", equalTo(station.getCreatedDate().toString())))
            .andExpect(jsonPath("$[0].updated_date", equalTo(station.getUpdatedDate().toString())));
    }

    @Test
    void should_return_measurement_station_by_id() throws Exception {
        // given
        var gpsLocation = aGpsLocation()
            .longitude(0.12)
            .longitude(0.11)
            .build();

        var station = aMeasurementStation()
            .gpsLocation(gpsLocation)
            .build();

        given(service.getById(station.getId())).willReturn(station);

        // when
        mockMvc.perform(get("/measurement-stations/" + station.getId()))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", equalTo(station.getId().toString())))
            .andExpect(jsonPath("$.name", equalTo(station.getName())))
            .andExpect(jsonPath("$.address.city", equalTo(station.getAddress().getCity())))
            .andExpect(jsonPath("$.address.postcode", equalTo(station.getAddress().getPostcode())))
            .andExpect(jsonPath("$.address.street", equalTo(station.getAddress().getStreet())))
            .andExpect(jsonPath("$.gps_location.latitude", equalTo(station.getGpsLocation().getLatitude())))
            .andExpect(jsonPath("$.gps_location.longitude", equalTo(station.getGpsLocation().getLongitude())))
            .andExpect(jsonPath("$.created_date", equalTo(station.getCreatedDate().toString())))
            .andExpect(jsonPath("$.updated_date", equalTo(station.getUpdatedDate().toString())));
    }

    @Test
    void should_return_measurements_for_given_station() throws Exception {
        // given
        var gpsLocation = aGpsLocation()
            .longitude(0.12)
            .longitude(0.11)
            .build();

        var station = aMeasurementStation()
            .gpsLocation(gpsLocation)
            .build();

        var measurement = aMeasurementFor(station).build();

        var measurements = List.of(
            measurement
        );

        given(service.getMeasurements(station.getId())).willReturn(measurements);

        // when
        mockMvc.perform(get("/measurement-stations/" + station.getId() + "/measurements"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", equalTo(measurement.getId().toString())))
            .andExpect(jsonPath("$[0].humidity_avg", equalTo(measurement.getHumidityAvg())))
            .andExpect(jsonPath("$[0].pressure_avg", equalTo(measurement.getPressureAvg())))
            .andExpect(jsonPath("$[0].temperature_avg", equalTo(measurement.getTemperatureAvg())))
            .andExpect(jsonPath("$[0].pm10_avg", equalTo(measurement.getPm10Avg())))
            .andExpect(jsonPath("$[0].pm25_avg", equalTo(measurement.getPm25Avg())))
            .andExpect(jsonPath("$[0].created_date", equalTo(measurement.getCreatedDate().toString())))
            .andExpect(jsonPath("$[0].measurement_station_id", equalTo(station.getId().toString())));
    }
}