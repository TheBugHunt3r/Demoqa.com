package core.config;

public class TokenManager {

    private static String token;
    private static String userId;

    public static void setToken(String value) {
        token = value;
        System.out.println("токен записан в TokenManager: " + value);
    }

    public static String getToken() {
        return token;
    }

    public static void setUserId(String id) {
        userId = id;
    }

    public static String getUserId() {
        return userId;
    }

    public static void clear() {
        token = null;
        userId = null;
    }
}