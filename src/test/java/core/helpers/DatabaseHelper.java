package core.helpers;

import core.config.ConfigManager;
import lombok.extern.slf4j.Slf4j;
import java.sql.*;

@Slf4j
public class DatabaseHelper {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                ConfigManager.getConfig().dbUrl(),
                ConfigManager.getConfig().dbUser(),
                ConfigManager.getConfig().dbPassword());
    }

    public static String getDataFromDb(String columnName, String username) {
        String sql = "SELECT " + columnName + " FROM data WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String result = rs.getString(columnName);
                    log.info("Из БД получено значение '{}' для пользователя '{}'", result, username);
                    return result;
                }
            }
        } catch (SQLException e) {
            log.info("Ошибка при получении данных из БД (column: {}, user: {}): ", columnName, username, e);
        }
        return null;
    }

    public static void ensureUserExists(String username, String password) {
        String sql = "INSERT INTO data (username, password) VALUES (?, ?) " +
                "ON CONFLICT (username) DO UPDATE SET password = EXCLUDED.password";
        executeUpdate(sql, username, password);
        log.info("Пользователь '{}' подготовлен в базе данных", username);
    }

    public static void executeUpdate(String sql, String... params) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setString(i + 1, params[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.info("Ошибка выполнения SQL запроса: {} | Параметры: {}", sql, params, e);
        }
    }

    public static void prepareTestData(String user, String pass, String book) {
        String sql = "INSERT INTO data (username, password, target_book) VALUES (?, ?, ?) " +
                "ON CONFLICT (username) DO UPDATE SET target_book = EXCLUDED.target_book";
        executeUpdate(sql, user, pass, book);
        log.info("Данные для теста книги '{}' подготовлены в БД", book);
    }
}
