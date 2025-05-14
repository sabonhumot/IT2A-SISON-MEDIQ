/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author Administrator
 */
public class pwHasher {
    
     public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());
        String encoded = Base64.getEncoder().encodeToString(hashBytes);
        return encoded;
    }
     
     
     public static boolean verifyPassword(String inputPassword, String storedHash) throws NoSuchAlgorithmException {
        String hashedInput = hashPassword(inputPassword); // Hash the input password
        return hashedInput.equals(storedHash); // Compare with stored hash
    }
    
    
}
