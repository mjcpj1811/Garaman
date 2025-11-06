<%--
  Created by IntelliJ IDEA.
  User: Khanh
  Date: 11/1/2025
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*, Garaman.model.Member" %>
<%
    Member member = (Member) session.getAttribute("user");
    if (member == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>
