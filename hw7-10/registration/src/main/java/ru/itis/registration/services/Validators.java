package ru.itis.registration.services;

import javax.servlet.http.HttpServletRequest;
import java.security.Security;
import java.util.HashMap;

public class Validators {

    public static HashMap<String, String> signUpData(HttpServletRequest req) {

        String name = req.getParameter("login");
        boolean nameB = true;
        boolean passB = true;
        boolean mailB = true;
        boolean acceptB = true;

        if (!name.matches("[A-Za-z0-9_.]*")) {
            name = "null";
            nameB = false;
            System.out.println("AAAAAAAAAAAA");
        }

        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");

        if (!password.equals(password2)) {
            password = "null";
            passB = false;
        }

        String mail = req.getParameter("mail");
        if (!mail.matches("[a-zA-Z\\-0-9\\.]+@([a-zA-Z\\-0-9]+\\.([a-z\\-])+)+")) {
            mail = "null";
            mailB = false;
            System.out.println("MAAAAAAAAAAAAil");
        }

        String accept = req.getParameter("accept");
//        System.out.println(accept);

        if (accept.equals("on")) {
            acceptB = false;
            System.out.println(accept);

        }

        HashMap<String, String> map = new HashMap<>();
        if (nameB && passB && mailB) {
            map.put("accept", "1");
            map.put("login", name);
            map.put("password", password);
            map.put("email", mail);
            System.out.println(name + " " + password + " " + mail);
        } else {
            map.put("accept", "0");
            map.put("help", accept);
        }

        return map;
    }
}
