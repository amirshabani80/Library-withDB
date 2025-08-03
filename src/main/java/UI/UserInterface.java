package UI;

import book.BookService;
import borrow.BorrowService;
import member.MemberService;

import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {
    static Scanner scanner = new Scanner(System.in);
    static int password = 123456;

    public static void main(String[] args) throws SQLException {
        int input;
        do {
            System.out.println("🔐 Please Enter the password 🔐");
            input = scanner.nextInt();
            scanner.nextLine();
            if (input != password)
                System.out.println("❌❌The entry password is incorrect❌❌");
        } while (input != password);

        System.out.println("Welcome Boss");
        while (true) {
            System.out.println("------------MAIN MENU-----------" +
                    "\n 1) Members Management \n 2) Books Management \n 3) Borrow Management \n 4) Exit");
            System.out.println("---------------------------\n<<Enter The Menu Option Number>>");
            int menuNumber = scanner.nextInt();
            switch (menuNumber) {
                case 1:
                    manageMembers();
                    break;
                case 2:
                    manageBooks();
                    break;
                case 3:
                    manageBorrows();
                    break;
                case 4:
                    System.out.println("GOODBYE!!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("The entered option is incorrect.");
            }
        }
    }

    public static void manageMembers() throws SQLException {
        boolean inMembersMenu = true;
        while (inMembersMenu) {
            System.out.println("------------MEMBERS-----------\n 1) Add  \n 2) Show Members list \n " +
                    "3) Edit  \n 4) Delete  \n 5) Search Member \n 6) Back to Main Menu");
            System.out.println("---------------------------\n<<Enter The Menu Option Number>>");
            int menuNumber = scanner.nextInt();
            MemberService memberService = new MemberService();
            switch (menuNumber) {
                case 1:
                    memberService.addMember();
                    break;
                case 2:
                    memberService.showMembersList();
                    break;
                case 3:
                    memberService.editMember();
                    break;
                case 4:
                    memberService.deleteMember();
                    break;
                case 5:
                    memberService.searchMember();
                    break;
                case 6:
                    inMembersMenu = false;
                    break;
                default:
                    System.out.println("The entered option is incorrect.");
                    break;
            }
        }
    }


    public static void manageBooks() throws SQLException {
        boolean inBooksMenu = true;
        while (inBooksMenu) {
            System.out.println("------------BOOKS-----------\n 1) Add  \n 2) Show Books list  \n 3) Edit " +
                    " \n 4) Delete  \n 5) Search Book \n 6) Back to Main Menu");
            System.out.println("---------------------------\n<<Enter The Menu Option Number>>");

            int bookMenuNumber = scanner.nextInt();
            BookService bookService = new BookService();
            switch (bookMenuNumber) {

                case 1:
                    bookService.addBook();
                    break;
                case 2:
                    bookService.showBooksList();
                    break;
                case 3:
                    bookService.editBook();
                    break;
                case 4:
                    bookService.deleteBook();
                    break;
                case 5:
                    bookService.searchBook();
                    break;
                case 6:
                    inBooksMenu = false;
                    break;
                default:
                    System.out.println("The entered option is incorrect.");
            }

        }
    }

    public static void manageBorrows() throws SQLException {
        boolean borrowingMenu = true;
        while (borrowingMenu) {
            System.out.println("---------BORROWING---------- " +
                    "\n 1) Borrow a Book \n 2) Show Borrow List \n 3) returned Book \n 4) Show delayed books \n 5) Back to Main Menu");
            System.out.println("---------------------------\n<<Enter The Menu Option Number>>");
            int borrowMenuNumber = scanner.nextInt();

            BorrowService borrowService = new BorrowService();
            switch (borrowMenuNumber) {
                case 1:
                    borrowService.borrowBook();
                    break;
                case 2:
                    borrowService.showBorrowList();
                    break;
                case 3:
                    borrowService.returnBook();
                    break;
                case 4:
                    borrowService.showDelayedBooks();
                case 5:
                    borrowingMenu = false;
                    break;
                default:
                    System.out.println("The entered option is incorrect.");
            }

        }
    }
}
