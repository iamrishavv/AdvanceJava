package com.example.servlet;
import com.example.util.DBUtil;
import com.example.util.DBUtil2;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            Connection con = DBUtil2.getConnection();

            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, req.getParameter("email"));
            ps.setString(2, req.getParameter("password"));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("user", rs.getString("fname"));
                resp.sendRedirect("dashboard");
            } else {
                resp.getWriter().println("Invalid Credentials");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
