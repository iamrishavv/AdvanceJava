package com.example.servlet;
//post method
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/book")

public class BookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. Read form data
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        String bookName = req.getParameter("bookName");
        double bookPrice = Double.parseDouble(req.getParameter("bookPrice"));

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // 2. JDBC logic
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/advjdb",
                    "root",
                    "Rishav123@"
            );

            String sql = "INSERT INTO books VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bookId);
            ps.setString(2, bookName);
            ps.setDouble(3, bookPrice);

            int count = ps.executeUpdate();

            if (count > 0) {
                out.println("<h2>Book Saved Successfully!</h2>");
            } else {
                out.println("<h2>Failed to Save Book</h2>");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
