package io.github.piotrkozuch.controller;

import io.github.piotrkozuch.db.MeasurementStationsTestData;
import io.github.piotrkozuch.db.model.GPSLocation;
import io.github.piotrkozuch.service.MeasurementStationsService;
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
        var gpsLocation = new GPSLocation();
        gpsLocation.setLongitude(0.12);
        gpsLocation.setLongitude(0.11);

        var station = aMeasurementStation();
        station.setGpsLocation(gpsLocation);
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
            .andExpect(jsonPath("$[0].gpsLocation.latitude", equalTo(station.getGpsLocation().getLatitude())))
            .andExpect(jsonPath("$[0].gpsLocation.longitude", equalTo(station.getGpsLocation().getLongitude())))
            .andExpect(jsonPath("$[0].createdDate", equalTo(station.getCreatedDate().toString())))
            .andExpect(jsonPath("$[0].updatedDate", equalTo(station.getUpdatedDate().toString())))
            .andExpect(jsonPath("$[0].measurements", hasSize(0)));
    }

    @Test
    void should_return_measurement_stations_by_id() throws Exception {
        // given
        var gpsLocation = new GPSLocation();
        gpsLocation.setLongitude(0.12);
        gpsLocation.setLongitude(0.11);

        var station = aMeasurementStation();
        station.setGpsLocation(gpsLocation);
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
            .andExpect(jsonPath("$.gpsLocation.latitude", equalTo(station.getGpsLocation().getLatitude())))
            .andExpect(jsonPath("$.gpsLocation.longitude", equalTo(station.getGpsLocation().getLongitude())))
            .andExpect(jsonPath("$.createdDate", equalTo(station.getCreatedDate().toString())))
            .andExpect(jsonPath("$.updatedDate", equalTo(station.getUpdatedDate().toString())))
            .andExpect(jsonPath("$.measurements", hasSize(0)));
    }
}