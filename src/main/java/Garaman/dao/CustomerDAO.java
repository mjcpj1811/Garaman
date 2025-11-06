package Garaman.dao;

import Garaman.model.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO extends DAO{
    public  CustomerDAO() {
        super();
    }
    public boolean updateProfile(Customer m) {
        String sql = """
            UPDATE tblMember
            SET name=?, address=?, email=?, phoneNumber=?
            WHERE id=?
        """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getName());
            ps.setString(2, m.getAddress());
            ps.setString(3, m.getEmail());
            ps.setString(4, m.getPhoneNumber());
            ps.setInt(5, m.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
