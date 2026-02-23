package com.example.servlet;

import com.example.util.DBUtilProject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
@WebServlet("/forgotPwd")
public class ForgotPwdServletProject extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String email = req.getParameter("email");

        try (Connection con = DBUtilProject.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT email FROM users WHERE email=?");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("resetEmail", email);
                res.sendRedirect("resetPwdProject.jsp");
            } else {
                res.getWriter().println("Email not registered");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
