package api.weather.model;

import lombok.Data;

@Data
public class Weather {
    private String icon;
    private String description;
    private String main;
    private int id;
}
