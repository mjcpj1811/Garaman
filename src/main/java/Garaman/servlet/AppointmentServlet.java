package Garaman.servlet;

import Garaman.dao.AppointmentDAO;
import Garaman.dao.CustomerDAO;
import Garaman.model.Appointment;
import Garaman.model.Customer;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {
    private final AppointmentDAO appointmentDAO = new AppointmentDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        if (customer == null || !"customer".equalsIgnoreCase(customer.getRole())) {
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            String dateTimeStr = request.getParameter("appointmentDateTime");
            String name = request.getParameter("name");
            String phone = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            if (dateTimeStr == null || dateTimeStr.isEmpty()) {
                request.setAttribute("error", "⚠️ Please select a date and time.");
                request.getRequestDispatcher("AppointmentFormUI.jsp").forward(request, response);
                return;
            }

            // Cập nhật thông tin khách hàng
            customer.setName(name);
            customer.setPhoneNumber(phone);
            customer.setEmail(email);
            customer.setAddress(address);
            customerDAO.updateProfile(customer);

            // Parse thời gian
            LocalDateTime appointmentDateTime = LocalDateTime.parse(dateTimeStr);
            if (appointmentDateTime.isBefore(LocalDateTime.now())) {
                request.setAttribute("error", "❌ You cannot book an appointment in the past.");
                request.getRequestDispatcher("AppointmentFormUI.jsp").forward(request, response);
                return;
            }

            Date date = Date.valueOf(appointmentDateTime.toLocalDate());
            Time time = Time.valueOf(appointmentDateTime.toLocalTime());
            Appointment appointment = new Appointment(0, date, time, "Pending");

            boolean ok = appointmentDAO.saveAppointment(appointment, customer);

            if (ok) {
                customer.addAppointment(appointment); // 🔹 thêm vào danh sách trong Customer
                session.setAttribute("user", customer); // 🔹 cập nhật lại session
                session.setAttribute("success", "✅ Appointment booked successfully!");
                response.sendRedirect("AppointmentFormUI.jsp");
            } else {
                request.setAttribute("error", "⚠️ Appointment already exists or could not be booked!");
                request.getRequestDispatcher("AppointmentFormUI.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "⚠️ Invalid date/time format!");
            request.getRequestDispatcher("AppointmentFormUI.jsp").forward(request, response);
        }
    }
}
