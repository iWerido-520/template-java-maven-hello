package org.example;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordManager {
    private static final String ALGORITHM = "MD5";
    public static String hashPasswordWithSalt(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(salt); // 添加盐
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    public static byte[] generateSalt() {
        byte[] salt = new byte[16];
        new java.security.SecureRandom().nextBytes(salt);
        return salt;
    }

    public static boolean verifyPasswordWithSalt(String enteredPassword, byte[] salt, String storedHash) {
        String hashOfInput = hashPasswordWithSalt(enteredPassword, salt);
        return hashOfInput.equals(storedHash);
    }
}