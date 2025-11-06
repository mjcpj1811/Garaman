package Garaman.dao;

import Garaman.model.Member;
import java.sql.*;

public class MemberDAO extends DAO {

    public MemberDAO() { super(); }

    public Member login(String username, String password) {
        String query = "SELECT * FROM tblMember WHERE username = ? AND password = ?";
        Member member = null;

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    member = new Member();
                    member.setId(rs.getInt("id"));
                    member.setName(rs.getString("name"));
                    member.setUsername(rs.getString("username"));
                    member.setPassword(rs.getString("password"));
                    member.setBirthday(rs.getDate("birthday"));
                    member.setAddress(rs.getString("address"));
                    member.setEmail(rs.getString("email"));
                    member.setPhoneNumber(rs.getString("phoneNumber"));
                    member.setRole(rs.getString("role"));
                    member.setGender(rs.getString("gender"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }


}
