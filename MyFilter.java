package com.example.servlet;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/hello")
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        // 1️⃣ PRE-PROCESSING
        System.out.println("Filter Pre-Processing: Before Servlet");

        // Call next resource (Servlet)
        chain.doFilter(request, response);

        // 2️⃣ POST-PROCESSING
        System.out.println("Filter Post-Processing: After Servlet");
    }
}
