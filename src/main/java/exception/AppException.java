package exception;


public class AppException extends BaseException{
    ErrorMessage message;
    public AppException(ErrorMessage message){
        this.message = message;
    }
}
