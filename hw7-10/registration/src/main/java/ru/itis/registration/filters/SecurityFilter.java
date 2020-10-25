package ru.itis.registration.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import ru.itis.registration.services.SecurityService;

@WebFilter("/*")
public class SecurityFilter  extends HttpFilter {

    protected final String[] protectedPaths = {
            "/profile"
    };

//  Запомниьт с какой страницы запрос чтобы не всегда уводить в профиль annoying

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean prot= false;
        for(String path : protectedPaths){
            if(path.equals(req.getRequestURI().substring(req.getContextPath().length()))){
                prot = true;
                break;
            }
        }
        if(prot && !SecurityService.isSigned(req)){
            res.sendRedirect(req.getContextPath() + "/");
        }
        else{
            if(SecurityService.isSigned(req)){
                req.setAttribute("user", SecurityService.getUser(req));
            }
            chain.doFilter(req, res);
        }
    }
}
