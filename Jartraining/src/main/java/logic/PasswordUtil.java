package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase utilitaria para el hashing de contraseñas usando SHA-256.
 * Centraliza la lógica de encriptación para mantener consistencia
 * en toda la aplicación.
 */
public class PasswordUtil {

    /**
     * Genera un hash SHA-256 de la contraseña proporcionada.
     * 
     * @param password La contraseña en texto plano a hashear.
     * @return El hash SHA-256 en formato hexadecimal (64 caracteres).
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar hash SHA-256", e);
        }
    }
}
