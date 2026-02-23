package com.example.servlet;
import com.example.util.DBUtilProject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/education")
public class educationServletProject {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("email") == null) {
            res.sendRedirect("loginProject.jsp");
            return;
        }

        String email = session.getAttribute("email").toString();
        String qualification = req.getParameter("qualification");
        String year = req.getParameter("year");
        String percentage = req.getParameter("percentage");

        try (Connection con = DBUtilProject.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO education(email, qualification, year, percentage) VALUES(?,?,?,?)"
            );

            ps.setString(1, email);
            ps.setString(2, qualification);
            ps.setString(3, year);
            ps.setString(4, percentage);

            ps.executeUpdate();

            res.sendRedirect("dashboardProject.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
