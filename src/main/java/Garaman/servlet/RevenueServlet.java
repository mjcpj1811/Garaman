package Garaman.servlet;

import Garaman.dao.ServiceDAO;
import Garaman.dao.SparePartDAO;
import Garaman.model.ServiceStatistic;
import Garaman.model.SparePartStatistic;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/revenue")
public class RevenueServlet extends HttpServlet {

    private final ServiceDAO serviceDAO = new ServiceDAO();
    private final SparePartDAO sparePartDAO = new SparePartDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String start = request.getParameter("startDate");
        String end = request.getParameter("endDate");

        if (start != null && end != null && !start.isEmpty() && !end.isEmpty()) {
            Date startDate = Date.valueOf(start);
            Date endDate = Date.valueOf(end);

            if (startDate.after(endDate)) {
                request.setAttribute("errorMsg", "❌ Ngày bắt đầu không được sau ngày kết thúc!");
            } else {
                List<ServiceStatistic> serviceStats = serviceDAO.getServiceRevenueList(startDate, endDate);
                List<SparePartStatistic> partStats = sparePartDAO.getSparePartRevenueList(startDate, endDate);

                double totalServiceRevenue = serviceStats.stream().mapToDouble(ServiceStatistic::getTotalRevenue).sum();
                double totalSpareRevenue = partStats.stream().mapToDouble(SparePartStatistic::getTotalRevenue).sum();

                request.setAttribute("serviceStats", serviceStats);
                request.setAttribute("partStats", partStats);
                request.setAttribute("totalServiceRevenue", totalServiceRevenue);
                request.setAttribute("totalSpareRevenue", totalSpareRevenue);
                request.setAttribute("totalOverallRevenue", totalServiceRevenue + totalSpareRevenue);
                request.setAttribute("startDate", start);
                request.setAttribute("endDate", end);
            }
        }

        request.getRequestDispatcher("ServiceSparePartRevenueUI.jsp").forward(request, response);
    }
}
