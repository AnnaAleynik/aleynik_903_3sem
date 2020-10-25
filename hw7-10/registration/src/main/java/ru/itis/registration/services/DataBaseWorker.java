package ru.itis.registration.services;

import java.sql.*;

public class DataBaseWorker {

    private Connection conn;

    public DataBaseWorker() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "postgres");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Where is your JDBC Driver?");
            e.printStackTrace();
            this.conn = null;

            return;
        }

    }

    public void writeUser(String login, String password, String email) throws SQLException {
        String command = "INSERT INTO Users" + "(login, password, email) VALUES" +
                "('" + login + "', '" + password + "', " + "'" + email
                + "');";

        PreparedStatement st = conn.prepareStatement(command);
        st.executeUpdate();
    }

    public ResultSet getByEmail(String email) throws SQLException {
        String command = "SELECT * FROM Users WHERE email = '" + email + "'";

        PreparedStatement st = conn.prepareStatement(command);
        ResultSet set = st.executeQuery();
        return set;

    }
}
