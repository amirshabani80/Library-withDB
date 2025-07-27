package book;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookService {
    static Scanner scanner = new Scanner(System.in);

    public void addBook() throws SQLException {
        System.out.println("Enter Book Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Writer Name:");
        String writer = scanner.nextLine();
        System.out.println("Enter Price:");
        int price = scanner.nextInt();
        scanner.nextLine();
        String status = "AVAILABLE";
            BookDTO bookDTO = new BookDTO(name, writer, price, status);
        BookDAO bookId=new BookDAO();
        System.out.println("Book added successfully.");
        System.out.println("The Book ID: " + bookId.addBook(bookDTO)+ "\n--------------------------------------------- "
                + "\n Book name: " + bookDTO.getName() + "|Writer : " + bookDTO.getWriter() + "|prise: " + bookDTO.getPrice() + "|Status: " + bookDTO.getStatus());
    }


    public void showBooksList() throws SQLException {
        BookDAO bookDAO=new BookDAO();
        List<BookDTO> bookDTOList = bookDAO.showBooksList();
        for (BookDTO books : bookDTOList) {
            System.out.println(books.toString());
        }
    }

    public void editBook() throws SQLException {
        System.out.println("Enter the Book ID Please");
        int id = scanner.nextInt();
        scanner.nextLine();
        BookDTO bookDTO = BookDAO.findById(id);
        if (bookDTO != null) {
            System.out.println("Current Book Info:" + bookDTO.toString() + "\n-----------------------------------------------------------\n" +
                    "Enter New information\n      ||      \nEnter The Name:");
            String newName = scanner.nextLine();
            System.out.println("Enter Writer Name:");
            String newWriter = scanner.nextLine();
            System.out.println("Enter Price:");
            int newPrice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter new status (AVAILABLE / NEEDREPARE / BORROW):");
            String newStatus = scanner.nextLine().toUpperCase();
            if (newName == null || newWriter == null || newPrice < 0) {
                System.out.println("fields are empty!!");
            } else {
                BookDTO newBookDTO = new BookDTO(newName,newWriter,newPrice,newStatus);
                BookDAO bookDAO=new BookDAO();
                int getToDao = bookDAO.editBook(newBookDTO, id);
                if (getToDao == 0) {
                    System.out.println("Failed to update the Book!!");
                } else {
                    System.out.println("Book updated successfully\n---------------------------------------------------------------");
                    System.out.println(BookDAO.findById(id).toString());
                }
            }
        }
    }

    public void deleteBook() throws SQLException {
        System.out.println("Enter the book ID");
        int id = scanner.nextInt();
        scanner.nextLine();
        BookDTO bookDTO = BookDAO.findById(id);
        if (bookDTO != null) {
            System.out.println(bookDTO.toString());
            System.out.println("If you are sure enter <<Y>>");
            String sure = scanner.nextLine().toUpperCase();
            if (sure.equals("Y")) {
                BookDAO bookDAO=new BookDAO();
                int delete = bookDAO.deleteBook(id);
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
        System.out.println("Enter The Book ID");
        int id = scanner.nextInt();
        scanner.nextLine();
        BookDTO bookDTO = BookDAO.findById(id);
        if (bookDTO == null) {
            System.out.println("Book not found!!");
        } else {
            System.out.println(bookDTO.toString());
        }
    }
}

