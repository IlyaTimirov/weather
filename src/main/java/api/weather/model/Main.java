package api.weather.model;

import lombok.Data;
import org.json.JSONPropertyName;

@Data
public class Main {
    private double temp;
    private double temp_min;
    private double grnd_level;
    private double humidity;
    private int pressure;
    private int sea_level;
    private double feels_like;
    private double temp_max;
}
