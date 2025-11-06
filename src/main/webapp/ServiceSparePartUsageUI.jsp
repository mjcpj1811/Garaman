<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, Garaman.model.Invoice" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Invoice Usage List - GaraMan</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="usage-page">
<%@ include file="/includes/navbar.jsp" %>
<%@ include file="/includes/formatVND.jsp" %>

<div class="usage-container">
    <h2 class="usage-title">Invoice Usage List</h2>

    <%
        List<Invoice> usageList = (List<Invoice>) request.getAttribute("usageList");
        Double totalRevenue = (Double) request.getAttribute("totalRevenue");
        String selectedName = (String) request.getAttribute("selectedName");
        String startDate = (String) request.getAttribute("startDate");
        String endDate = (String) request.getAttribute("endDate");
    %>

    <p class="usage-desc">
        Showing invoices for "<strong><%= selectedName %></strong>"
        from <strong><%= startDate %></strong> to <strong><%= endDate %></strong>
    </p>

    <table class="usage-table">
        <tr>
            <th>No.</th>
            <th>Invoice ID</th>
            <th>Date</th>
            <th>Customer</th>
            <th>Created by</th>
            <th>Total Amount (VND)</th>
        </tr>

        <%
            if (usageList != null && !usageList.isEmpty()) {
                int index = 1;
                for (Invoice inv : usageList) {
        %>
        <tr>
            <td><%= index++ %></td>
            <td>
                <a href="invoice?action=view&id=<%= inv.getId() %>" class="usage-link">
                    <%= inv.getId() %>
                </a>
            </td>
            <td><%= inv.getInvoiceDate() %></td>
            <td><%= inv.getCustomer() != null ? inv.getCustomer().getName() : "N/A" %></td>
            <td><%= inv.getSalesStaff() != null ? inv.getSalesStaff().getName() : "N/A" %></td>
            <td><%= nf.format(inv.getTotalAmount()) %></td>
        </tr>
        <% } } else { %>
        <tr><td colspan="6">No invoices found.</td></tr>
        <% } %>
    </table>

    <p class="usage-total">
        Total revenue: <%= nf.format(totalRevenue != null ? totalRevenue : 0) %> VND
    </p>

    <form action="javascript:history.back()">
        <button type="submit" class="usage-btn">Back</button>
    </form>
</div>

</body>
</html>
