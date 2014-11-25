/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Database.util.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Touche
 */
public class EditDepartment {
    
    public static String SELECT_ID = "SELECT id FROM department WHERE depName = ?";
    public static String SELECT_NAME = "SELECT depName FROM department";
    public static String SELECT_NAME_FROM_ID = "SELECT depName FROM department ON id == ?";
    
    public static int getDepartmentId(String departmentName) throws NamingException, SQLException {
               
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        int depId;
        String column = "id";
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_ID);
        stmt.setString(1, departmentName);
        rs = stmt.executeQuery();
        rs.next();
        depId = rs.getInt(column);
        
        rs.close();
        stmt.close();
        connection.close();
        
        return depId;
        
    }
    
    public static List<String> getDepartments() throws NamingException, SQLException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        List<String> names = new ArrayList();
        String column = "depName";
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_NAME);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            names.add(rs.getString(column));
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return names;
               
    }
    
    public static String getNameFromId(int id) throws NamingException, SQLException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        String name = null;
        String column = "depName";
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_NAME_FROM_ID);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            name = rs.getString(column);
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return name;
       
    }
    
}
