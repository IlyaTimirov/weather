package exception.notfound;


import exception.AppException;
import exception.ErrorMessage;

public class UserNotFoundException extends AppException {
    public UserNotFoundException() {
        super(ErrorMessage.USER_NOT_FOUND);
    }
}
