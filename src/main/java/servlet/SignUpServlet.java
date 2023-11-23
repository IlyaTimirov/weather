package servlet;

import exception.AppException;
import model.User;
import service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.BCryptPassword;

import java.io.IOException;

@WebServlet(urlPatterns = "/sign-up")
public class SignUpServlet extends BaseServlet {
    private final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.processTemplate("sign-up", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BCryptPassword bCryptPassword = new BCryptPassword();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = new User();
            user.setLogin(login);
            user.setPassword(bCryptPassword.getBCryptPassword(password));
            userService.save(user);
            resp.sendRedirect("/sign-in");
        }catch (AppException e){
            e.sendError(req, resp, e);
            super.processTemplate("sign-up", req, resp);
        }

    }
}
