package borrow;

import book.BookDTO;
import databaseConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {

    public int borrowBook(LibraryDTO libraryDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement updatePs = null;
        try {
            conn=DBConnection.getConnection();
            String Sql = "INSERT INTO borrowed_books (book_id,member_id,borrow_date, return_date) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(Sql);
            ps.setInt(1, libraryDTO.getBookId());
            ps.setInt(2, libraryDTO.getMemberId());
            ps.setString(3, libraryDTO.getBorrowDate());
            ps.setString(4, libraryDTO.getReturnDate());
            ps.executeUpdate();
            String updateSql = "UPDATE books SET status='BORROWED' WHERE id=?";
            updatePs = conn.prepareStatement(updateSql);
            updatePs.setInt(1, libraryDTO.getBookId());
            updatePs.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
            if (updatePs != null) updatePs.close();
        }
        return 0;
    }

    public int returnBook(int bookId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement statusPs = null;
        try {
           conn= DBConnection.getConnection();
            String Sql = "DELETE FROM borrowed_books WHERE book_id=?";
            ps = conn.prepareStatement(Sql);
            ps.setInt(1, bookId);
            ps.executeUpdate();
            String statusSql = "UPDATE books SET status='AVAILABLE' WHERE id=?";
            statusPs = conn.prepareStatement(statusSql);
            statusPs.setInt(1, bookId);
            statusPs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
            if (statusPs != null) statusPs.close();
        }
        return 1;
    }

    public List<LibraryDTO> showBorrowList() throws SQLException {
        List <LibraryDTO> libraryDTOS=new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs =null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM borrowed_books WHERE book_id IS NOT null";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                LibraryDTO libraryDTO=new LibraryDTO(rs.getInt(1),rs.getInt(2)
                        ,rs.getString(3),rs.getString(4));
                libraryDTOS.add(libraryDTO);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return libraryDTOS;
    }

}