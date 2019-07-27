package com.levent.flightapi.util;

import com.levent.flightapi.model.FlightModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlightUtil {
    public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder builder = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                builder.append(chars, 0, read);
            }

            return builder.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public static List<FlightModel> getSortedListByDeparture(List<FlightModel> flightModelList){
        return flightModelList.stream()
                .sorted(Comparator.comparing(FlightModel::getDeparture))
                .collect(Collectors.toList());
    }

    public static List<FlightModel> getSortedListByArrival(List<FlightModel> flightModelList) {
        return flightModelList.stream()
                .sorted(Comparator.comparing(FlightModel::getArrival))
                .collect(Collectors.toList());
    }

    public static List<FlightModel> getSortedListByDepartureTime(List<FlightModel> flightModelList) {
        return flightModelList.stream()
                .sorted(Comparator.comparing(FlightModel::getDepartureTime))
                .collect(Collectors.toList());
    }

    public static List<FlightModel> getSortedListByArrivalTime(List<FlightModel> flightModelList) {
        return flightModelList.stream()
                .sorted(Comparator.comparing(FlightModel::getArrivalTime))
                .collect(Collectors.toList());
    }
}
