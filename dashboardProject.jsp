<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>

<html>
<head>
    <title>Dashboard</title>
    <style>
        body { margin: 0; font-family: Arial; }
        .container { display: flex; height: 100vh; }
        .menu {
            width: 220px;
            background: #2c3e50;
            color: white;
            padding: 20px;
        }
        .menu a {
            display: block;
            color: white;
            text-decoration: none;
            margin: 10px 0;
        }
        .menu a:hover { background: #34495e; padding: 5px; }
        .content { padding: 20px; flex: 1; }
    </style>
</head>

<body>

<%
    if (session.getAttribute("email") == null) {
        response.sendRedirect("loginProject.jsp");
        return;
    }
%>

<div class="container">

    <div class="menu">
        <h3>Dashboard</h3>
        <p>${sessionScope.email}</p>
        <hr>

        <a href="addressProject.jsp">Address</a>
        <a href="educationProject.jsp">Education</a>
        <a href="familyProject.jsp">Family</a>
        <a href="logout">Logout</a>

    </div>

    <div class="content">
        <h2>Welcome 👋</h2>
        <p>Select an option from the left menu.</p>
    </div>

</div>

</body>
</html>