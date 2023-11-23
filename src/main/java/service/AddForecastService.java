package service;

import api.weather.ForecastService;
import api.weather.model.Forecast;
import com.fasterxml.jackson.core.JsonProcessingException;
import model.Location;

public class AddForecastService {
    private final LocationService locationService = new LocationService();
    private final ForecastService forecastService = new ForecastService();

    public void addForecast(String city, int id) throws JsonProcessingException {
        Forecast forecast = forecastService.findByName(city);
        UserService userService = new UserService();
        Location location = new Location();
        location.setName(city);
        location.setUserId(userService.getById(id));
        location.setLongitude(forecast.getCoord().getLon());
        location.setLatitude(forecast.getCoord().getLat());
        locationService.save(location);
    }
}
