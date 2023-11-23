package servlet;

import java.io.IOException;
import java.util.List;

import api.weather.model.Forecast;
import dto.ForecastDto;
import service.LocationService;
import api.weather.ForecastService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.Mapper;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends BaseServlet {
    private final LocationService locationService = new LocationService();
    private final ForecastService forecastService = new ForecastService();
    private final Mapper mapper = new Mapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cookie cookie = (Cookie) session.getAttribute("cookie");
        if(cookie != null) {
            List<Forecast> forecasts = forecastService.findByAll(locationService
                    .findUserId(Integer.parseInt(cookie.getValue())));
            List<ForecastDto> forecastsDto = mapper.toListForecastDto(forecasts);
            req.setAttribute("forecasts", forecastsDto);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        super.processTemplate("home", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String city = req.getParameter("city");
        Cookie cookie = (Cookie) session.getAttribute("cookie");
        locationService
                .remove(locationService.findByCityAndUserId(city, Integer.parseInt(cookie.getValue())));
        resp.sendRedirect("/home");
    }
}
