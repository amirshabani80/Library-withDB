package borrow;


import book.BookDTO;
import book.BookService;
import member.MemberDTO;
import member.MemberService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class BorrowService {
    static Scanner scanner = new Scanner(System.in);

    public void borrowBook() throws SQLException {
        String bookId;
        do {
            System.out.println("Enter the Book ID Please");
            bookId = scanner.nextLine();
            if (!BorrowValidation.isValidNumber(bookId)) {
                System.out.println("Invalid ID!!");
            }
        } while (!BorrowValidation.isValidNumber(bookId));
        BookDTO bookFind = BookService.findBook(Integer.parseInt(bookId));
        String memberId;
        do {
            System.out.println("Enter the Book ID Please");
            memberId = scanner.nextLine();
            if (!BorrowValidation.isValidNumber(memberId)) {
                System.out.println("Invalid ID!!");
            }
        } while (!BorrowValidation.isValidNumber(memberId));
        MemberDTO memberFind = MemberService.findMember(Integer.parseInt(memberId));
        if (memberFind == null) {
            System.out.println("Member Not Found!!");
            return;
        } else if (bookFind == null) {
            System.out.println("Book Not Found!!");
            return;
        } else if (bookFind.getStatus() != BookDTO.Status.AVAILABLE) {
            System.out.println("The Book is Not Available!!");
            return;
        } else {
            String borrowDate;
            String returnDate;
            do {
                System.out.println("Enter Borrow Date (like 2022-1-24):");
                borrowDate = scanner.nextLine();
                if (!BorrowValidation.isValidDate(borrowDate)) {
                    System.out.println("Invalid date!!");
                }
            } while (!BorrowValidation.isValidDate(borrowDate));
            do {
                System.out.println("Enter Return Date (like 2022-1-24):");
                returnDate = scanner.nextLine();
                if (!BorrowValidation.isValidDate(returnDate)) {
                    System.out.println("Invalid date!!");
                }
            } while (!BorrowValidation.isValidDate(returnDate));
            BorrowDTO borrowDTO = new BorrowDTO(bookId, memberId,
                    borrowDate, returnDate);
            BorrowDAO borrowDAO = new BorrowDAO();
            int borrow = borrowDAO.borrowBook(borrowDTO);
            if (borrow == 1) {
                System.out.println("The book has been successfully borrowed✔");
            } else System.out.println("Borrowing operation failed!!!");
        }
    }


    public void returnBook() throws SQLException {
        String bookId;
        do {
            System.out.println("Enter the Book ID Please");
            bookId = scanner.nextLine();
            if (!BorrowValidation.isValidNumber(bookId)) {
                System.out.println("Invalid ID!!");
            }
        } while (!BorrowValidation.isValidNumber(bookId));
        BookDTO bookFind = BookService.findBook(Integer.parseInt(bookId));
        BorrowDAO borrowDAO = new BorrowDAO();
        int returnBook = borrowDAO.returnBook(Integer.parseInt(bookId));
        if (returnBook == 1) {
            System.out.println("Now,The book is AVAILABLE!");
        }
    }


    public void showDelayedBooks() throws SQLException {
        BorrowDAO borrowDAO = new BorrowDAO();
        List<BorrowDTO> borrowedBooks = borrowDAO.showBorrowList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();

        boolean anyDelayed = false;

        for (BorrowDTO book : borrowedBooks) {
            LocalDate returnDate = LocalDate.parse(book.getReturnDate(), formatter);
            if (today.isAfter(returnDate)) {
                anyDelayed = true;
                long daysLate = ChronoUnit.DAYS.between(returnDate, today);
                System.out.println("Book ID: " + book.getBookId() + " is delayed by " + daysLate + " days.");
                System.out.println("Member ID: " + book.getMemberId());
                System.out.println("Return Date: " + book.getReturnDate());
                System.out.println("------------------------------");
            }
        }

        if (!anyDelayed) {
            System.out.println("No delayed books found.");
        }
    }


    public void showBorrowList() throws SQLException {
        BorrowDAO borrowDAO = new BorrowDAO();
        List<BorrowDTO> borrowedBooks = borrowDAO.showBorrowList();
        for (BorrowDTO borrowDTO : borrowedBooks
        ) {
            System.out.println(borrowDTO);
            System.out.println("book: " + BookService.findBook(borrowDTO.getBookId()).getName() +
                    " |member: " + MemberService.findMember(borrowDTO.getMemberId()).getName());
        }
    }

}
