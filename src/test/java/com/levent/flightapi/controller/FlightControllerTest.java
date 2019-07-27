package com.levent.flightapi.controller;

import com.levent.flightapi.Constant;
import com.levent.flightapi.model.FlightModel;
import com.levent.flightapi.service.FlightResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerTest {

    @MockBean
    private FlightResponse flightResponse;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllFlightByDeparture() throws Exception {
        getAllFlightByField(Constant.FIELDS.DEPARTURE);
    }

    @Test
    public void getAllFlightByArrival() throws Exception {
        getAllFlightByField(Constant.FIELDS.ARRIVAL);
    }

    @Test
    public void getAllFlightByDepartureTime() throws Exception {
        getAllFlightByField(Constant.FIELDS.DEPARTURE_TIME);
    }

    @Test
    public void getAllFlightByArrivalTime() throws Exception {
        getAllFlightByField(Constant.FIELDS.ARRIVAL_TIME);
    }

    @Test
    public void getAllFlightByOther() throws Exception {
        FlightController flightController = new FlightController(flightResponse);

        assertEquals(flightController.getAllFlightByField("data"), new ArrayList<>());
    }

    @Test
    public void searchDeparture() throws Exception {
        search(Constant.FIELDS.DEPARTURE, "istanbul");
    }

    @Test
    public void searchArrival() throws Exception {
        search(Constant.FIELDS.ARRIVAL, "Singapore");
    }

    @Test
    public void searchBoth() throws Exception {
        search(Constant.FIELDS.BOTH, "istanbul");
    }

    @Test
    public void searchOther() throws Exception {
        FlightController flightController = new FlightController(flightResponse);

        assertEquals(flightController.searchDeparture("abc", "data"), new ArrayList<>());
    }

    private void search(String field, String location) throws Exception {
        FlightController flightController = new FlightController(flightResponse);

        when(flightController.searchDeparture(field, location)).thenReturn(getFlightModelList());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flights/search/" + field + "/" + location).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        String expected = "[{\"departure\":\"Istanbul\",\"arrival\":\"Singapore\",\"departureTime\":12345678,\"arrivalTime\":12345789}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    private void getAllFlightByField(String field) throws Exception {
        FlightController flightController = new FlightController(flightResponse);

        when(flightController.getAllFlightByField(field)).thenReturn(getFlightModelList());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flights/" + field).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        String expected = "[{\"departure\":\"Istanbul\",\"arrival\":\"Singapore\",\"departureTime\":12345678,\"arrivalTime\":12345789}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    private List<FlightModel> getFlightModelList(){
        List<FlightModel> flightModelList = new ArrayList<>();
        FlightModel flightModel = new FlightModel();
        flightModel.setDeparture("Istanbul");
        flightModel.setArrival("Singapore");
        flightModel.setDepartureTime(12345678L);
        flightModel.setArrivalTime(12345789L);
        flightModelList.add(flightModel);
        return flightModelList;
    }
}