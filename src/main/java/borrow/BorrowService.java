package borrow;


import book.BookDTO;
import book.BookService;
import member.MemberDTO;
import member.MemberService;
import staticStrings.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class BorrowService {
    static Scanner scanner = new Scanner(System.in);

    public void borrowBook() throws SQLException {
        String bookId = BookService.takeBookId();
        BookDTO bookFind = BookService.findBook(Integer.parseInt(bookId));
        String memberId = MemberService.takeMemberId();
        MemberDTO memberFind = MemberService.findMember(Integer.parseInt(memberId));
        if (memberFind == null) {
            Strings.memberNotFound();
            return;
        } else if (bookFind == null) {
            Strings.bookNotFound();
            return;
        } else if (bookFind.getStatus() != BookDTO.Status.AVAILABLE) {
            Strings.notAvailable();
            return;
        } else {
            String borrowDate = takeBorrowDate();
            String returnDate = takeReturnDate();
            BorrowDTO borrowDTO = new BorrowDTO(bookId, memberId,
                    borrowDate, returnDate);
            BorrowDAO borrowDAO = new BorrowDAO();
            int borrow = borrowDAO.borrowBook(borrowDTO);
            if (borrow == 1) {
                Strings.successBorrow();
            } else Strings.failBorrow();
        }
    }


    public void returnBook() throws SQLException {
        String bookId = BookService.takeBookId();
        BorrowDAO borrowDAO = new BorrowDAO();
        int returnBook = borrowDAO.returnBook(Integer.parseInt(bookId));
        if (returnBook == 1) {
            Strings.isAvailable();
        }
    }


        public void showDelayedBooks() throws SQLException {
            BorrowDAO borrowDAO = new BorrowDAO();
            List<BorrowDTO> borrowedBooks = borrowDAO.showBorrowList();
            boolean anyDelayed = false;
            for (BorrowDTO book : borrowedBooks) {
                long daysLate = calculateDelayDays(book.getReturnDate());
                if (daysLate > 0) {
                    anyDelayed = true;
                    Strings.late(book.getBookId(), daysLate, book.getMemberId(), book.getReturnDate());
                }
            }
            if (!anyDelayed) {
                Strings.notDelayBook();
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

    public String takeBorrowDate() {
        String borrowDate;
        do {
            Strings.borrowDate();
            borrowDate = scanner.nextLine();
            if (!BorrowValidation.isValidDate(borrowDate)) {
                Strings.invalidDate();
            }
        } while (!BorrowValidation.isValidDate(borrowDate));
        return borrowDate;
    }

    public String takeReturnDate() {
        String returnDate;

        do {
            Strings.returnDate();
            returnDate = scanner.nextLine();
            if (!BorrowValidation.isValidDate(returnDate)) {
                Strings.invalidDate();
            }
        } while (!BorrowValidation.isValidDate(returnDate));
        return returnDate;
    }
    private long calculateDelayDays(String returnDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate returnDate = LocalDate.parse(returnDateStr, formatter);
        return ChronoUnit.DAYS.between(returnDate, today);
    }

}
