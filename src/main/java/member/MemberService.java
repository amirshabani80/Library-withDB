package member;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class MemberService {
    static Scanner scanner = new Scanner(System.in);

    public void addMember() throws SQLException {
        String name;
        String phoneNumber;
        String birthday;
        do {
            System.out.println("Enter full name");
            name = scanner.nextLine();
            if (!MemberValidation.isValidName(name)) {
                System.out.println("Input name is incorrect!!\n");
            }
        } while (!MemberValidation.isValidName(name));
        do {
            System.out.println("Enter birthday (like 2022-12-14)");
            birthday = scanner.nextLine();
            if (!MemberValidation.isValidDate(birthday)) {
                System.out.println("Input date is incorrect!!\n");
            }
        } while (!MemberValidation.isValidDate(birthday));
        do {
            System.out.println("Enter phone number");
            phoneNumber = scanner.nextLine();
            if (!MemberValidation.isValidNumber(phoneNumber)) {
                System.out.println("\"Input number is incorrect!!\\n\"");
            }
        } while (!MemberValidation.isValidNumber(phoneNumber));
        MemberDTO memberDTO = new MemberDTO(name, birthday, phoneNumber);
        MemberDAO memberDAO = new MemberDAO();
        int memberId = memberDAO.addMember(memberDTO);
        if (memberId != -1) {
            System.out.println("ID: " + memberId + "\n----------------------------------------------\n" +
                    "Name: " + memberDTO.getName() + " | Birthday: " + memberDTO.getBirthday() +
                    " | Phone number: " + memberDTO.getPhoneNumber());
        }
    }

    public void showMembersList() throws SQLException {
        MemberDAO memberDAO = new MemberDAO();
        List<MemberDTO> memberDTOList = memberDAO.showMembersList();
        for (MemberDTO member : memberDTOList) {
            System.out.println(member.toString());
        }
    }

    public void editMember() throws SQLException {
        String id;
        do {
            System.out.println("Please Enter ID");
            id = scanner.nextLine();
            if (!MemberValidation.isValidNumber(id)) {
                System.out.println("Invalid ID!!\n");
            }
        } while (!MemberValidation.isValidNumber(id));
        MemberDTO member = MemberDAO.findById(Integer.parseInt(id));
        if (member != null) {
            System.out.println("Current member info:" + member);
            System.out.println("------------------------------------------------------------\n" +
                    "Enter New information\n      ||      \nEnter The Name:");
            String newName;
            String newBirthday;
            String newPhoneNumber;
            do {
                newName = scanner.nextLine();
                if (!MemberValidation.isValidName(newName)) {
                    System.out.println("Input name is incorrect!!\n");
                }
            } while (!MemberValidation.isValidName(newName));
            do {
                System.out.println("Enter birthday (like 2022-12-14)");
                newBirthday = scanner.nextLine();
                if (!MemberValidation.isValidDate(newBirthday)) {
                    System.out.println("Invalid Date!!");
                }
            } while (!MemberValidation.isValidDate(newBirthday));
            do {
                System.out.println("Enter phone number");
                newPhoneNumber = scanner.nextLine();
                if (!MemberValidation.isValidNumber(newPhoneNumber)) {
                    System.out.println("Invalid phone number!!");
                }
            } while (!MemberValidation.isValidNumber(newPhoneNumber));
            MemberDTO newMember = new MemberDTO(newName, newBirthday, newPhoneNumber);
            MemberDAO memberDAO = new MemberDAO();
            int getToDao = memberDAO.editMember(newMember, Integer.parseInt(id));
            if (getToDao == 0) {
                System.out.println("Failed to update the Member!!");
            } else {
                System.out.println("Member updated successfully\n------------------------------------------\n");
                System.out.println(MemberDAO.findById(Integer.parseInt(id)).toString());
            }
        } else System.out.println("Member not found!!");
    }

    public void deleteMember() throws SQLException {
        String id;
        do {
            System.out.println("Please Enter ID");
            id = scanner.nextLine();
            if (!MemberValidation.isValidNumber(id)) {
                System.out.println("Invalid ID!!\n");
            }
        } while (!MemberValidation.isValidNumber(id));
        MemberDTO member = MemberDAO.findById(Integer.parseInt(id));
        if (member != null) {
            System.out.println(member);
            System.out.println("If you are sure enter <<Y>>");
            String sure = scanner.nextLine().toUpperCase();
            if (sure.equals("Y")) {
                MemberDAO memberDAO = new MemberDAO();
                int delete = memberDAO.deleteMember(Integer.parseInt(id));
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
        System.out.println("Enter The Member name or ID");
        String input = scanner.next();
        MemberDTO memberDTO;
        if (input.matches("\\d+")) {
            memberDTO = MemberDAO.findById(Integer.parseInt(input));
        } else {
            memberDTO = MemberDAO.findByName(input);
        }
        if (memberDTO == null) {
            System.out.println("Member not found!!");
        } else {
            System.out.println(memberDTO);
        }
    }

    public static MemberDTO findMember(int id) throws SQLException {
        return MemberDAO.findById(id);
    }
}
