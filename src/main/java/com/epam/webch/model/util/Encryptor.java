package com.epam.webch.model.util;

import com.epam.webch.exception.UtilException;
import com.epam.webch.model.entity.user.UserCredentials;
import jakarta.xml.bind.DatatypeConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Encryptor {
    private static final Logger logger = LogManager.getLogger();

    public static UserCredentials generateCredentials(String password) throws UtilException {
        byte[] salt = generateSalt();
        byte[] hashPass = generateHash(password, salt);
        String hashPassHex = Base64.getEncoder().encodeToString(hashPass);
        String saltHex = Base64.getEncoder().encodeToString(salt);
        UserCredentials userCredentials = new UserCredentials(hashPassHex, saltHex);
        return userCredentials;
    }

    public static String generateCredentials(String password, byte[] salt) throws UtilException {
        byte[] hashPass = generateHash(password, salt);
        String hashPassHex = Base64.getEncoder().encodeToString(hashPass);
        return hashPassHex;
    }

    private static byte[] generateHash(String password, byte[] salt) throws UtilException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        final String ALGORITHM_NAME = "PBKDF2WithHmacSHA1";
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM_NAME);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return hash;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.log(Level.ERROR, "Util exception in encryptPassword method. {}", e.getMessage());
            throw new UtilException("Service exception in encryptPassword method. " + e.getMessage());
        }
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
