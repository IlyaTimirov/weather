package api.weather;

import api.weather.model.Forecast;
import dto.ForecastDto;
import exception.ForecastNotFoundException;
import model.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import util.Mapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ForecastService {
    private final static String URL_WEATHER_API = "https://api.openweathermap.org/data/2.5/weather?";
    private final static String WEATHER_KEY = "&appid=485a29903288f8395f3bd3a8ff4e943c&units=metric";
    private final Mapper mapper = new Mapper();
    private final ObjectMapper objectMapper = new ObjectMapper();
    public List<Forecast> findByAll(List<Location> locations) throws JsonProcessingException {
        List<Forecast> forecasts = new ArrayList<>();
        for(Location location: locations){
            String latitude = location.getLatitude().toString();
            String longitude = location.getLongitude().toString();
            Forecast forecast = convert(findByCoordinates(latitude, longitude));
            forecasts.add(forecast);
        }
        return forecasts;
    }

    private Forecast convert(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Forecast.class);
    }
    private String findByCoordinates(String latitude, String longitude) {
        return getForecastFromApi(URL_WEATHER_API + "lat="
                    + latitude + "&lon=" + longitude + WEATHER_KEY);
    }
    
    public Forecast findByName(String city) throws ForecastNotFoundException, JsonProcessingException {
        return convert(getForecastFromApi( URL_WEATHER_API + "q="
                + city + WEATHER_KEY));
    }

    private String getForecastFromApi(String ur) throws ForecastNotFoundException {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(ur);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();
            if(conn.getResponseCode() != 200){
                throw new RuntimeException();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                builder.append(output);
            }
            conn.disconnect();
        } catch (Exception e) {
            throw new ForecastNotFoundException();
        }
        return builder.toString();
    }
}
