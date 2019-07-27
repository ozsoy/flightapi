package com.levent.flightapi.controller;

import com.levent.flightapi.Constant;
import com.levent.flightapi.model.FlightModel;
import com.levent.flightapi.service.FlightResponse;
import com.levent.flightapi.util.FlightUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/flights")
public class FlightController {

    private final FlightResponse flightService;

    public FlightController(FlightResponse flightService) {
        this.flightService = flightService;
    }

    @GetMapping(path = "/{field}", produces = "application/json")
    public List<FlightModel> getAllFlightByField(@PathVariable String field) throws Exception {
        if (Constant.FIELDS.DEPARTURE.equals(field) ) {
            return FlightUtil.getSortedListByDeparture(flightService.getFlights());
        } else if (Constant.FIELDS.ARRIVAL.equals(field) ) {
            return FlightUtil.getSortedListByArrival(flightService.getFlights());
        } else if (Constant.FIELDS.DEPARTURE_TIME.equals(field) ) {
            return FlightUtil.getSortedListByDepartureTime(flightService.getFlights());
        } else if (Constant.FIELDS.ARRIVAL_TIME.equals(field) ) {
            return FlightUtil.getSortedListByArrivalTime(flightService.getFlights());
        } else {
            return new ArrayList<>();
        }
    }

    @GetMapping(path = "/search/{type}/{location}", produces = "application/json")
    public List<FlightModel> searchDeparture(@PathVariable String type, @PathVariable String location) throws Exception {
        if (Constant.FIELDS.DEPARTURE.equals(type) ) {
            return FlightUtil.getSortedListByDeparture(flightService.searchFlights(type, location));
        } else if (Constant.FIELDS.ARRIVAL.equals(type) ) {
            return FlightUtil.getSortedListByArrival(flightService.searchFlights(type, location));
        } else if (Constant.FIELDS.BOTH.equals(type) ) {
            return FlightUtil.getSortedListByDeparture(flightService.searchFlights(type, location));
        } else {
            return new ArrayList<>();
        }
    }
}
