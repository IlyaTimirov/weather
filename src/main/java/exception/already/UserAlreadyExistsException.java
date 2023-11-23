package exception.already;

import exception.AppException;
import exception.ErrorMessage;

public class UserAlreadyExistsException extends AppException {

    public UserAlreadyExistsException() {
        super(ErrorMessage.USER_NOT_FOUND);
    }
}
