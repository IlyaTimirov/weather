package dto;

import lombok.Data;

@Data
public class ForecastDto {
    private String city;
    private String country;
    private double temp;
    private double tempMin;
    private double tempMax;
}
