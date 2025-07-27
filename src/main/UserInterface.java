package main;

import borrow.LibraryService;
import member.MemberService;
import book.BookService;

import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("------------MAIN MENU-----------" +
                    "\n 1) Members Management \n 2) Books Management \n 3) Library Management \n 4) Exit");
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
                    manageLibrary();
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
        Scanner scanner = new Scanner(System.in);


        boolean inMembersMenu = true;
        while (inMembersMenu) {
            System.out.println("------------MEMBERS-----------\n 1) Add member \n 2) Show Members list \n " +
                    "3) Edit member \n 4) Delete member \n 5) Search Member \n 6) Back to Main Menu");
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
            Scanner scanner = new Scanner(System.in);
            System.out.println("------------BOOKS-----------\n 1) Add Book \n 2) Show Books list  \n 3) Edit Book" +
                    " \n 4) Delete Book \n 5) Search Book \n 6) Back to Main Menu");
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

    public static void manageLibrary() throws SQLException {
        boolean borrowingMenu = true;
        while (borrowingMenu) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("---------BORROWING---------- " +
                    "\n 1) Borrow a Book \n 2) Show Borrow List \n 3) returned Book \n 4) Back to Main Menu");
            System.out.println("---------------------------\n<<Enter The Menu Option Number>>");
            int borrowMenuNumber = scanner.nextInt();
            LibraryService libraryService = new LibraryService();
            switch (borrowMenuNumber) {
                case 1:
                    libraryService.borrowBook();
                    break;
                case 2:
                    libraryService.showBorrowList();
                    break;
                case 3:
                    libraryService.returnBook();
                    break;
                case 4:
                    borrowingMenu = false;
                    break;
                default:
                    System.out.println("The entered option is incorrect.");
            }
        }
    }
}
