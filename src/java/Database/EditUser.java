/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.User;
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
public class EditUser {
    
    public static final String INSERT_USER = "INSERT INTO users (username, password) VALUES (?, ?)";
    public static final String INSERT_USER_ROLE = "INSERT INTO user_roles (username, rolename) VALUES (?, ?)";
    
    public void addUser(User user) throws SQLException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            String query = INSERT_USER;
            
            connection = Connect.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPasswd());
            
            stmt.executeUpdate();
        } 
        
        catch (NamingException | SQLException ex) {
            Logger.getLogger(EditUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        
    }
    
    public void addRole(User user) throws SQLException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            String query = INSERT_USER_ROLE;
            
            connection = Connect.getConnection();
            stmt = connection.prepareStatement(query);
            
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getRole());
            
            stmt.executeUpdate();
        }
        
        catch (NamingException | SQLException ex) {
            Logger.getLogger(EditUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        } 
    }
    
}
