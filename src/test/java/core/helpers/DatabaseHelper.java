package core.helpers;

import core.config.ConfigManager;

import java.sql.*;

public class DatabaseHelper {

    public static String getDataFromDb(String columnName, String username) {
        String sql = "SELECT " + columnName + " FROM data WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(
                ConfigManager.getConfig().dbUrl(),
                ConfigManager.getConfig().dbUser(),
                ConfigManager.getConfig().dbPassword());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString(columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
