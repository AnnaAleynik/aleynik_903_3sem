package ru.itis.registration.services;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SecurityService {

    public static Map<String, Object> getUser(HttpServletRequest req) {
        if(isSigned(req)){
            Map<String, Object> user = new HashMap<>();
            user.put("email", req.getSession().getAttribute("email"));
            return user;
        }
        return null;
    }

    public static boolean isSigned(HttpServletRequest req) {

        return req.getSession().getAttribute("email") != null;
    }

    public static boolean signIn(HttpServletRequest req, String email, String password) throws SQLException {

        DataBaseWorker db = new DataBaseWorker();
        ResultSet set = db.getByEmail(email);
        while (set.next()) {

            String dEmail = set.getString("email");
            String dPassword = set.getString("password");
            if (email.equals(dEmail) && password.equals(dPassword)) {
                req.getSession().setAttribute("email", email);
                return true;
            }
        }

        return false;
    }
}
