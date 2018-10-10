package main.java.validation;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {

    public static String getHash(String password) {

        byte[] bytes = password.getBytes();
        String algorithm = "SHA-256";
        String hashValue = null;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(bytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No such algorithm Exception.");
        }
        return hashValue;
    }

}
