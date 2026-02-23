package com.example.servlet;

import com.example.util.DBUtilProject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
@WebServlet("/resetPwd")
public class resetPwdServletProject extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("resetEmail") == null) {
            res.sendRedirect("forgotPwdProject.jsp");
            return;
        }

        String email = session.getAttribute("resetEmail").toString();
        String newPwd = req.getParameter("newPwd");
        String confirmPwd = req.getParameter("confirmPwd");

        if (!newPwd.equals(confirmPwd)) {
            res.getWriter().println("Passwords do not match");
            return;
        }

        try (Connection con = DBUtilProject.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE users SET password=? WHERE email=?");

            ps.setString(1, newPwd);
            ps.setString(2, email);

            ps.executeUpdate();

            session.invalidate(); // clear session
            res.sendRedirect("loginProject.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
