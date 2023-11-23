package servlet;

import api.weather.ForecastService;
import dto.ForecastDto;
import exception.AppException;
import service.AddForecastService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Mapper;

import java.io.IOException;

@WebServlet(urlPatterns = "/search")
public class SearchForecastServlet extends BaseServlet{
    private final ForecastService forecastService = new ForecastService();
    private final Mapper mapper = new Mapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("city");
        try {
            ForecastDto forecast = mapper.toForecastDto(forecastService.findByName(name));
            req.setAttribute("forecast", forecast);
            resp.setStatus(HttpServletResponse.SC_OK);
            super.processTemplate("search", req, resp);
        }catch (AppException e){
            e.sendError(req, resp, e);
            super.processTemplate("search", req, resp);
        }
    }
}
