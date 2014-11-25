/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Touche
 */
public class MD5Generator {
    
    private static MessageDigest md;
    
    public static final String HASH_ALGORITHM = "MD5";
    
    public static String generatePassword(String passwd) {
        
        try {
            md = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            // TODO zpracovat vyjimku
        }
        
        md.update(passwd.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        
        return sb.toString();
        
    }
    
}
