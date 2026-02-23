package com.example.servlet;
import com.example.util.DBUtilProject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
@WebServlet("/register")
public class registerServletProject extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        try (Connection con = DBUtilProject.getConnection()) {

            PreparedStatement ps = con.prepareStatement("INSERT INTO users(fname,lname,email,password,gender) VALUES(?,?,?,?,?)");

            ps.setString(1, req.getParameter("fname"));
            ps.setString(2, req.getParameter("lname"));
            ps.setString(3, req.getParameter("email"));
            ps.setString(4, req.getParameter("pwd"));
            ps.setString(5, req.getParameter("gender"));

            ps.executeUpdate();
            res.sendRedirect("loginProject.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
