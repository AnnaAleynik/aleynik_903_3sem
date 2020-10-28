package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {

    protected Connection conn;
    protected static Connection connection;
    private static final String DBNAME = "magnit";

    public static Connection getConn() {
        String url = "jdbc:postgresql://localhost:5432/" + DBNAME;
        String user = "postgres";
        String password = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            connection = null;
            System.out.println("Where is your JDBC Driver?");
            e.printStackTrace();
        }
        return connection;
    }

    public DBWorker() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DBNAME,"postgres", "postgres");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Where is your JDBC Driver?");
            e.printStackTrace();
            this.conn = null;

            return;
        }

    }
}
