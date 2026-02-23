package com.example.servlet;

import com.example.util.DBUtil;   // ✅ USE HIKARI DBUtil
import com.example.util.DBUtil2;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try (Connection con = DBUtil2.getConnection()) {

            String sql =
                    "INSERT INTO users(fname, lname, email, password, gender) VALUES (?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, req.getParameter("fname"));
            ps.setString(2, req.getParameter("lname"));
            ps.setString(3, req.getParameter("email"));
            ps.setString(4, req.getParameter("password"));
            ps.setString(5, req.getParameter("gender"));

            int rows = ps.executeUpdate();

            if (rows > 0) {
                // ✅ PASS SUCCESS MESSAGE
                resp.sendRedirect("login.html?msg=success");
            } else {
                resp.sendRedirect("register.html?msg=fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("register.html?msg=fail");
        }
    }
}