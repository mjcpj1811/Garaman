<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, Garaman.model.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Invoice Detail - GaraMan</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="invoice-page">
<%@ include file="/includes/navbar.jsp" %>
<%@ include file="/includes/formatVND.jsp" %>

<div class="invoice-container">
    <%
        Invoice invoice = (Invoice) request.getAttribute("invoice");
        double totalService = (Double) request.getAttribute("totalService");
        double totalPart = (Double) request.getAttribute("totalPart");
        double totalAmount = (Double) request.getAttribute("totalAmount");
    %>

    <h2 class="invoice-title">Invoice Detail</h2>

    <div class="invoice-info">
        <h3 class="invoice-section-title">General Information</h3>
        <p><strong>ID:</strong> <%= invoice.getId() %></p>
        <p><strong>Created date:</strong> <%= invoice.getInvoiceDate() %></p>
        <p><strong>Customer name:</strong> <%= invoice.getCustomer() != null ? invoice.getCustomer().getName() : "N/A" %></p>
        <p><strong>Created by:</strong> <%= invoice.getSalesStaff() != null ? invoice.getSalesStaff().getName() : "N/A" %></p>
    </div>

    <h3 class="invoice-section-title">Service Requests</h3>
    <table class="invoice-table">
        <tr><th>No.</th><th>ID</th><th>Name</th><th>Unit Price</th><th>Quantity</th><th>Amount</th></tr>
        <%
            int sIndex = 1;
            for (Request req : invoice.getRequestList()) {
                if (req.getServiceRequestList() != null) {
                    for (ServiceRequest s : req.getServiceRequestList()) {
        %>
        <tr>
            <td><%= sIndex++ %></td>
            <td><%= s.getService().getId() %></td>
            <td><%= s.getService().getName() %></td>
            <td><%= nf.format(s.getService().getPrice()) %></td>
            <td><%= s.getQuantity() %></td>
            <td><%= nf.format(s.getService().getPrice() * s.getQuantity()) %></td>
        </tr>
        <% }}} if (sIndex == 1) { %>
        <tr><td colspan="6">No service requests.</td></tr><% } %>
    </table>

    <h3 class="invoice-section-title">Spare Part Requests</h3>
    <table class="invoice-table">
        <tr><th>No.</th><th>ID</th><th>Name</th><th>Unit Price</th><th>Quantity</th><th>Amount</th></tr>
        <%
            int pIndex = 1;
            for (Request req : invoice.getRequestList()) {
                if (req.getSparePartRequestList() != null) {
                    for (SparePartRequest p : req.getSparePartRequestList()) {
        %>
        <tr>
            <td><%= pIndex++ %></td>
            <td><%= p.getSparePart().getId() %></td>
            <td><%= p.getSparePart().getName() %></td>
            <td><%= nf.format(p.getSparePart().getSellingPrice()) %></td>
            <td><%= p.getQuantity() %></td>
            <td><%= nf.format(p.getSparePart().getSellingPrice() * p.getQuantity()) %></td>
        </tr>
        <% }}} if (pIndex == 1) { %>
        <tr><td colspan="6">No spare part requests.</td></tr><% } %>
    </table>

    <div class="invoice-totals">
        <p>Total service: <%= nf.format(totalService) %> VND</p>
        <p>Total spare part: <%= nf.format(totalPart) %> VND</p>
        <p><strong>Total amount: <%= nf.format(totalAmount) %> VND</strong></p>
    </div>

    <form action="javascript:history.back()">
        <button type="submit" class="usage-btn">Back</button>
    </form>
</div>
</body>
</html>
