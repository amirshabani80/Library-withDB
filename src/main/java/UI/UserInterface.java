package UI;

import book.BookService;
import borrow.BorrowService;
import member.MemberService;
import staticStrings.*;

import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {
    static Scanner scanner = new Scanner(System.in);
    static int password = 123456;

    public static void main(String[] args) throws SQLException {
        Integer input;
        do {
            Strings.enterPass();
            input = scanner.nextInt();
            scanner.nextLine();
            if (input != password)
                Strings.passincorect();
        } while (input != password);

        Strings.welcome();
        while (true) {
            Strings.mainMenu();
            Integer menuNumber = scanner.nextInt();
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
                    Strings.goodby();
                    System.exit(0);
                    break;
                default:
                    Strings.enterOptionIncorrect();
            }
        }
    }

    public static void manageMembers() throws SQLException {
        boolean inMembersMenu = true;
        while (inMembersMenu) {
            Strings.memberMenu();
            Integer menuNumber = scanner.nextInt();
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
                    Strings.enterOptionIncorrect();
                    break;
            }
        }
    }


    public static void manageBooks() throws SQLException {
        boolean inBooksMenu = true;
        while (inBooksMenu) {
            Strings.booksMenu();
            Integer bookMenuNumber = scanner.nextInt();
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
                    Strings.enterOptionIncorrect();
            }

        }
    }

    public static void manageBorrows() throws SQLException {
        boolean borrowingMenu = true;
        while (borrowingMenu) {
            Strings.borrowMenu();
            Integer borrowMenuNumber = scanner.nextInt();

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
                    Strings.enterOptionIncorrect();
            }

        }
    }
}
