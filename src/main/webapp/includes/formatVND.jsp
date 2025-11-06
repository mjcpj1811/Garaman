<%--
  Created by IntelliJ IDEA.
  User: Khanh
  Date: 11/1/2025
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.text.NumberFormat, java.util.Locale" %>
<%
    NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
    nf.setMaximumFractionDigits(0);

    application.setAttribute("vnCurrencyFormat", nf);
%>
