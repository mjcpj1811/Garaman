<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - GaraMan</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="login-page">
<div class="login-container">
    <h2 class="login-title">GaraMan Login</h2>

    <form action="login" method="post" class="login-form">
        <label class="login-label">Username:</label>
        <input type="text" name="username" class="login-input" placeholder="Enter your username" required>

        <label class="login-label">Password:</label>
        <input type="password" name="password" class="login-input" placeholder="Enter your password" required>

        <button type="submit" class="login-btn">Login</button>
    </form>

    <% if (request.getParameter("error") != null) { %>
    <p class="login-error">❌ Invalid username or password</p>
    <% } %>
</div>

</body>
</html>
