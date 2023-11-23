package exception;

public class IncorrectPasswordException extends AppException {
    public IncorrectPasswordException() {
        super(ErrorMessage.INCORRECT_PASSWORD);
    }
}
