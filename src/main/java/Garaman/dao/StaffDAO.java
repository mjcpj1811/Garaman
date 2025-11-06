package Garaman.dao;

import Garaman.model.Member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StaffDAO extends DAO {

    public StaffDAO() {
        super();
    }

    public  String getStaffPosition(int memberId) {
        String query = "SELECT position FROM tblstaff WHERE tblMemberId = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String pos = rs.getString("position");
                return pos;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
