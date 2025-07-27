package borrow;

import book.BookDAO;
import book.BookDTO;
import databaseConnection.DBConnection;
import member.MemberDAO;
import member.MemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LibraryService {
    static Scanner scanner = new Scanner(System.in);

    public void borrowBook() throws SQLException {
        System.out.println("Enter book ID");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        BookDTO bookFind = BookDAO.findById(bookId);
        System.out.println("Enter member ID");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        MemberDTO memberFind = MemberDAO.findById(memberId);
        if (memberFind == null) {
            System.out.println("Member Not Found!!");
            return;
        } else if (bookFind == null) {
            System.out.println("Book Not Found!!");
            return;
        } else if (bookFind.getStatus()!= BookDTO.Status.AVAILABLE) {
            System.out.println("The Book is Not Available!!");
            return;
        } else {
            System.out.println("Enter Borrow Date (like 2022-1-24):");
            String borrowDate = scanner.nextLine();
            System.out.println("Enter Return Date (like 2022-1-24):");
            String returnDate = scanner.nextLine();
            LibraryDTO libraryDTO = new LibraryDTO(bookId, memberId,
                    borrowDate, returnDate);
            LibraryDAO libraryDAO = new LibraryDAO();
            int borrow = libraryDAO.borrowBook(libraryDTO);
            if (borrow == 1) {
                System.out.println("The book has been successfully borrowed✔");
            } else System.out.println("Borrowing operation failed!!!");
        }
    }


    public void returnBook() throws SQLException {
        System.out.println("Enter Book ID");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        BookDTO bookFind = BookDAO.findById(bookId);
        if (bookFind == null) {
            System.out.println("Book Not Found!!");
            return;
        }

        LibraryDAO libraryDAO = new LibraryDAO();
        int returnBook = libraryDAO.returnBook(bookId);
        if (returnBook == 1) {
            System.out.println("Now,The book is AVAILABLE!");
        }
    }

    public void showBorrowList() throws SQLException {
        LibraryDAO libraryDAO = new LibraryDAO();
        List<LibraryDTO> borrowedBooks = libraryDAO.showBorrowList();
        for (LibraryDTO libraryDTO : borrowedBooks
        ) {
            System.out.println(libraryDTO.toString());
        }
    }

}
