package core.utils;

public class EncryptorMain {

    public static void main(String[] args) {
        String password = "Password123!";
        String encrypted = EncryptionUtils.encrypt(password);
        System.out.println("зашифрованный пароль: " + encrypted);
    }
}
