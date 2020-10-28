package servlets;

import services.TableDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

@WebServlet("/list")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TableDAO tableDAO = new TableDAO();
        try {
            HashMap<String, String> map = tableDAO.makeTable();
            tableDAO.destroy();
            Set<String> set = map.keySet();
            req.getSession().setAttribute("keys", set);
            req.getSession().setAttribute("map", map);
            req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);

        }

    }
}
