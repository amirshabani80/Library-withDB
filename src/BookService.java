import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class BookService {
    static ArrayList<BookDTO> booksList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public void addBook() {
        System.out.println("Enter The Book Name");
        BookDTO newBook = new BookDTO(scanner.next());
        System.out.println("Enter The Writer name");
        newBook.setWriter(scanner.next());
        System.out.println("Enter The Price");
        newBook.setPrice(scanner.nextInt());
        newBook.setStatus(BookDTO.Status.AVAILABLE);
        System.out.println("New Book Added: " + newBook.getInfo());
        booksList.add(newBook);
    }

    public void searchBook() {
        if (booksList.isEmpty()) {
            System.out.println("The Books List is Empty");
        } else if (booksList.get(0) != null) {
            System.out.println("Enter The Book Name");
            BookDTO bookDTO = findBookByName(scanner.next());
            System.out.println(bookDTO.getInfo());
        } else System.out.println("Invalid Book!!");
    }

    public void editBook() {
        System.out.println("Enter the Book Name Please");
        BookDTO bookService = findBookByName(scanner.next());
        System.out.println(bookService.getInfo()+"\n --------------------------------------");
        System.out.println("Enter New Name");
        bookService.setName(scanner.next());
        System.out.println("Enter New WriterNAme");
        bookService.setWriter(scanner.next());
        System.out.println("Enter New Price");
        bookService.setPrice(scanner.nextInt());
        System.out.println("New Book:" + bookService.getInfo());
    }

    public void showBooksList() {
        if (booksList.isEmpty()) {
            System.out.println("THE LIST IS EMPTY!!");

        } else
            for (BookDTO bookDTO : booksList) {
                System.out.println(bookDTO.getInfo());
            }
    }
    public void deleteBook() {
        System.out.println("Enter the Book Name:");
        BookDTO bookDTO = findBookByName(scanner.nextLine());
        booksList.remove(bookDTO);
        System.out.println("Book "+"<< " +bookDTO.getName()+" >>"+ " Deleted");

    }

    /**
     * find ook by name
     *
     * @param bookName
     * @return
     */
   static BookDTO findBookByName(String bookName) {
        for (BookDTO bookDTO : booksList) {
            if (bookDTO.getName().equalsIgnoreCase(bookName)) {
                return bookDTO;

            }
        }
        return null;
    }
}

