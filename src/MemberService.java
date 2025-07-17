import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MemberService {
    public static void addMember() {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection conn = DBConnection.getConnection();
            System.out.println("Enter full name");
            String name = scanner.nextLine();
            System.out.println("Enter birthday (like 2022/12/14)");
            String birthday = scanner.nextLine();
            System.out.println("Enter phone number");
            String phoneNumber = scanner.nextLine();
            String sql = "INSERT INTO members (name,birthday,phone_number) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, birthday);
            ps.setString(3, phoneNumber);
            ps.executeUpdate();
            System.out.println("Member added successfully");
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {//next pointer
                int generatedId = rs.getInt(1);
                System.out.println("ID: " + generatedId + "\n----------------------------------------------\n" +
                        "Name: " + name + " | Birthday: " + birthday + " | Phone number: " + phoneNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showMembers() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM members WHERE name IS NOT null";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + "|Name: " + rs.getString("name") + "|Birthday: "
                        + rs.getString("birthday") + "|Phone number: " + rs.getString("phone_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editMember() {
        try {
            Connection conn = DBConnection.getConnection();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter ID");
            int id = scanner.nextInt();
            String sql = "SELECT * FROM members WHERE id =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Current member info:");
                System.out.println("Name: " + rs.getString("name") + "| Birthday: " + rs.getString("birthday") +
                        "| Phone number: " + rs.getString("phone_number") + "\n-------------------------------------------");
                System.out.println("Enter The Name:");
                String newName = scanner.nextLine();
                System.out.println("Enter birthday (like 2022/12/14)");
                String newBirthday = scanner.next();
                System.out.println("Enter phone number");
                String newPhoneNumber = scanner.nextLine();
                String updateSql = "UPDATE members SET name = ?, birthday = ?, phone_number = ?";
                PreparedStatement newPs = conn.prepareStatement(updateSql);
                newPs.setString(1, newName);
                newPs.setString(2, newBirthday);
                newPs.setString(3, newPhoneNumber);
                int updatedRows = newPs.executeUpdate();
                if (updatedRows > 0) {
                    System.out.println("Member updated successfully");
                } else {
                    System.out.println("Failed to update the Member!!");
                }
            } else {
                System.out.println("Member not found!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteMember() {
        try {
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
        }
    }
}
