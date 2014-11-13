/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Database.util.Connect;
import Models.Nurse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Touche
 */
public class EditNurse {
    
    public static final String INSERT_NURSE = "INSERT INTO nurse (username, surname, birthNum, address, city,"
            + "departmentNum) VALUES (?, ?, ?, ?, ?, ?)";
    
    public void addNurse(Nurse nurse) throws SQLException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            connection = Connect.getConnection();
            stmt = connection.prepareCall(INSERT_NURSE);
            stmt.setString(1, nurse.getUsername());
            stmt.setString(2, nurse.getSurname());
            stmt.setString(3, nurse.getBirthNum());
            stmt.setString(4, nurse.getAddress());
            stmt.setString(5, nurse.getCity());
            stmt.setInt(6, nurse.getDepartmentNum());
            
            stmt.executeUpdate();
        }
        
        catch (NamingException ex) {
            Logger.getLogger(EditDoctor.class.getName()).log(Level.SEVERE, null, ex);
            // todo pri vyvolani vyjimek vypsat error zpravu
        }
        
        finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        
    }
        
}
