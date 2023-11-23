package exception.notfound;

import exception.AppException;
import exception.ErrorMessage;

public class ForecastNotFoundException extends AppException {
    public ForecastNotFoundException() {
        super(ErrorMessage.NOT_FOUND_FORECAST);
    }
}
