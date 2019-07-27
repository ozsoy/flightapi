package com.levent.flightapi;

import com.levent.flightapi.model.FlightModel;
import com.levent.flightapi.service.FlightResponse;
import com.levent.flightapi.util.FlightUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class FlightApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightApiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(FlightResponse flightResponse) {
        return args -> {
            // read json and write to System.out
            for (FlightModel flightModel: FlightUtil.getSortedListByDeparture(flightResponse.getFlights())) {
                System.out.println("Departure: " + flightModel.getDeparture() + " Arrival: " + flightModel.getArrival() + " Departure Time: " + new Date(flightModel.getDepartureTime()) + " Arrival Time: " + new Date(flightModel.getArrivalTime()));
            }
        };
    }
}
