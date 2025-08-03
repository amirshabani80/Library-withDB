package book;


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
            System.out.println("Enter Book Name:");
            name = scanner.nextLine();
            if (!BookVaidation.isValidName(name)) {
                System.out.println("Invalid name!!");
            }
        } while (!BookVaidation.isValidName(name));
        do {
            System.out.println("Enter Writer Name:");
            writer = scanner.nextLine();
            if (!BookVaidation.isValidName(writer)) {
                System.out.println("Invalid name!!");
            }
        } while (!BookVaidation.isValidName(writer));
        do {
            System.out.println("Enter Price:");
            price = scanner.nextLine();
            if (!BookVaidation.isValidNumber(price)) {
                System.out.println("Invalid price!!");
            }
        } while (!BookVaidation.isValidNumber(price));
        String status = "AVAILABLE";
        BookDTO bookDTO = new BookDTO(name, writer, price, status);
        BookDAO bookId = new BookDAO();
        System.out.println("Book added successfully.");
        System.out.println("The Book ID: " + bookId.addBook(bookDTO) + "\n--------------------------------------------- "
                + "\n Book name: " + bookDTO.getName() + "|Writer : " + bookDTO.getWriter() + "|prise: " + bookDTO.getPrice() + "|Status: " + bookDTO.getStatus());
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
            System.out.println("Enter the Book ID Please");
            id = scanner.nextLine();
            if (!BookVaidation.isValidNumber(id)) {
                System.out.println("Invalid ID!!");
            }
        } while (!BookVaidation.isValidNumber(id));
        BookDTO bookDTO = BookDAO.findById(Integer.parseInt(id));
        if (bookDTO != null) {
            System.out.println("Current Book Info:" + bookDTO + "\n-----------------------------------------------------------\n" +
                    "Enter New information\n      ||      \nEnter The Name:");
            String newWriter;
            String newName;
            String newPrice;
            do {
                newName = scanner.nextLine();
                if (!BookVaidation.isValidName(newName)) {
                    System.out.println("InvalidName!!");
                }
            } while (!BookVaidation.isValidName(newName));
            do {
                System.out.println("Enter Writer Name:");
                newWriter = scanner.nextLine();
                if (!BookVaidation.isValidName(newName)) {
                    System.out.println("InvalidName!!");
                }
            } while (!BookVaidation.isValidName(newName));
            do {
                System.out.println("Enter Price:");
                newPrice = scanner.nextLine();
                if (!BookVaidation.isValidNumber(newPrice)) {
                    System.out.println("Invalid Price!!");
                }
            } while (!BookVaidation.isValidNumber(newPrice));
            System.out.println("Enter new status (AVAILABLE / NEEDREPARE / BORROW):");
            String newStatus = scanner.nextLine().toUpperCase();
            BookDTO newBookDTO = new BookDTO(newName, newWriter, newPrice, newStatus);
            BookDAO bookDAO = new BookDAO();
            int getToDao = bookDAO.editBook(newBookDTO, Integer.parseInt(id));
            if (getToDao == 0) {
                System.out.println("Failed to update the Book!!");
            } else {
                System.out.println("Book updated successfully\n---------------------------------------------------------------");
                System.out.println(BookDAO.findById(Integer.parseInt(id)).toString());
            }
        }
    }


    public void deleteBook() throws SQLException {
        String id;
        do {
            System.out.println("Enter the Book ID Please");
            id = scanner.nextLine();
            if (!BookVaidation.isValidNumber(id)) {
                System.out.println("Invalid ID!!");
            }
        } while (!BookVaidation.isValidNumber(id));
        BookDTO bookDTO = BookDAO.findById(Integer.parseInt(id));
        if (bookDTO != null) {
            System.out.println(bookDTO);
            System.out.println("If you are sure enter <<Y>>");
            String sure = scanner.nextLine().toUpperCase();
            if (sure.equals("Y")) {
                BookDAO bookDAO = new BookDAO();
                int delete = bookDAO.deleteBook(Integer.parseInt(id));
                if (delete == 1)
                    System.out.println("Book deleted successfully");
            } else {
                System.out.println("Deletion canceled");
            }
        } else {
            System.out.println("Deletion failed!!");
        }
    }

    public void searchBook() throws SQLException {
        System.out.println("Enter The Book name or ID");
        String input = scanner.next();
        if (input.matches("\\d+")) {
            BookDTO bookDTO = BookDAO.findById(Integer.parseInt(input));
            if (bookDTO == null) {
                System.out.println("Book not found!!");
            } else {
                System.out.println(bookDTO);
            }
        } else {
            BookDTO bookDTO = BookDAO.findByName(input);
            if (bookDTO == null) {
                System.out.println("Book not found!!");
            } else {
                System.out.println(bookDTO);
            }
        }
    }

    public static BookDTO findBook(int id) throws SQLException {
        return BookDAO.findById(id);
    }
}

