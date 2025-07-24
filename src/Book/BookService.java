package Book;
import DB.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class BookService {
    static Scanner scanner = new Scanner(System.in);

    public void addBook() {

        try (Connection conn = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Book Name:");
            String name = scanner.nextLine();

            System.out.println("Enter Writer Name:");
            String writer = scanner.nextLine();

            System.out.println("Enter Price:");
            int price = scanner.nextInt();
            scanner.nextLine();

            String status = "AVAILABLE";

            String sql = "INSERT INTO books (name, writer, price, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, writer);
            ps.setInt(3, price);
            ps.setString(4, status);

            ps.executeUpdate();
            System.out.println("Book added successfully.");
            ResultSet rs = ps.getGeneratedKeys();//getId after add
            if (rs.next()) {//next pointer
                int generatedId = rs.getInt(1);
                System.out.println("The Book ID: " + generatedId + "\n--------------------------------------------- "
                        + "\n Book name: " + name + "|Writer : " + writer + "|prise: " + price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showBooksList() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM books WHERE name is not null";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String writer = rs.getString("writer");
                int price = rs.getInt("price");
                String status = rs.getString("status");

                System.out.println("Book: " + name +
                        " | Writer: " + writer +
                        " | Price: " + price +
                        " | Status: " + status +
                        " |ID: " + rs.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editBook() {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Enter the Book Name Please");
            String name = scanner.nextLine();
            String sql = "SELECT * FROM books WHERE LOWER(name) = LOWER(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Current Book Info:");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Writer: " + rs.getString("writer"));
                System.out.println("Price: " + rs.getInt("price"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("----------------------------------");

                System.out.println("Enter The Books Name:");
                String newName = scanner.nextLine();
                System.out.println("Enter Writer Name:");
                String newWriter = scanner.nextLine();

                System.out.println("Enter Price:");
                int newPrice = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter new status (AVAILABLE / NEEDREPARE / BORROW):");
                String newStatus = scanner.nextLine().toUpperCase();

                String updateSql = "UPDATE books SET name = ?, writer = ?, price = ?, status = ? WHERE name = ?";

                PreparedStatement newPs = conn.prepareStatement(updateSql);
                newPs.setString(1, newName);
                newPs.setString(2, newWriter);
                newPs.setInt(3, newPrice);
                newPs.setString(4, newStatus);
                newPs.setString(5, name);
                int updatedRows = newPs.executeUpdate();
                if (updatedRows > 0) {
                    System.out.println("Book updated successfully");
                } else {
                    System.out.println("Failed to update the book!!");
                }
            } else {
                System.out.println("Book not found!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Enter the book name");
            String bookName = scanner.nextLine();
            String sql = "SELECT * FROM books WHERE LOWER(name)=LOWER (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bookName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Name : " + rs.getString("name") + "|Writer: " + rs.getString("writer") + "|Prise: "
                        + rs.getInt("price") + "|Status: " + rs.getString("status") + "|ID: " + rs.getInt("id"));
                System.out.println("If you are sure enter <<Y>>");
                String sure = scanner.nextLine().toUpperCase();
                if (sure.equals("Y")) {
                    String deleteSql = "DELETE FROM books WHERE LOWER(name)=LOWER(?)";
                    PreparedStatement deletePs = conn.prepareStatement(deleteSql);
                    deletePs.setString(1, bookName);
                    int rowsDeleted = deletePs.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Book deleted successfully.");
                    } else {
                        System.out.println("Deletion failed.");
                    }
                    deletePs.close();
                } else {
                    System.out.println("Deletion canceled.");
                }

            } else {
                System.out.println("The Book NOT found!!!");
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void searchBook() {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Enter The Book Name");
            String name = scanner.nextLine();

            String sql = "SELECT * FROM books WHERE LOWER(name) = LOWER(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Name : " + rs.getString("name"));
                System.out.println("Writer : " + rs.getString("writer"));
                System.out.println("Prise : " + rs.getInt("price"));
                System.out.println("Status : " + rs.getString("status"));
                System.out.println("ID : " + rs.getInt("id"));
            } else {
                System.out.println("The Book NOT found!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

