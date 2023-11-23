package servlet;

import exception.AppException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.AddForecastService;

import java.io.IOException;

@WebServlet(name = "/add")
public class AddForecastServlet extends BaseServlet{
    private final AddForecastService addForecastService = new AddForecastService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String city = req.getParameter("city");
        Cookie cookie = (Cookie) session.getAttribute("cookie");
        try {
            addForecastService.addForecast(city, Integer.parseInt(cookie.getValue()));
            resp.sendRedirect("/home");
        }catch (AppException e){
            e.sendError(req, resp, e);
            super.processTemplate("search", req, resp);
        }
    }
}
