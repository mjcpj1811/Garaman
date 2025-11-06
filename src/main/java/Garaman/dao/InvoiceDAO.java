package Garaman.dao;

import Garaman.model.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class InvoiceDAO extends DAO {

    public InvoiceDAO() { super(); }

    // ================= SERVICE USAGE =================
    public List<Invoice> getServiceUsageList(int serviceId, Date startDate, Date endDate) {
        List<Invoice> list = new ArrayList<>();
        String sql = """
            SELECT i.id, i.invoiceDate,
                   c.name AS customerName, s.name AS staffName,
                   SUM(sv.price * sr.quantity) AS itemRevenue
            FROM tblInvoice i
            JOIN tblMember c ON i.tblCustomerId = c.id
            JOIN tblStaff st ON i.tblSalesStaffId = st.tblMemberId
            JOIN tblMember s ON st.tblMemberId = s.id
            JOIN tblRequest r ON i.id = r.tblInvoiceId
            JOIN tblServiceRequest sr ON sr.tblRequestId = r.id
            JOIN tblService sv ON sv.id = sr.tblServiceId
            WHERE sv.id = ?
              AND i.invoiceDate BETWEEN ? AND ?
            GROUP BY i.id, i.invoiceDate, c.name, s.name
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, serviceId);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setName(rs.getString("customerName"));

                SalesStaff salesStaff = new SalesStaff("Sales");
                salesStaff.setName(rs.getString("staffName"));

                Invoice inv = new Invoice();
                inv.setId(rs.getInt("id"));
                inv.setInvoiceDate(rs.getDate("invoiceDate"));
                inv.setTotalAmount(rs.getDouble("itemRevenue"));
                inv.setCustomer(customer);
                inv.setSalesStaff(salesStaff);

                list.add(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= SPARE PART USAGE =================
    public List<Invoice> getSparePartUsageList(int sparePartId, Date startDate, Date endDate) {
        List<Invoice> list = new ArrayList<>();
        String sql = """
            SELECT i.id, i.invoiceDate,
                   c.name AS customerName, s.name AS staffName,
                   SUM(sp.sellingPrice * spr.quantity) AS itemRevenue
            FROM tblInvoice i
            JOIN tblMember c ON i.tblCustomerId = c.id
            JOIN tblStaff st ON i.tblSalesStaffId = st.tblMemberId
            JOIN tblMember s ON st.tblMemberId = s.id
            JOIN tblRequest r ON i.id = r.tblInvoiceId
            JOIN tblSparePartRequest spr ON spr.tblRequestId = r.id
            JOIN tblSparePart sp ON sp.id = spr.tblSparePartId
            WHERE sp.id = ?
              AND i.invoiceDate BETWEEN ? AND ?
            GROUP BY i.id, i.invoiceDate, c.name, s.name
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, sparePartId);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setName(rs.getString("customerName"));

                SalesStaff salesStaff = new SalesStaff("Sales");
                salesStaff.setName(rs.getString("staffName"));

                Invoice inv = new Invoice();
                inv.setId(rs.getInt("id"));
                inv.setInvoiceDate(rs.getDate("invoiceDate"));
                inv.setTotalAmount(rs.getDouble("itemRevenue"));
                inv.setCustomer(customer);
                inv.setSalesStaff(salesStaff);

                list.add(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= GET FULL INVOICE =================
    public Invoice getInvoice(int invoiceId) {
        Invoice inv = new Invoice();

        String sqlInvoice = """
            SELECT i.id, i.invoiceDate, i.totalAmount,
                   c.name AS customerName, s.name AS staffName
            FROM tblInvoice i
            JOIN tblMember c ON i.tblCustomerId = c.id
            JOIN tblStaff st ON i.tblSalesStaffId = st.tblMemberId
            JOIN tblMember s ON st.tblMemberId = s.id
            WHERE i.id = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sqlInvoice)) {
            ps.setInt(1, invoiceId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setName(rs.getString("customerName"));

                SalesStaff salesStaff = new SalesStaff("Sales");
                salesStaff.setName(rs.getString("staffName"));

                inv.setId(rs.getInt("id"));
                inv.setInvoiceDate(rs.getDate("invoiceDate"));
                inv.setTotalAmount(rs.getDouble("totalAmount"));
                inv.setCustomer(customer);
                inv.setSalesStaff(salesStaff);
            }

            // ========== Lấy danh sách Request ==========
            String sqlReq = """
                SELECT id, type, description, status
                FROM tblRequest
                WHERE tblInvoiceId = ?
            """;
            PreparedStatement psReq = con.prepareStatement(sqlReq);
            psReq.setInt(1, invoiceId);
            ResultSet rsReq = psReq.executeQuery();

            List<Request> requestList = new ArrayList<>();
            while (rsReq.next()) {
                Request req = new Request();
                req.setId(rsReq.getInt("id"));
                req.setType(rsReq.getString("type"));
                req.setDescription(rsReq.getString("description"));
                req.setStatus(rsReq.getString("status"));
                req.setCustomer(inv.getCustomer());
                req.setSalesStaff(inv.getSalesStaff());

                // ========== Lấy danh sách ServiceRequest ==========
                String sqlService = """
                    SELECT sr.id, sr.quantity, s.id AS serviceId, s.name AS serviceName, s.price
                    FROM tblServiceRequest sr
                    JOIN tblService s ON sr.tblServiceId = s.id
                    WHERE sr.tblRequestId = ?
                """;
                PreparedStatement psService = con.prepareStatement(sqlService);
                psService.setInt(1, req.getId());
                ResultSet rsService = psService.executeQuery();

                List<ServiceRequest> serviceList = new ArrayList<>();
                while (rsService.next()) {
                    Service service = new Service();
                    service.setId(rsService.getInt("serviceId"));
                    service.setName(rsService.getString("serviceName"));
                    service.setPrice(rsService.getDouble("price"));

                    ServiceRequest sr = new ServiceRequest();
                    sr.setId(rsService.getInt("id"));
                    sr.setQuantity(rsService.getInt("quantity"));
                    sr.setService(service);
                    serviceList.add(sr);
                }
                req.setServiceRequestList(serviceList);

                // ========== Lấy danh sách SparePartRequest ==========
                String sqlPart = """
                    SELECT spr.id, spr.quantity, sp.id AS partId, sp.name AS partName, sp.sellingPrice
                    FROM tblSparePartRequest spr
                    JOIN tblSparePart sp ON spr.tblSparePartId = sp.id
                    WHERE spr.tblRequestId = ?
                """;
                PreparedStatement psPart = con.prepareStatement(sqlPart);
                psPart.setInt(1, req.getId());
                ResultSet rsPart = psPart.executeQuery();

                List<SparePartRequest> partList = new ArrayList<>();
                while (rsPart.next()) {
                    SparePart part = new SparePart();
                    part.setId(rsPart.getInt("partId"));
                    part.setName(rsPart.getString("partName"));
                    part.setSellingPrice(rsPart.getDouble("sellingPrice"));

                    SparePartRequest spr = new SparePartRequest();
                    spr.setId(rsPart.getInt("id"));
                    spr.setQuantity(rsPart.getInt("quantity"));
                    spr.setSparePart(part);
                    partList.add(spr);
                }
                req.setSparePartRequestList(partList);

                requestList.add(req);
            }

            inv.setRequestList(requestList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inv;
    }
}
