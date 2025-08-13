package member;

import borrow.*;
import staticStrings.*;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class MemberService {
    static Scanner scanner = new Scanner(System.in);

    public void addMember() throws SQLException {
        String name = takeName();
        String phoneNumber=takePhoneNumber();
        String birthday=takeBirthday();

        MemberDTO memberDTO = new MemberDTO(name, birthday, phoneNumber);
        MemberDAO memberDAO = new MemberDAO();
        Integer memberId = memberDAO.addMember(memberDTO);
        if (memberId != -1) {
            Strings.memberAdded(memberId, memberDTO.getName(), memberDTO.getBirthday(), memberDTO.getPhoneNumber());
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
        String id=takeMemberId();
        MemberDTO member = MemberDAO.findById(Integer.parseInt(id));
        if (member != null) {
            Strings.currentMemberInfo(member);
            Strings.newMemberInf();
            String newName=takeNewName();
            String newBirthday=takeNewBirthday();
            String newPhoneNumber=takePhoneNumber();
            MemberDTO newMember = new MemberDTO(newName, newBirthday, newPhoneNumber);
            MemberDAO memberDAO = new MemberDAO();
            Integer getToDao = memberDAO.editMember(newMember, Integer.parseInt(id));
            if (getToDao == 0) {
                Strings.failUpdate();
            } else {
                Strings.memberUpdated();
                System.out.println(MemberDAO.findById(Integer.parseInt(id)).toString());
            }
        } else Strings.memberNotFound();
    }

    public void deleteMember() throws SQLException {
        String id=takeMemberId();
        MemberDTO member = MemberDAO.findById(Integer.parseInt(id));
        if (member != null) {
            System.out.println(member);
            Strings.sure();
            String sure = scanner.nextLine().toUpperCase();
            if (sure.equals("Y")) {
                MemberDAO memberDAO = new MemberDAO();
                int delete = memberDAO.deleteMember(Integer.parseInt(id));
                if (delete == 1) {
                    Strings.memberSuccessDel();
                } else {
                    Strings.failDel();
                }
            } else {
                Strings.cancelDel();
            }
        } else {
            Strings.memberNotFound();
        }
    }

    public void searchMember() throws SQLException {
        Strings.enterMemberNorId();
        String input = scanner.next();
        MemberDTO memberDTO;
        if (input.matches("\\d+")) {
            memberDTO = MemberDAO.findById(Integer.parseInt(input));
        } else {
            memberDTO = MemberDAO.findByName(input);
        }
        if (memberDTO == null) {
            Strings.memberNotFound();
        } else {
            System.out.println(memberDTO);
        }
    }

    public static String takeMemberId() {
        String memberId;
        do {
            Strings.enterMemberId();
            memberId = scanner.nextLine();
            if (!BorrowValidation.isValidNumber(memberId)) {
                Strings.invalidId();
            }
        } while (!BorrowValidation.isValidNumber(memberId));
        return memberId;
    }

    public String takeName() {
        String name;
        do {
            Strings.enterMemberName();
            name = scanner.nextLine();
            if (!MemberValidation.isValidName(name)) {
                Strings.invalidName();
            }
        } while (!MemberValidation.isValidName(name));
        return name;
    }

    public String takeBirthday() {
        String birthday;
        do {
            Strings.enterBirthday();
            birthday = scanner.nextLine();
            if (!MemberValidation.isValidDate(birthday)) {
                Strings.invalidDate();
            }
        } while (!MemberValidation.isValidDate(birthday));
        return birthday;
    }
    public String takePhoneNumber(){
        String phoneNumber;
        do {
            Strings.enterPhone();
            phoneNumber = scanner.nextLine();
            if (!MemberValidation.isValidNumber(phoneNumber)) {
                Strings.invalidNumber();
            }
        } while (!MemberValidation.isValidNumber(phoneNumber));
        return phoneNumber;
    }
    public String takeNewName(){
        String newName;
        do {
            newName = scanner.nextLine();
            if (!MemberValidation.isValidName(newName)) {
                Strings.invalidName();
            }
        } while (!MemberValidation.isValidName(newName));
        return newName;
    }
    public String takeNewBirthday(){
        String newBirthday;
        do {
            Strings.enterBirthday();
            newBirthday = scanner.nextLine();
            if (!MemberValidation.isValidDate(newBirthday)) {
                Strings.invalidDate();
            }
        } while (!MemberValidation.isValidDate(newBirthday));
        return newBirthday;
    }
    public String takeNewPhoneNmber(){
        String newPhoneNumber;
        do {
            Strings.enterPhone();
            newPhoneNumber = scanner.nextLine();
            if (!MemberValidation.isValidNumber(newPhoneNumber)) {
                Strings.invalidNumber();
            }
        } while (!MemberValidation.isValidNumber(newPhoneNumber));
        return newPhoneNumber;
    }

    public static MemberDTO findMember(int id) throws SQLException {
        return MemberDAO.findById(id);
    }
}
