package core.db;

import core.config.ConfigManager;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DatabaseClient {

    private final String url;
    private final String user;
    private final String password;

    public DatabaseClient() {
        this.url = ConfigManager.getConfig().dbUrl();
        this.user = ConfigManager.getConfig().dbUser();
        this.password = ConfigManager.getConfig().dbPassword();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn.isValid(2);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        DatabaseClient db = new DatabaseClient();
        log.info("подключение к БД: {}", db.testConnection());
    }
}
