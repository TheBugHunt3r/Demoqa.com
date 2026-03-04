package core.config;

public class TokenManager {

    private static ThreadLocal<String> token = new ThreadLocal<>();
    private static ThreadLocal<String> userId = new ThreadLocal<>();

    public static void setToken(String value) {
        token.set(value);
    }

    public static String getToken() {
        return token.get();
    }

    public static void removeToken() {
        token.remove();
    }

    public static void setUserId(String id) {
        userId.set(id);
    }

    public static String getUserId() {
        return userId.get();
    }

    public static void clear() {
        token.remove();
        userId.remove();
    }
}
