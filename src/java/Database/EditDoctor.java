/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Touche
 */
public class EditDoctor {
    
    public static final String INSERT_DOC = "INSERT INTO doctor (docName, docSurname, birthNum, address, city, tel, Email) VALUES (?, ?, ?, ?, ?, NULL, ?)";
    public static final String INSERT_DOC_WITH_TEL = "INSERT INTO doctor (docName, docSurname, birthNum, address, city, tel, Email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
    public void addDoctor(Doctor doctor) throws SQLException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            String query;
            
            if (doctor.getTelNum() != null) query = INSERT_DOC_WITH_TEL;
            else query = INSERT_DOC;
            
            connection = Connect.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSurname());
            stmt.setString(3, doctor.getBirthNum());
            stmt.setString(4, doctor.getAddress());
            stmt.setString(5, doctor.getCity());        
            if (doctor.getTelNum() != null) {
                stmt.setInt(6, doctor.getTelNum());
                stmt.setString(7, doctor.getMail());
            }
            
            else {
                stmt.setString(6, doctor.getMail());
            }
            
            stmt.executeUpdate();
        }
        
        catch (NamingException | SQLException ex) {
            Logger.getLogger(EditDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
 
    }

}
