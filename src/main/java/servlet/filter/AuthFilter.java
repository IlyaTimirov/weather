package servlet.filter;

import model.User;
import service.SessionService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        SessionService sessionService = new SessionService();
        Cookie cookie = (Cookie) session.getAttribute("cookie");
        boolean loggedIn = cookie != null
                && sessionService.findByIdSession(cookie.getName()).getExpiresAt().isAfter(LocalDateTime.now())
                && cookie.getName().equals(sessionService.findByIdSession(cookie.getName()).getId());
        if(loggedIn){
            UserService userService = new UserService();
            User user = userService.getById(Integer.parseInt(cookie.getValue()));
            req.setAttribute("name", user.getLogin());
            req.setAttribute("loggedIn", loggedIn);
        }
        if(loggedIn && (req.getRequestURI().equals("/sign-in") ||  req.getRequestURI().equals("/sign-up"))){
            res.sendRedirect("/home");
        }
        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
