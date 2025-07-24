package Member;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

import DB.DBConnection;

public class MemberService {
    public static void addMember() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter full name");
        String name = scanner.nextLine();
        System.out.println("Enter birthday (like 2022/12/14)");
        String birthday = scanner.nextLine();
        System.out.println("Enter phone number");
        String phoneNumber = scanner.nextLine();
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName(name);
        memberDTO.setBirthday(birthday);
        memberDTO.setPhoneNumber(phoneNumber);
        MemberDAO.addMember(memberDTO);
        int memberId = MemberDAO.addMember(memberDTO);
        if (memberId != -1) {
            System.out.println("ID: " + memberId + "\n----------------------------------------------\n" +
                    "Name: " + memberDTO.getName() + " | Birthday: " + memberDTO.getBirthday() + " | Phone number: " + memberDTO.getPhoneNumber());

        }

    }

    public static void showMembers() throws SQLException {
        List<MemberDTO> memberDTOListList = MemberDAO.showMembers();
        for (MemberDTO member : memberDTOListList) {
            System.out.println("ID: " + member.getId()
                    + " | Name: " + member.getName()
                    + " | Birthday: " + member.getBirthday()
                    + " | Phone: " + member.getPhoneNumber());
        }
    }

    public void editMember() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter ID");
        int id = scanner.nextInt();
        scanner.nextLine();
        MemberDTO member = MemberDAO.findById(id);
        if (member != null) {
            System.out.println("Current member info:" + member.toString());
            System.out.println("------------------------------------------------------------\n" +
                    "New information\n      ||      \nEnter The Name:");
            String newName = scanner.nextLine();
            System.out.println("Enter birthday (like 2022/12/14)");
            String newBirthday = scanner.nextLine();
            System.out.println("Enter phone number");
            String newPhoneNumber = scanner.nextLine();
            if (newName == null || newBirthday == null || newPhoneNumber == null) {
                System.out.println("fields are empty!!");
            } else {
                MemberDTO newMember = new MemberDTO();
                newMember.setName(newName);
                newMember.setBirthday(newBirthday);
                newMember.setPhoneNumber(newPhoneNumber);
                int getToDao = MemberDAO.editMember(newMember, id);
                if (getToDao == 0) {
                    System.out.println("Failed to update the Member!!");
                } else {
                    System.out.println("Member updated successfully");
                }
            }
        } else System.out.println("Member not found!!");
    }

    public static void deleteMember() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID");
        int id = scanner.nextInt();
        scanner.nextLine();
        MemberDTO member = MemberDAO.findById(id);
        if (member != null) {
            System.out.println(member.toString());
            System.out.println("If you are sure enter <<Y>>");
            String sure = scanner.nextLine().toUpperCase();
            if (sure.equals("Y")) {
               int delete= MemberDAO.deleteMember(id);
                if (delete==1){
                    System.out.println("Member deleted successfully");
                } else {
                    System.out.println("Deletion failed!!");
                }
                }

             else {
                System.out.println("Deletion canceled");
            }

        } else {
            System.out.println("Member not found!!");
        }


       /* try {
            Connection conn = DBConnection.getConnection();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter ID");
            int id = scanner.nextInt();
            scanner.nextLine();
            String sql = "SELECT * FROM members WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + "|Name: " + rs.getString("name") + "|Birthday: "
                        + rs.getString("birthday") + "|Phone number: " + rs.getString("phone_number"));
                System.out.println("If you are sure enter <<Y>>");
                String sure = scanner.nextLine().toUpperCase();
                if (sure.equals("Y")) {
                    String delSql = "DELETE FROM members WHERE id=?";
                    PreparedStatement delPs = conn.prepareStatement(delSql);
                    delPs.setInt(1, id);
                    int rowsDeleted = delPs.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Member deleted successfully");
                    } else {
                        System.out.println("Delition failed!!");
                    }

                    delPs.close();
                } else {
                    System.out.println("Delotion canceled");
                }

            } else {
                System.out.println("Member not found!!");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
