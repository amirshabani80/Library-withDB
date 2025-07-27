package member;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class MemberService {
    static Scanner scanner = new Scanner(System.in);

    public void addMember() throws SQLException {
        System.out.println("Enter full name");
        String name = scanner.nextLine();
        System.out.println("Enter birthday (like 2022/12/14)");
        String birthday = scanner.nextLine();
        System.out.println("Enter phone number");
        String phoneNumber = scanner.nextLine();
        MemberDTO memberDTO = new MemberDTO(name, birthday, phoneNumber);
        MemberDAO memberDAO=new MemberDAO();
        int memberId = memberDAO.addMember(memberDTO);
        if (memberId != -1) {
            System.out.println("ID: " + memberId + "\n----------------------------------------------\n" +
                    "Name: " + memberDTO.getName() + " | Birthday: " + memberDTO.getBirthday() + " | Phone number: " + memberDTO.getPhoneNumber());

        }

    }

    public void showMembersList() throws SQLException {
        MemberDAO memberDAO=new MemberDAO();
        List<MemberDTO> memberDTOList = memberDAO.showMembersList();
        for (MemberDTO member : memberDTOList) {
            System.out.println(member.toString());
        }
    }

    public void editMember() throws SQLException {
        System.out.println("Please Enter ID");
        int id = scanner.nextInt();
        scanner.nextLine();
        MemberDTO member = MemberDAO.findById(id);
        if (member != null) {
            System.out.println("Current member info:" + member);
            System.out.println("------------------------------------------------------------\n" +
                    "Enter New information\n      ||      \nEnter The Name:");
            String newName = scanner.nextLine();
            System.out.println("Enter birthday (like 2022/12/14)");
            String newBirthday = scanner.nextLine();
            System.out.println("Enter phone number");
            String newPhoneNumber = scanner.nextLine();
            if (newName == null || newBirthday == null || newPhoneNumber == null) {
                System.out.println("fields are empty!!");
            } else {
                MemberDTO newMember = new MemberDTO(newName, newBirthday, newPhoneNumber);
                MemberDAO memberDAO=new MemberDAO();
                int getToDao = memberDAO.editMember(newMember, id);
                if (getToDao == 0) {
                    System.out.println("Failed to update the Member!!");
                } else {
                    System.out.println("Member updated successfully\n------------------------------------------\n");
                    System.out.println(MemberDAO.findById(id).toString());
                }
            }
        } else System.out.println("Member not found!!");
    }

    public void deleteMember() throws SQLException {
        System.out.println("Enter ID");
        int id = scanner.nextInt();
        scanner.nextLine();
        MemberDTO member = MemberDAO.findById(id);
        if (member != null) {
            System.out.println(member);
            System.out.println("If you are sure enter <<Y>>");
            String sure = scanner.nextLine().toUpperCase();
            if (sure.equals("Y")) {
                MemberDAO memberDAO=new MemberDAO();
                int delete = memberDAO.deleteMember(id);
                if (delete == 1) {
                    System.out.println("Member deleted successfully");
                } else {
                    System.out.println("Deletion failed!!");
                }
            } else {
                System.out.println("Deletion canceled");
            }
        } else {
            System.out.println("Member not found!!");
        }
    }

    public void searchMember() throws SQLException {
        System.out.println("Enter The Member ID");
        int id = scanner.nextInt();
        scanner.nextLine();
        MemberDTO memberDTO = MemberDAO.findById(id);
        if (memberDTO == null) {
            System.out.println("Book not found!!");
        } else {
            System.out.println(memberDTO);
        }
    }
}
