package domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import util.DBUtil;

public class AutoLoginDAO {
    public void saveToken(long mno, String token, LocalDateTime expireDate) {
        String sql = "INSERT INTO tbl_autologin (mno, token, voiddate) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, mno);
            pstmt.setString(2, token);
            pstmt.setString(3, expireDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long findMnoByToken(String token) {
        String sql = "SELECT mno FROM tbl_autologin WHERE token = ? AND voiddate > NOW()";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, token);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("mno");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteToken(long mno) {
        String sql = "DELETE FROM tbl_autologin WHERE mno = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, mno);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}