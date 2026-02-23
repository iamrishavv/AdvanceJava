package com.example.servlet;
import com.example.util.DBUtilProject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
@WebServlet("/login")
public class loginServletProject extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");

        try (Connection con = DBUtilProject.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");

            ps.setString(1, email);
            ps.setString(2, pwd);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("email", email);
                res.sendRedirect("dashboardProject.jsp");
            } else {
                res.getWriter().println("Invalid Credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
