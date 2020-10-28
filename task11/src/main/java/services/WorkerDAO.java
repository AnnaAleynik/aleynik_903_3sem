package services;

import entities.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.function.Predicate;

public class WorkerDAO {
    private Connection conn;

    public WorkerDAO() {
        this.conn = DBWorker.getConn();
    }

    public void destroy() throws SQLException {
        conn.close();
    }

    public LinkedList<Worker> getWorkersByBranch(int id) throws SQLException {
        LinkedList<Worker> workers = new LinkedList<>();

        String command = "SELECT w.id, w.name, w.mail FROM workers w where branch_id = ?";
        PreparedStatement st = conn.prepareStatement(command);
        st.setInt(1, id);
        ResultSet set = st.executeQuery();

        Worker worker;
        while (set.next()) {
            worker = new Worker(
                    set.getInt("id"),
                    set.getString("name"),
                    set.getString("mail")
            );
            workers.add(worker);
        }
        return workers;
    }
}
