package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Location;
import service.LocationService;

import java.io.IOException;

@WebServlet("/remove")
public class RemoveForecastServlet extends BaseServlet{
    private final LocationService locationService = new LocationService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String city = req.getParameter("city");
        Cookie cookie = (Cookie) session.getAttribute("cookie");
        Location location = locationService
                .findByCityAndUserId(city, Integer.parseInt(cookie.getValue()));
        locationService.remove(location);
        resp.sendRedirect("/home");
    }

}
