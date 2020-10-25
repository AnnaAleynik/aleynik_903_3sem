package ru.itis.registration.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("")
public class Filter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String userAg = req.getHeader("User-Agent");
        System.out.println("filter: " + userAg);

        if (userAg.contains("Edg")) {
            resp.getWriter().print("OOPs..");
        } else {
            chain.doFilter(req, resp);
        }

    }
}
