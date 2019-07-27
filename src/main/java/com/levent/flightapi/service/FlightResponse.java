package com.levent.flightapi.service;

import com.levent.flightapi.model.FlightModel;

import java.util.List;

public interface FlightResponse {
    List<FlightModel> getFlights() throws Exception;

    List<FlightModel> searchFlights(String type, String location) throws Exception;
}
