package com.example.dao;
import com.example.model.Book;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
public class BookDAO {
    // INSERT
    public boolean insertBook(Book book) {
        String sql = "INSERT INTO books VALUES (?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, book.getBookId());
            ps.setString(2, book.getBookName());
            ps.setDouble(3, book.getBookPrice());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE
    public boolean updateBook(Book book) {
        String sql = "UPDATE books SET book_name=?, book_price=? WHERE book_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getBookName());
            ps.setDouble(2, book.getBookPrice());
            ps.setInt(3, book.getBookId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteBook(int bookId) {
        String sql = "DELETE FROM books WHERE book_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
