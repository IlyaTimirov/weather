package exception;

import jakarta.servlet.http.HttpServletResponse;

public enum ErrorMessage {
    USER_NOT_FOUND("errorUser", "User not found!", HttpServletResponse.SC_NOT_FOUND),
    INCORRECT_PASSWORD("errorPassword", "Incorrect password!", HttpServletResponse.SC_UNAUTHORIZED),
    ALREADY_EXISTS_USER("alreadyExists", "A user with this login already exists", HttpServletResponse.SC_CONFLICT),
    NOT_FOUND_FORECAST("errorForecast", "Forecast not found!", HttpServletResponse.SC_NOT_FOUND),
    ALREADY_EXISTS_FORECAST("errorAlreadyForecast", "The forecast has already been added", HttpServletResponse.SC_CONFLICT);
    private final String errorObject;
    private final String errorMessage;
    private final int code;

    ErrorMessage(String errorObject, String errorMessage, int code) {
        this.errorObject = errorObject;
        this.errorMessage = errorMessage;
        this.code = code;
    }

    public String getErrorObject() {
        return errorObject;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getCode() {
        return code;
    }
}

