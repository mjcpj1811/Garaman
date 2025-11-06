<%--
  Created by IntelliJ IDEA.
  User: Khanh
  Date: 10/28/2025
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="sessionCheck.jsp" %>

<div class="navbar">
    <h1>GaraMan</h1>
    <div style="display: flex; align-items: center; gap: 15px;">
        <span class="navbar-user">Welcome, <strong><%= member.getName() %></strong></span>
        <a href="logout" class="logout-btn">Logout</a>
    </div>
</div>
