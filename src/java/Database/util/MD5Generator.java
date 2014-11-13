/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Touche
 */
public class MD5Generator {
    
    private static MessageDigest md;
    
    public static String generatePassword(String passwd) {
        
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5Generator.class.getName()).log(Level.SEVERE, null, ex);
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
