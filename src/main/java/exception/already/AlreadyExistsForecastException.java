package exception.already;

import exception.AppException;
import exception.ErrorMessage;

public class AlreadyExistsForecastException extends AppException {
    public AlreadyExistsForecastException() {
        super(ErrorMessage.ALREADY_EXISTS_FORECAST);
    }
}
