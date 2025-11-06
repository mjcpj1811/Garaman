package Garaman.dao;

import Garaman.model.SparePartStatistic;

import java.sql.*;
import java.util.*;
import java.sql.Date;

public class SparePartDAO extends DAO{
    public SparePartDAO() {super();}

    public List<SparePartStatistic> getSparePartRevenueList(Date startDate, Date endDate) {
        List<SparePartStatistic> list = new ArrayList<>();
        String sql = """
            SELECT sp.id, sp.name,
                   SUM(spr.quantity) AS totalUsage,                     
                   SUM(spr.quantity * sp.sellingPrice) AS totalRevenue  
            FROM tblSparePart sp
            JOIN tblSparePartRequest spr ON sp.id = spr.tblSparePartId
            JOIN tblRequest r ON spr.tblRequestId = r.id
            JOIN tblInvoice i ON r.tblInvoiceId = i.id
            WHERE i.invoiceDate BETWEEN ? AND ?
            GROUP BY sp.id, sp.name
            ORDER BY totalRevenue DESC
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SparePartStatistic(
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
