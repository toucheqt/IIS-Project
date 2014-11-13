/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Database.util.Connect;
import Models.Doctor;
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
public class EditDoctor {
    
    public static final String INSERT_USER = "INSERT INTO usertable (username, surname, birthNum, address,"
            + " city, email, tel, roleType, password) VALUES (?, ?, ?, ?, ?, ?, NULL, ?, ?)";
    public static final String INSERT_USER_WITH_TEL = "INSERT INTO usertable (username, surname, birthNum,"
            + " address, city, email, tel, roleType, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
    public static final String SELECT_ROLE_TYPE = "SELECT roleType FROM usertable WHERE email = ?";
    
    public void addDoctor(Doctor doctor) throws SQLException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            String query;
            
            if (doctor.getTel()!= null) query = INSERT_USER_WITH_TEL;
            else query = INSERT_USER;
            
            connection = Connect.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, doctor.getUsername());
            stmt.setString(2, doctor.getSurname());
            stmt.setString(3, doctor.getBirthNum());
            stmt.setString(4, doctor.getAddress());
            stmt.setString(5, doctor.getCity());
            stmt.setString(6, doctor.getEmail());
            
            if (doctor.getTel() != null) {
                stmt.setInt(7, doctor.getTel());
                stmt.setString(8, doctor.getRoleType());
                stmt.setString(9, doctor.getPassword());
            }
            
            else {
                stmt.setString(7, doctor.getRoleType());
                stmt.setString(8, doctor.getPassword());
            }
        
            stmt.executeUpdate();
        }
        
        catch (NamingException ex) {
            Logger.getLogger(EditDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
 
    }
    
    public static String getUserRole(String user) throws SQLException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String role = null;
        String column = "roleType";
        
        try {
            connection = Connect.getConnection();
            stmt = connection.prepareStatement(SELECT_ROLE_TYPE);
            stmt.setString(1, user);
            rs = stmt.executeQuery();
            
            rs.next();
            role = rs.getString(column);
        }
        
        catch (NamingException | SQLException ex) {
            Logger.getLogger(EditDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        
        return role;
        
    }

}
