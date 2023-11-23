package servlet;

import exception.AppException;
import exception.IncorrectPasswordException;
import model.Session;
import model.User;
import service.SessionService;
import service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.BCryptPassword;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = "/sign-in")
public class SignInServlet extends BaseServlet {
    private final UserService userService = new UserService();
    private final SessionService serviceSession = new SessionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.processTemplate("sign-in", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        BCryptPassword bCryptPassword = new BCryptPassword();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = userService.findByLogin(login);
            if(!bCryptPassword.correctPassword(password, user.getPassword())){
                throw new IncorrectPasswordException();
            }
            Session session = new Session();
            session.setUser(user);
            session.setExpiresAt(LocalDateTime.now().plusHours(1));
            serviceSession.save(session);
            Cookie cookie = new Cookie(serviceSession.findByUserIdSession(user.getId()).orElseThrow().getId(),
                    String.valueOf(user.getId()));
            cookie.setMaxAge(3600);
            httpSession.setAttribute("cookie", cookie);
            resp.addCookie(cookie);
            resp.sendRedirect("/home");
        }catch (AppException e){
            e.sendError(req, resp, e);
            super.processTemplate("sign-in", req, resp);
        }
    }
}
