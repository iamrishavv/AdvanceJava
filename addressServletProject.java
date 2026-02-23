package com.example.servlet;
import com.example.util.DBUtilProject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/address")
public class addressServletProject extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("email") == null) {
            res.sendRedirect("loginProject.jsp");
            return;
        }

        String email = session.getAttribute("email").toString();
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String country = req.getParameter("country");

        try (Connection con = DBUtilProject.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO address(email, city, state, country) VALUES(?,?,?,?)"
            );

            ps.setString(1, email);
            ps.setString(2, city);
            ps.setString(3, state);
            ps.setString(4, country);

            ps.executeUpdate();

            res.sendRedirect("dashboardProject.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
