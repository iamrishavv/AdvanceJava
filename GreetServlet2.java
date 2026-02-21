package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/greet2")
public class GreetServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // 1. Get name from form
        String name = req.getParameter("username");

        // 2. Get current hour
        int hour = LocalTime.now().getHour();

        String message;

        if (hour >= 6 && hour < 12) {
            message = "Good Morning";
        } else if (hour >= 12 && hour < 16) {
            message = "Good Afternoon";
        } else if (hour >= 16 && hour < 20) {
            message = "Good Evening";
        } else {
            message = "Good Night";
        }

        // 3. Output
        out.println("<h2>" + message + ", " + name + "!</h2>");
    }
}
