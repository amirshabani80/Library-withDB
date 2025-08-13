package staticStrings;

import book.*;
import member.*;

public final class Strings {

    public static void enterMemberName() {
        System.out.println("Enter full name");
    }

    public static void enterBirthday() {
        System.out.println("Enter birthday (like 2022-12-14)");
    }

    public static void enterPhone() {
        System.out.println("Enter phone number");
    }

    public static void invalidName() {
        System.out.println("Input name is incorrect!!\n");
    }

    public static void invalidDate() {
        System.out.println("Input date is incorrect!!\n");
    }


    public static void invalidNumber() {
        System.out.println("\"Input number is incorrect!!\\n\"");
    }

    public static void memberAdded(Integer memberId, String name, String birthday, String phoneNumber) {
        System.out.println("Member added successfully!\n" +
                "\n----------------------------------------------\n" + " ID: " + memberId +
                "\n Name: " + name + " | Birthday: " + birthday +
                " | Phone number: " + phoneNumber);
    }

    public static void enterId() {
        System.out.println("Please Enter ID");
    }

    public static void invalidId() {
        System.out.println("Invalid ID!!\n");
    }

    public static void currentMemberInfo(MemberDTO memberDTO) {
        System.out.println("Current member info:" + memberDTO);
    }

    public static void newMemberInf() {
        System.out.println("------------------------------------------------------------\n" +
                "Enter New information\n      ||      \nEnter The Name:");
    }

    public static void failUpdate() {
        System.out.println("Failed to update the Member!!");
    }

    public static void memberUpdated() {
        System.out.println("Member updated successfully\n------------------------------------------\n");
    }

    public static void memberNotFound() {
        System.out.println("Member not found!!");
    }

    public static void sure() {
        System.out.println("If you are sure enter <<Y>>");
    }

    public static void failDel() {
        System.out.println("Deletion failed!!");
    }

    public static void memberSuccessDel() {
        System.out.println("Member deleted successfully");
    }

    public static void cancelDel() {
        System.out.println("Deletion canceled");
    }

    public static void enterMemberNorId() {
        System.out.println("Enter The Member name or ID");
    }

    public static void enterBookId() {
        System.out.println("Enter the Book ID Please");
    }

    public static void enterMemberId() {
        System.out.println("Enter the Member ID Please");
    }

    public static void bookNotFound() {
        System.out.println("Book Not Found!!");
    }

    public static void notAvailable() {
        System.out.println("The Book is Not Available!!");
    }

    public static void borrowDate() {
        System.out.println("Enter Borrow Date (like 2022-1-24):");
    }

    public static void returnDate() {
        System.out.println("Enter Return Date (like 2022-1-24):");
    }

    public static void successBorrow() {
        System.out.println("The book has been successfully borrowed✔");
    }

    public static void failBorrow() {
        System.out.println("Borrowing operation failed!!!");
    }

    public static void isAvailable() {
        System.out.println("Now,The book is AVAILABLE!");
    }

    public static void late(Integer bookId, Long daysLate, Integer memberId, String returnDate) {
        System.out.println("Book ID: " + bookId + " is delayed by " + daysLate + " days.\n" +
                "Member ID: " + memberId +
                "\nReturn Date: " + returnDate + "------------------------------");
    }

    public static void notDelayBook() {
        System.out.println("No delayed books found.");

    }

    public static void enterBookName() {
        System.out.println("Enter Book Name:");
    }

    public static void writerName() {
        System.out.println("Enter Writer Name:");
    }

    public static void price() {
        System.out.println("Enter Price:");
    }

    public static void invalidPrice() {
        System.out.println("Invalid price!!");
    }

    public static void bookAdded(Integer bookDAO, String bookName, String writerName, Integer price, BookDTO.Status status) {
        System.out.println("Book added successfully.\n" + "The Book ID: " + bookDAO +
                "\n--------------------------------------------- "
                + "\n Book name: " + bookName + "|Writer : " + writerName +
                "|prise: " + price + "|Status: " + status);
    }

    public static void currentBook(BookDTO bookDTO) {
        System.out.println("Current Book Info:" + bookDTO);
    }

    public static void newBookInf() {
        System.out.println("\n-----------------------------------------------------------\n" +
                "Enter New information\n      ||      \nEnter The Name:");
    }
    public static void enterStat(){
        System.out.println("Enter new status (AVAILABLE / NEEDREPARE / BORROW):");
    }
    public static void failedBookUpdate(){
        System.out.println("Failed to update the Book!!");
    }
    public static void successBookUpdate(){
        System.out.println("Book updated successfully\n" +
                "---------------------------------------------------------------");
    }
    public static void delBookSuccess(){
        System.out.println("Book deleted successfully");
    }
    public static void enterBookNorId() {
        System.out.println("Enter The Book name or ID");
    }
}
