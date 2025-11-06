<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, Garaman.model.ServiceStatistic, Garaman.model.SparePartStatistic" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Service & Spare Part Revenue - GaraMan</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="revenue-page">
<%@ include file="/includes/navbar.jsp" %>
<%@ include file="/includes/formatVND.jsp" %>

<div class="revenue-container">

    <h2 class="revenue-title">Service & Spare Part Revenue</h2>

    <!-- Bộ lọc ngày -->
    <form action="revenue" method="get" class="revenue-form">
        <label class="revenue-label">Date from:</label>
        <input type="date" name="startDate" class="revenue-input"
               value="<%= request.getAttribute("startDate") != null ? request.getAttribute("startDate") : "" %>">

        <label class="revenue-label">Date to:</label>
        <input type="date" name="endDate" class="revenue-input"
               value="<%= request.getAttribute("endDate") != null ? request.getAttribute("endDate") : "" %>">

        <input type="submit" class="revenue-btn" value="View">
    </form>

    <% String errorMsg = (String) request.getAttribute("errorMsg"); %>
    <% if (errorMsg != null) { %>
    <div class="revenue-error"><%= errorMsg %></div>
    <% } %>

    <!-- Dịch vụ -->
    <%
        List<ServiceStatistic> serviceStats = (List<ServiceStatistic>) request.getAttribute("serviceStats");
        Double totalServiceRevenue = (Double) request.getAttribute("totalServiceRevenue");
    %>

    <h3 class="revenue-subtitle">Service Statistics</h3>
    <table class="revenue-table">
        <tr>
            <th>No.</th>
            <th>ID</th>
            <th>Name</th>
            <th>Usage Count</th>
            <th>Revenue (VND)</th>
        </tr>
        <%
            if (serviceStats != null && !serviceStats.isEmpty()) {
                int index = 1;
                for (ServiceStatistic s : serviceStats) {
        %>
        <tr>
            <td><%= index++ %></td>
            <td><%= s.getId() %></td>
            <td>
                <a href="invoice?action=listUsage&type=service&id=<%= s.getId() %>&name=<%= java.net.URLEncoder.encode(s.getName(), "UTF-8") %>&startDate=<%= request.getAttribute("startDate") %>&endDate=<%= request.getAttribute("endDate") %>">
                    <%= s.getName() %>
                </a>
            </td>
            <td><%= s.getTotalUsage() %></td>
            <td><%= nf.format(s.getTotalRevenue()) %></td>
        </tr>
        <% } } else { %>
        <tr><td colspan="6">No service statistics available.</td></tr>
        <% } %>
    </table>

    <p class="revenue-total">Total service revenue:
        <%= nf.format(totalServiceRevenue != null ? totalServiceRevenue : 0) %> VND
    </p>

    <!-- Phụ tùng -->
    <%
        List<SparePartStatistic> partStats = (List<SparePartStatistic>) request.getAttribute("partStats");
        Double totalSpareRevenue = (Double) request.getAttribute("totalSpareRevenue");
        Double totalOverallRevenue = (Double) request.getAttribute("totalOverallRevenue");
    %>

    <h3 class="revenue-subtitle">Spare Part Statistics</h3>
    <table class="revenue-table">
        <tr>
            <th>No.</th>
            <th>ID</th>
            <th>Name</th>
            <th>Usage Count</th>
            <th>Revenue (VND)</th>
        </tr>
        <%
            if (partStats != null && !partStats.isEmpty()) {
                int index = 1;
                for (SparePartStatistic p : partStats) {
        %>
        <tr>
            <td><%= index++ %></td>
            <td><%= p.getId() %></td>
            <td>
                <a href="invoice?action=listUsage&type=sparepart&id=<%= p.getId() %>&name=<%= java.net.URLEncoder.encode(p.getName(), "UTF-8") %>&startDate=<%= request.getAttribute("startDate") %>&endDate=<%= request.getAttribute("endDate") %>">
                    <%= p.getName() %>
                </a>
            </td>
            <td><%= p.getTotalUsage() %></td>
            <td><%= nf.format(p.getTotalRevenue()) %></td>
        </tr>
        <% } } else { %>
        <tr><td colspan="6">No spare part statistics available.</td></tr>
        <% } %>
    </table>

    <p class="revenue-total">Total spare part revenue:
        <%= nf.format(totalSpareRevenue != null ? totalSpareRevenue : 0) %> VND
    </p>

    <p class="revenue-total">
        <strong>Total Overall Revenue:
            <%= nf.format(totalOverallRevenue != null ? totalOverallRevenue : 0) %> VND
        </strong>
    </p>
    <form action="SelectStatisticsTypeUI.jsp" method="get">
        <button type="submit" class="usage-btn">Back</button>
    </form>

</div>
</body>
</html>
