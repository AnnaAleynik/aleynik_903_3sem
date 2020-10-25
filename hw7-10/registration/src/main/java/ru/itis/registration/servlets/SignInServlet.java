package ru.itis.registration.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import ru.itis.registration.services.SecurityService;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/html/signIn.html").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(email != null && password != null){
            try {
                if(SecurityService.signIn(req, email, password)){
                    resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                    System.out.println("yes");
                    return;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        req.setAttribute("email", req.getParameter("email"));
        getServletContext().getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, resp);
    }
}
