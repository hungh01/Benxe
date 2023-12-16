package com.example.Tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.example.BenXe.Model.DiaDiem;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class toolsMap {
    public DiaDiem getLocation(String tinh, String huyen, String xa, String sonha){
        DiaDiem result = new DiaDiem();
        try {
            String accessToken = "pk.eyJ1IjoiZWFsZmxtIiwiYSI6ImNsNmEyYTl0NTBjeXkzanFmajV1ZmFkcXEifQ.ajO7MarYAHOccS4yrY4cPg";
            String address = "82a đường số 48 nhánh 3, Hiệp Bình Chánh, Thủ Đức, Hồ Chí Minh";

            String encodedAddress = URLEncoder.encode(address, "UTF-8");
            String apiUrl = "https://api.mapbox.com/geocoding/v5/mapbox.places/" + encodedAddress + ".json?access_token=" + accessToken;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Optional: Set request method to GET
            connection.setRequestMethod("GET");

            // Get the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.toString());

            // Extract latitude and longitude
            JsonNode firstFeature = jsonNode.path("features").path(0);
            String latitude = firstFeature.path("center").path(1).asText();
            String longitude = firstFeature.path("center").path(0).asText();
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
