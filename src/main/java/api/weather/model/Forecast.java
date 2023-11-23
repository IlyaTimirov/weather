package api.weather.model;

import lombok.Data;

@Data
public class Forecast {
    private int visibility;
    private int timezone;
    private Main main;
    private Clouds clouds;
    private Sys sys;
    private int dt;
    private Coord coord;
    private Weather[] weather;
    private String name;
    private int cod;
    private int id;
    private String base;
    private Wind wind;
    private Rain rain;
    private Snow snow;
}
