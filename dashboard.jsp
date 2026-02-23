<%--
  Created by IntelliJ IDEA.
  User: Rishav
  Date: 21-02-2026
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<h2>Hello, Welcome to our application</h2>

<p>User: <%= session.getAttribute("user") %></p>

<a href="logout">Logout</a>

</body>
</html>