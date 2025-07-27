package book;

import databaseConnection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public int addBook(BookDTO bookDTO) throws SQLException {
        int generatedId = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO books (name, writer, price, status) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bookDTO.getName());
            ps.setString(2, bookDTO.getWriter());
            ps.setInt(3, bookDTO.getPrice());
            ps.setString(4, String.valueOf(bookDTO.getStatus()));
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return generatedId;
    }

    public List<BookDTO> showBooksList() throws SQLException {
        List<BookDTO> bookDTOList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM books WHERE name is not null";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BookDTO bookDTO = new BookDTO(rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5));
                bookDTO.setId(rs.getInt(1));
                bookDTOList.add(bookDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return bookDTOList;
    }

    public int editBook(BookDTO newBookDTO, int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String updateSql = "UPDATE books SET name = ?, writer = ?, price = ?, status = ? WHERE id = ?";
            ps = conn.prepareStatement(updateSql);
            ps.setString(1, newBookDTO.getName());
            ps.setString(2, newBookDTO.getWriter());
            ps.setInt(3, newBookDTO.getPrice());
            ps.setString(4, String.valueOf(newBookDTO.getStatus()));
            ps.setInt(5, id);
            int updatedRow = ps.executeUpdate();
            if (updatedRow == 0) {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return 1;
    }

    public int deleteBook(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "DELETE FROM books WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return 0;
    }

    public static BookDTO findById(int id) throws SQLException {
        BookDTO book = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM books WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                book = new BookDTO(rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5));
                book.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return book;
    }
}
