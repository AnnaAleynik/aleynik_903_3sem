package servlets;

import entities.Branch;
import services.BranchDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

@WebServlet("/workers")
public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BranchDAO branchDAO = new BranchDAO();
            LinkedList<Branch> list = branchDAO.getAllBranches();

            req.getSession().setAttribute("list", list);
            req.getRequestDispatcher("/WEB-INF/views/worker.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);

        }
    }
}
