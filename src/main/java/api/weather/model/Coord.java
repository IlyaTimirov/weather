package api.weather.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Coord {
    private BigDecimal lon;
    private BigDecimal lat;
}
