package com.levent.flightapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levent.flightapi.Constant;
import com.levent.flightapi.model.FlightModel;
import com.levent.flightapi.model.FlightModelData;
import com.levent.flightapi.util.FlightUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
public class FlightResponseImpl implements FlightResponse {

    @Override
    public List<FlightModel> getFlights() throws Exception {
        List<FlightModel> flightModelList = new ArrayList<>();
        flightModelList.addAll(getCheapFlights().getData());
        flightModelList.addAll(getBusinessFlights().getData());
        return flightModelList;
    }

    @Override
    public List<FlightModel> searchFlights(String type, String location) throws Exception {
        List<FlightModel> flightModelList = new ArrayList<>();
        for( FlightModel flightModel: getFlights()){
            if (Constant.FIELDS.DEPARTURE.equals(type) ) {
                if ( location.compareToIgnoreCase(flightModel.getDeparture()) == 0 ) {
                    flightModelList.add(flightModel);
                }
            } else if (Constant.FIELDS.ARRIVAL.equals(type) ) {
                if ( location.compareToIgnoreCase(flightModel.getArrival()) == 0 ) {
                    flightModelList.add(flightModel);
                }
            } else if (Constant.FIELDS.BOTH.equals(type) ) {
                if ( location.compareToIgnoreCase(flightModel.getDeparture()) == 0 || location.compareToIgnoreCase(flightModel.getArrival()) == 0 ) {
                    flightModelList.add(flightModel);
                }
            }

        }
        return flightModelList;
    }

    private FlightModelData getCheapFlights() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        String jsonString = FlightUtil.readUrl(Constant.URL.cheap);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonOb = jsonArray.getJSONObject(i);
            jsonOb.put("departureTime", jsonOb.remove("departure"));
            jsonOb.put("arrivalTime", jsonOb.remove("arrival"));

            String departure = jsonOb.get("route").toString().split("-")[0];
            String arrival = jsonOb.get("route").toString().split("-")[1];
            jsonOb.remove("route");
            jsonOb.put("departure", departure);
            jsonOb.put("arrival", arrival);
        }

        return objectMapper.readValue(jsonObject.toString(), new TypeReference<FlightModelData>(){});
    }

    private FlightModelData getBusinessFlights() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper.readValue(new URL(Constant.URL.business), new TypeReference<FlightModelData>(){});
    }
}
