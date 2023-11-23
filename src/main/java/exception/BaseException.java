package exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BaseException extends RuntimeException{

    public void sendError(HttpServletRequest req, HttpServletResponse resp, AppException e){
        req.setAttribute(e.message.getErrorObject(), e.message.getErrorMessage());
        resp.setStatus(e.message.getCode());
    }
}
