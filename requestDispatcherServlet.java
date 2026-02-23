package com.example.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/first")
public class requestDispatcherServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter writer = res.getWriter();
        writer.append("this is first servlet response");


        RequestDispatcher rd = req.getRequestDispatcher("/second");

       // rd.forward(req, res);

        rd.include(req, res);
    }


}
