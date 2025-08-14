package book;


import borrow.*;
import staticStrings.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookService {
    static Scanner scanner = new Scanner(System.in);

    public void addBook() throws SQLException {
        String name;
        String writer;
        String price;
        do {
            Strings.enterBookName();
            name = scanner.nextLine();
            if (!BookVaidation.isValidName(name)) {
                Strings.invalidName();
            }
        } while (!BookVaidation.isValidName(name));
        do {
            Strings.writerName();
            writer = scanner.nextLine();
            if (!BookVaidation.isValidName(writer)) {
                Strings.invalidName();
            }
        } while (!BookVaidation.isValidName(writer));
        do {
            Strings.price();
            price = scanner.nextLine();
            if (!BookVaidation.isValidNumber(price)) {
                Strings.invalidPrice();
            }
        } while (!BookVaidation.isValidNumber(price));
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
        String id;
        do {
            Strings.enterBookId();
            id = scanner.nextLine();
            if (!BookVaidation.isValidNumber(id)) {
                Strings.invalidId();
            }
        } while (!BookVaidation.isValidNumber(id));
        BookDTO bookDTO = BookDAO.findById(Integer.parseInt(id));
        if (bookDTO != null) {
            Strings.currentBook(bookDTO);
            Strings.newBookInf();
            String newWriter;
            String newName;
            String newPrice;
            do {
                newName = scanner.nextLine();
                if (!BookVaidation.isValidName(newName)) {
                    Strings.invalidName();
                }
            } while (!BookVaidation.isValidName(newName));
            do {
                Strings.writerName();
                newWriter = scanner.nextLine();
                if (!BookVaidation.isValidName(newName)) {
                    Strings.invalidName();
                }
            } while (!BookVaidation.isValidName(newName));
            do {
                Strings.price();
                newPrice = scanner.nextLine();
                if (!BookVaidation.isValidNumber(newPrice)) {
                    Strings.invalidPrice();
                }
            } while (!BookVaidation.isValidNumber(newPrice));
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
        String id;
        do {
            Strings.enterBookId();
            id = scanner.nextLine();
            if (!BookVaidation.isValidNumber(id)) {
                Strings.invalidId();
            }
        } while (!BookVaidation.isValidNumber(id));
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

    public static BookDTO findBook(int id) throws SQLException {
        return BookDAO.findById(id);
    }
}

