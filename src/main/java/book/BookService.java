package book;


import borrow.*;
import staticStrings.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookService {
    static Scanner scanner = new Scanner(System.in);

    public void addBook() throws SQLException {
        String name = takeName();
        String writer = takeWriterName();
        String price = String.valueOf(takePrice());
        String status = "AVAILABLE";
        BookDTO bookDTO = new BookDTO(name, writer, price, status);
        BookDAO bookId = new BookDAO();
        Strings.bookAdded(bookId.addBook(bookDTO), bookDTO.getName(), bookDTO.getWriter(), bookDTO.getPrice(), bookDTO.getStatus());

    }


    public void showBooksList() throws SQLException {
        BookDAO bookDAO = new BookDAO();
        List<BookDTO> bookDTOList = bookDAO.showBooksList();
        for (BookDTO books : bookDTOList) {
            System.out.println(books.toString());
        }
    }

    public void editBook() throws SQLException {
        String id = takeBookId();
        BookDTO bookDTO = BookDAO.findById(Integer.parseInt(id));
        if (bookDTO != null) {
            Strings.currentBook(bookDTO);
            Strings.newBookInf();
            String newWriter = takeNewWriterName();
            String newName = takeNewName();
            String newPrice=takeNewPrice();
            Strings.enterStat();
            String newStatus = scanner.nextLine().toUpperCase();
            BookDTO newBookDTO = new BookDTO(newName, newWriter, newPrice, newStatus);
            BookDAO bookDAO = new BookDAO();
            int getToDao = bookDAO.editBook(newBookDTO, Integer.parseInt(id));
            if (getToDao == 0) {
                Strings.failedBookUpdate();
            } else {
                Strings.successBookUpdate();
                System.out.println(BookDAO.findById(Integer.parseInt(id)).toString());
            }
        }
    }


    public void deleteBook() throws SQLException {
        String id=takeBookId();
        BookDTO bookDTO = BookDAO.findById(Integer.parseInt(id));
        if (bookDTO != null) {
            System.out.println(bookDTO);
            Strings.sure();
            String sure = scanner.nextLine().toUpperCase();
            if (sure.equals("Y")) {
                BookDAO bookDAO = new BookDAO();
                int delete = bookDAO.deleteBook(Integer.parseInt(id));
                if (delete == 1)
                    Strings.delBookSuccess();
            } else {
                Strings.cancelDel();
            }
        } else {
            Strings.failDel();
        }
    }

    public void searchBook() throws SQLException {
        Strings.enterBookNorId();
        String input = scanner.next();
        if (input.matches("\\d+")) {
            BookDTO bookDTO = BookDAO.findById(Integer.parseInt(input));
            if (bookDTO == null) {
                Strings.bookNotFound();
            } else {
                System.out.println(bookDTO);
            }
        } else {
            BookDTO bookDTO = BookDAO.findByName(input);
            if (bookDTO == null) {
                Strings.bookNotFound();
            } else {
                System.out.println(bookDTO);
            }
        }
    }

    public static String takeBookId() {
        String bookId;
        do {
            Strings.enterBookId();
            bookId = scanner.nextLine();
            if (!BorrowValidation.isValidNumber(bookId)) {
                Strings.invalidId();
            }
        } while (!BorrowValidation.isValidNumber(bookId));
        return bookId;
    }

    public String takeName() {
        String name;
        do {
            Strings.enterBookName();
            name = scanner.nextLine();
            if (!BookVaidation.isValidName(name)) {
                Strings.invalidName();
            }
        } while (!BookVaidation.isValidName(name));
        return name;
    }

    public String takeWriterName() {
        String writer;
        do {
            Strings.writerName();
            writer = scanner.nextLine();
            if (!BookVaidation.isValidName(writer)) {
                Strings.invalidName();
            }
        } while (!BookVaidation.isValidName(writer));
        return writer;
    }

    public Integer takePrice() {
        String price;
        do {
            Strings.price();
            price = scanner.nextLine();
            if (!BookVaidation.isValidNumber(price)) {
                Strings.invalidPrice();
            }
        } while (!BookVaidation.isValidNumber(price));
        return null;
    }

    public String takeNewName() {
        String newName;
        do {
            newName = scanner.nextLine();
            if (!BookVaidation.isValidName(newName)) {
                Strings.invalidName();
            }
        } while (!BookVaidation.isValidName(newName));
        return newName;
    }

    public String takeNewWriterName() {
        String newWriter;
        do {
            Strings.writerName();
            newWriter = scanner.nextLine();
            if (!BookVaidation.isValidName(newWriter)) {
                Strings.invalidName();
            }
        } while (!BookVaidation.isValidName(newWriter));
        return newWriter;
    }

    public String takeNewPrice() {
        String newPrice;
        do {
            Strings.price();
            newPrice = scanner.nextLine();
            if (!BookVaidation.isValidNumber(newPrice)) {
                Strings.invalidPrice();
            }
        } while (!BookVaidation.isValidNumber(newPrice));
        return newPrice;
    }

    public static BookDTO findBook(int id) throws SQLException {
        return BookDAO.findById(id);
    }
}

