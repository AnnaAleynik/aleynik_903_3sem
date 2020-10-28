package services;

import entities.Branch;
import entities.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class BranchDAO {
    private Connection conn;

    public BranchDAO() {
        this.conn = DBWorker.getConn();
    }

    public void destroy() throws SQLException {
        conn.close();
    }

    public LinkedList<Branch> getAllBranches() throws SQLException {
        WorkerDAO workerDAO = new WorkerDAO();

        LinkedList<Branch> list = new LinkedList<>();
        String command = "SELECT id, address FROM branch_office";
        PreparedStatement st = conn.prepareStatement(command);
        ResultSet set = st.executeQuery();
        Branch branch;
        while (set.next()) {
            int id = set.getInt("id");
            branch = new Branch(
                    id,
                    set.getString("address"),
                    workerDAO.getWorkersByBranch(id)
            );

            list.add(branch);
        }
        workerDAO.destroy()  ;
        return list;
    }


}
