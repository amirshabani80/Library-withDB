package Borrow;

import DB.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryService {

    public void borrowBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Enter member ID");
            int memberId = scanner.nextInt();
            System.out.println("Enter book ID");
            int bookId = scanner.nextInt();
            scanner.nextLine();
            String memberCheckSql = "SELECT * FROM members WHERE id = ?";
            PreparedStatement memberPs = conn.prepareStatement(memberCheckSql);
            memberPs.setInt(1, memberId);
            ResultSet memberRs = memberPs.executeQuery();
            if (!memberRs.next()) {
                System.out.println("Member Not Found!!");
                return;
            }
            String bookCheckSql = "SELECT * FROM books WHERE id=?";
            PreparedStatement bookPs = conn.prepareStatement(bookCheckSql);
            bookPs.setInt(1, bookId);
            ResultSet bookRs = bookPs.executeQuery();
            if (!bookRs.next()) {
                System.out.println("Book Not Found!!");
                return;
            }
            String status = bookRs.getString("status");
            if (!status.equalsIgnoreCase("AVAILABLE")) {
                System.out.println("The Book is Not Available!!");
                return;
            }

            System.out.println("Enter Borrow Date (like 2022/1/24):");
            String borrowDate = scanner.nextLine();
            System.out.println("Enter Return Date (like 2022/1/24):");
            String returnDate = scanner.nextLine();
            String insertSql = "INSERT INTO borrowed_books (member_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?)";
            PreparedStatement insertPs = conn.prepareStatement(insertSql);
            insertPs.setInt(1, memberId);
            insertPs.setInt(2, bookId);
            insertPs.setString(3, borrowDate);
            insertPs.setString(4, returnDate);
            insertPs.executeUpdate();
            //update status
            String updateBookSql = "UPDATE books SET status='BORROW' WHERE id=?";
            PreparedStatement updatePs = conn.prepareStatement(updateBookSql);
            updatePs.setInt(1, bookId);
            updatePs.executeUpdate();
            System.out.println("The book has been successfully borrowed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Enter Book ID");
            int bookId = scanner.nextInt();
            String bookSql = "SELECT * FROM books WHERE id=?";
            PreparedStatement bookPs = conn.prepareStatement(bookSql);
            bookPs.setInt(1, bookId);
            ResultSet bookRs = bookPs.executeQuery();
            if (!bookRs.next()) {
                System.out.println("Book Not Found!!");
                return;
            }
            //delete from borrowed_books
            String deleteSql = "DELETE FROM borrowed_books WHERE book_id=?";
            PreparedStatement deletePs = conn.prepareStatement(deleteSql);
            deletePs.setInt(1, bookId);
            deletePs.executeUpdate();

            //change book status
            String statusSql = "UPDATE books SET status='AVAILABLE' WHERE id=?";
            PreparedStatement statusPs = conn.prepareStatement(statusSql);
            statusPs.setInt(1, bookId);
            statusPs.executeUpdate();

            System.out.println("Now,The book is AVAILABLE!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showBorrowList() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM borrowed_books WHERE member_id IS NOT null";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Member ID: " + rs.getInt("member_id")
                        + "|Borrowed book ID: " + rs.getInt("book_id") + "|Borrow date: "
                        + rs.getString("borrow_date") + "|Return date: " + rs.getString("return_date"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
