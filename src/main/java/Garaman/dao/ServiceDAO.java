package Garaman.dao;

import Garaman.model.ServiceStatistic;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ServiceDAO extends DAO {
    public ServiceDAO() {super();}

        public List<ServiceStatistic> getServiceRevenueList(Date startDate, Date endDate) {
        List<ServiceStatistic> list = new ArrayList<>();
        String sql = """
            SELECT s.id, s.name,
                   SUM(sr.quantity) AS totalUsage,                     
                   SUM(sr.quantity * s.price) AS totalRevenue          
            FROM tblService s
            JOIN tblServiceRequest sr ON s.id = sr.tblServiceId
            JOIN tblRequest r ON sr.tblRequestId = r.id
            JOIN tblInvoice i ON r.tblInvoiceId = i.id
            WHERE i.invoiceDate BETWEEN ? AND ?
            GROUP BY s.id, s.name
            ORDER BY totalRevenue DESC
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ServiceStatistic(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("totalUsage"),
                        rs.getFloat("totalRevenue")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
