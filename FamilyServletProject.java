package com.example.servlet;
import com.example.util.DBUtilProject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/family")
public class FamilyServletProject {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("email") == null) {
            res.sendRedirect("loginProject.jsp");
            return;
        }

        String email = session.getAttribute("email").toString();
        String father = req.getParameter("father");
        String mother = req.getParameter("mother");
        int siblings = Integer.parseInt(req.getParameter("siblings"));

        try (Connection con = DBUtilProject.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO family(email, father, mother, siblings) VALUES(?,?,?,?)"
            );

            ps.setString(1, email);
            ps.setString(2, father);
            ps.setString(3, mother);
            ps.setInt(4, siblings);

            ps.executeUpdate();

            res.sendRedirect("dashboardProject.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
