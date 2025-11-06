package Garaman.servlet;

import Garaman.dao.InvoiceDAO;
import Garaman.model.Invoice;
import Garaman.model.Request;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/invoice")
public class InvoiceServlet extends HttpServlet {
    private final InvoiceDAO invoiceDAO = new InvoiceDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String type = request.getParameter("type");
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        request.setAttribute("selectedName", name);

        if (action == null || idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số action hoặc id");
            return;
        }

        try {
            if ("listUsage".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(idParam);
                String startDateStr = request.getParameter("startDate");
                String endDateStr = request.getParameter("endDate");

                java.sql.Date startDate = startDateStr != null && !startDateStr.isEmpty()
                        ? java.sql.Date.valueOf(startDateStr)
                        : java.sql.Date.valueOf("1900-01-01");
                java.sql.Date endDate = endDateStr != null && !endDateStr.isEmpty()
                        ? java.sql.Date.valueOf(endDateStr)
                        : java.sql.Date.valueOf("2999-12-31");

                List<Invoice> invoices = null;

                if ("service".equalsIgnoreCase(type)) {
                    invoices = invoiceDAO.getServiceUsageList(id, startDate, endDate);
                } else if ("sparepart".equalsIgnoreCase(type)) {
                    invoices = invoiceDAO.getSparePartUsageList(id, startDate, endDate);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tham số type không hợp lệ");
                    return;
                }

                double totalRevenue = invoices.stream()
                        .mapToDouble(Invoice::getTotalAmount)
                        .sum();

                request.setAttribute("usageList", invoices);
                request.setAttribute("totalRevenue", totalRevenue);
                request.setAttribute("type", type);
                request.setAttribute("startDate", startDateStr);
                request.setAttribute("endDate", endDateStr);

                request.getRequestDispatcher("ServiceSparePartUsageUI.jsp").forward(request, response);
                return;
            }

            if ("view".equalsIgnoreCase(action)) {
                int invoiceId = Integer.parseInt(idParam);
                Invoice invoice = invoiceDAO.getInvoice(invoiceId);

                if (invoice == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy hóa đơn");
                    return;
                }

                double totalService = 0;
                double totalPart = 0;

                if (invoice.getRequestList() != null) {
                    for (Request req : invoice.getRequestList()) {
                        if (req.getServiceRequestList() != null) {
                            totalService += req.getServiceRequestList().stream()
                                    .mapToDouble(s -> s.getService().getPrice() * s.getQuantity())
                                    .sum();
                        }
                        if (req.getSparePartRequestList() != null) {
                            totalPart += req.getSparePartRequestList().stream()
                                    .mapToDouble(p -> p.getSparePart().getSellingPrice() * p.getQuantity())
                                    .sum();
                        }
                    }
                }

                request.setAttribute("invoice", invoice);
                request.setAttribute("totalService", totalService);
                request.setAttribute("totalPart", totalPart);
                request.setAttribute("totalAmount", invoice.getTotalAmount());

                request.getRequestDispatcher("InvoiceViewUI.jsp").forward(request, response);
                return;
            }

            response.sendRedirect("ServiceSparePartRevenueUI.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý dữ liệu");
        }
    }
}
