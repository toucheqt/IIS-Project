/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Touche
 */
public class MailSender {
    
    // todo why im using gmail when its not working
    public static final String ROOT_EMAIL = "iisproject014@gmail.com";
    public static final String HOST = "smtp.gmail.com";
    
    public static void sendEmail(String receiver, String passwd) throws NamingException, MessagingException {
                
        final String password = "iisproject";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props, 
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(ROOT_EMAIL, password);
                    }
                });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ROOT_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            //message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject("Bylo Vám vygenerováno heslo");
            message.setText("Dobrý den,\nbylo vám vygenerováno heslo pro přístup do systému nemocnice"
                    + " na Veleslavínově.\nLogin: " + receiver + "\nHeslo: " + passwd + "\n\nTato zpráva byla"
                    + " generována automaticky.");
            
            Transport.send(message);
        }
        
        catch (MessagingException ex) {
            ex.printStackTrace();
        }
        
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
