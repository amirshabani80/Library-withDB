import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryService {
    static ArrayList<LibraryDTO> borrowList = new ArrayList<>();

    public static void borrowBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter member's ID to borrow a book");
        int memberId = scanner.nextInt();
        System.out.println("Enter Book Name");
        String bookName = scanner.next();
        MemberDTO member = MemberService.findById(memberId);
        BookDTO book = BookService.findBookByName(bookName);
        if (member == null) {
            System.out.println("Member Not Found!!");
            return;
        }
        if (book == null) {
            System.out.println("Book Not Found!!");
            return;
        }
        if (book.getStatus() != BookDTO.Status.AVAILABLE) {
            System.out.println("The Book is Not Available!!");
            return;
        }
        book.setStatus(BookDTO.Status.BORROW);
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setBook(book);
        libraryDTO.setMember(member);

        System.out.println("Enter Borrow Date (like 2022/1/24)");
        libraryDTO.setBorrowDate(scanner.next());
        System.out.println("Enter Return Date (like 2022/1/24)");
        libraryDTO.setReturnDate(scanner.next());
        borrowList.add(libraryDTO);

        System.out.println("The " + book.getName() + " Borrowed to " + member.getName());
    }

    public static void returnBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Book Name");
        String bookName = scanner.next();
        BookDTO book = BookService.findBookByName(bookName);
        if (book == null) {
            System.out.println("Book Not Found!!");
            return;
        }
        book.setStatus(BookDTO.Status.AVAILABLE);
        System.out.println("The " + book.getName() + " is Available!!");
        borrowList.remove(book);
    }

    public static void showBorrowList() {
        if (borrowList.isEmpty()) {
            System.out.println("The Member List is Empty");
        } else
            for (LibraryDTO s : borrowList) {
                System.out.println(s.toString());
            }

    }
}
