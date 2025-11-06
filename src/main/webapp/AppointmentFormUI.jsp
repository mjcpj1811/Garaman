<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="Garaman.model.Customer" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book Appointment - GaraMan</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="appointment-page">
<%@ include file="/includes/navbar.jsp" %>

<%
    Customer customer = (Customer) session.getAttribute("user");
    if (customer == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    String success = (String) session.getAttribute("success");
    if (success != null) { session.removeAttribute("success"); }
    String error = (String) request.getAttribute("error");
%>

<div class="appointment-box">
    <h2 class="appointment-title">Book Online Appointment</h2>

    <form action="appointment" method="post">
        <!-- Ngày giờ -->
        <div class="appointment-row">
            <div style="flex:1;">
                <label class="appointment-label">Select Date & Time <span style="color:red">*</span></label>
                <input type="datetime-local" name="appointmentDateTime" required>
            </div>
        </div>

        <!-- Họ tên & SĐT -->
        <div class="appointment-row">
            <div style="flex:1;">
                <label class="appointment-label">Full Name</label>
                <input type="text" name="name" value="<%= customer != null ? customer.getName() : "" %>" required>
            </div>
            <div style="flex:1;">
                <label class="appointment-label">Phone Number</label>
                <input type="text" name="phoneNumber" value="<%= customer != null ? customer.getPhoneNumber() : "" %>" required>
            </div>
        </div>

        <!-- Email & Địa chỉ -->
        <div class="appointment-row">
            <div style="flex:1;">
                <label class="appointment-label">Email</label>
                <input type="email" name="email" value="<%= customer != null ? customer.getEmail() : "" %>" required>
            </div>
            <div style="flex:1;">
                <label class="appointment-label">Address</label>
                <input type="text" name="address" value="<%= customer != null ? customer.getAddress() : "" %>" required>
            </div>
        </div>

        <!-- Nút -->
        <input type="submit" value="Book Appointment" class="appointment-btn">
    </form>

    <% if (error != null) { %>
    <p class="appointment-message appointment-error"><%= error %></p>
    <% } else if (success != null) { %>
    <p class="appointment-message appointment-success"><%= success %></p>
    <% } %>

    <a href="CustomerMenuUI.jsp" class="appointment-back">← Back to Home</a>
</div>

</body>
</html>
