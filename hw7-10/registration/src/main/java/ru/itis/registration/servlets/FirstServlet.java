package ru.itis.registration.servlets;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import ru.itis.registration.services.Validators;
import ru.itis.registration.services.DataBaseWorker;


@WebServlet("/signup")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/html/hello.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, String> map = Validators.signUpData(req);

        if (map.get("accept").equals("1")) {

            String name = map.get("login");
            String password = getHash(map.get("password"));
            String mail = map.get("email");

            /* CSV
            File file = new File("E:\\Programming\\studying\\Java\\3sem\\registration\\data\\file.csv");
            try (CSVPrinter printer = new CSVPrinter(new FileWriter(file), CSVFormat.EXCEL)) {
                printer.printRecord(name, mail, password);
                printer.println();
            } catch (IOException ex) {
                ex.printStackTrace();
            }*/

            DataBaseWorker db = new DataBaseWorker();
            try {
                db.writeUser(name, password, mail);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
//            System.out.println(map.get("help"));
        }

    }

    private String getHash(String password) {
//        todo
        return password;
    }

}
