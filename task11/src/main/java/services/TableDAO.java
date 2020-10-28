package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class TableDAO {

    private Connection conn;

    public TableDAO() {
        this.conn = DBWorker.getConn();
    }

    public void destroy() throws SQLException {
        conn.close();
    }

    public HashMap<String, String> makeTable() throws SQLException {
        String command = "SELECT bo.address, w.name FROM branch_office bo join workers w ON bo.id = w.branch_id";

        PreparedStatement st = conn.prepareStatement(command);
        ResultSet set = st.executeQuery();

        HashMap<String, String> map = new HashMap<>();

        while (set.next()) {
            String address = set.getString("address");
            String name = set.getString("name");
            map.put(name, address);
        }
        return map;
    }
}
