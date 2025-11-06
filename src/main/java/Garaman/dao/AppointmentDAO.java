package Garaman.dao;

import Garaman.model.Appointment;
import Garaman.model.Customer;
import java.sql.*;

public class AppointmentDAO extends DAO {

    public AppointmentDAO() { super(); }

    public boolean saveAppointment(Appointment appointment, Customer customer) {
        String check = """
            SELECT COUNT(*) FROM tblAppointment
            WHERE tblCustomerId=? AND appointmentDate=? AND appointmentTime=?;
        """;

        String insert = """
            INSERT INTO tblAppointment(appointmentDate, appointmentTime, status, tblCustomerId)
            VALUES (?,?,?,?)
        """;

        try {
            try (PreparedStatement ps = con.prepareStatement(check)) {
                ps.setInt(1, customer.getId());
                ps.setDate(2, appointment.getAppointmentDate());
                ps.setTime(3, appointment.getAppointmentTime());
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Duplicate appointment detected");
                    return false;
                }
            }

            try (PreparedStatement stmt = con.prepareStatement(insert)) {
                stmt.setDate(1, appointment.getAppointmentDate());
                stmt.setTime(2, appointment.getAppointmentTime());
                stmt.setString(3, appointment.getStatus());
                stmt.setInt(4, customer.getId());
                return stmt.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
