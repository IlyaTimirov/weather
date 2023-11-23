package util;

import dto.ForecastDto;
import api.weather.model.Forecast;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public ForecastDto toForecastDto(Forecast forecast){
        ForecastDto forecastDto = new ForecastDto();
        forecastDto.setCity(forecast.getName());
        forecastDto.setCountry(forecast.getSys().getCountry());
        forecastDto.setTemp(forecast.getMain().getTemp());
        forecastDto.setTempMin(forecast.getMain().getTemp_min());
        forecastDto.setTempMax(forecast.getMain().getTemp_max());
        return forecastDto;
    }

    public List<ForecastDto> toListForecastDto(List<Forecast> forecasts){
        List<ForecastDto> forecastsDto = new ArrayList<>();
        for(Forecast forecast: forecasts){
            forecastsDto.add(toForecastDto(forecast));
        }
        return forecastsDto;
    }
}
