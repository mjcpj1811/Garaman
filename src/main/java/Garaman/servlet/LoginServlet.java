package Garaman.servlet;

import Garaman.dao.MemberDAO;
import Garaman.dao.StaffDAO;
import Garaman.model.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final MemberDAO memberDAO = new MemberDAO();
    private final StaffDAO staffDAO = new StaffDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Member baseMember = memberDAO.login(username, password);

        if (baseMember != null) {
            HttpSession session = request.getSession();

            // Xác định role
            String role = baseMember.getRole().toLowerCase();
            Member user = baseMember; // Mặc định là Member

            switch (role) {
                case "customer" -> {
                    // Tạo Customer từ Member
                    Customer customer = new Customer();
                    copyMemberFields(baseMember, customer);
                    user = customer;
                    session.setAttribute("user", user);
                    response.sendRedirect("CustomerMenuUI.jsp");
                    return;
                }

                case "staff" -> {
                    String position = staffDAO.getStaffPosition(baseMember.getId()).toLowerCase();
                    session.setAttribute("position", position);

                    if ("manager".equalsIgnoreCase(position)) {
                        Manager manager = new Manager(position);
                        copyMemberFields(baseMember, manager);
                        user = manager;
                        session.setAttribute("user", user);
                        response.sendRedirect("ManagerMenuUI.jsp");
                        return;
                    }
                    else {
                        response.sendRedirect("Login.jsp?error=unknownPosition");
                        return;
                    }
                }

                default -> {
                    response.sendRedirect("Login.jsp?error=role");
                    return;
                }
            }

        } else {
            response.sendRedirect("Login.jsp?error=invalid");
        }
    }

    /**
     * Tiện ích sao chép thuộc tính chung từ Member sang subclass
     */
    private void copyMemberFields(Member src, Member dest) {
        dest.setId(src.getId());
        dest.setName(src.getName());
        dest.setUsername(src.getUsername());
        dest.setPassword(src.getPassword());
        dest.setBirthday(src.getBirthday());
        dest.setAddress(src.getAddress());
        dest.setEmail(src.getEmail());
        dest.setPhoneNumber(src.getPhoneNumber());
        dest.setRole(src.getRole());
        dest.setGender(src.getGender());
    }
}
