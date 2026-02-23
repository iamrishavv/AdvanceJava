package com.example.servlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("login.html");
            return;
        }

        resp.sendRedirect("dashboard.jsp");
    }
}
