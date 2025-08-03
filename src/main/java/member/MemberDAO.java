package member;

import databaseConnection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public int addMember(MemberDTO memberDTO) throws SQLException {
        int generatedId = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO members (name,birthday,phone_number) VALUES (?,?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, memberDTO.getName());
            ps.setString(2, memberDTO.getBirthday());
            ps.setString(3, memberDTO.getPhoneNumber());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {//next pointer
                generatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return generatedId;
    }


    public List<MemberDTO> showMembersList() throws SQLException {
        List<MemberDTO> members = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM members WHERE name IS NOT null";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MemberDTO member = new MemberDTO(rs.getString(2), rs.getString(3), rs.getString(4));
                member.setId(rs.getInt(1));
                members.add(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return members;
    }

    public int editMember(MemberDTO newMember, int id) throws SQLException {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            String Sql = "UPDATE members SET name = ?, birthday = ?, phone_number = ? WHERE id=? ";
            ps = conn.prepareStatement(Sql);
            ps.setString(1, newMember.getName());
            ps.setString(2, newMember.getBirthday());
            ps.setString(3, newMember.getPhoneNumber());
            ps.setInt(4, id);
            int updatedRow = ps.executeUpdate();
            if (updatedRow == 0) {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return 1;
    }

    public int deleteMember(int id) throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "DELETE FROM members WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return 0;
    }


    public static MemberDTO findById(int id) throws SQLException {
        MemberDTO member = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM members WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                member = new MemberDTO(rs.getString(2), rs.getString(3), rs.getString(4));
                member.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return member;
    }

    public static MemberDTO findByName(String name) throws SQLException {
        MemberDTO member = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM members WHERE name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                member = new MemberDTO(rs.getString(2), rs.getString(3), rs.getString(4));
                member.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return member;
    }
}



