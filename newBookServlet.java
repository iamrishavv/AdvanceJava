package com.example.servlet;
import com.example.dao.BookDAO;
import com.example.model.Book;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/book")
public class newBookServlet extends HttpServlet {
    private BookDAO dao = new BookDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");
        resp.setContentType("text/html");

        if ("insert".equals(action)) {

            Book book = new Book(
                    Integer.parseInt(req.getParameter("bookId")),
                    req.getParameter("bookName"),
                    Double.parseDouble(req.getParameter("bookPrice"))
            );

            resp.getWriter().println(
                    dao.insertBook(book) ? "Book Inserted Successfully" : "Insert Failed"
            );

        } else if ("update".equals(action)) {

            Book book = new Book(
                    Integer.parseInt(req.getParameter("bookId")),
                    req.getParameter("bookName"),
                    Double.parseDouble(req.getParameter("bookPrice"))
            );

            resp.getWriter().println(
                    dao.updateBook(book) ? "Book Updated Successfully" : "Update Failed"
            );

        } else if ("delete".equals(action)) {

            int bookId = Integer.parseInt(req.getParameter("bookId"));

            resp.getWriter().println(
                    dao.deleteBook(bookId) ? "Book Deleted Successfully" : "Delete Failed"
            );
        }
    }
}
