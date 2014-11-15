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
    
    public static int getDepartmentId(String departmentName) throws NamingException, SQLException {
               
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int depId;
        String column = "id";
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_ID);
        stmt.setString(1, departmentName);
        rs = stmt.executeQuery();
        rs.next();
        depId = rs.getInt(column);
        
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (connection != null) connection.close();
        
        return depId;
        
    }
    
    public static List<String> getDepartments() throws NamingException, SQLException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> names = new ArrayList();
        String column = "depName";
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_NAME);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            names.add(rs.getString(column));
        }
        
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (connection != null) connection.close();
        
        return names;
               
    }
    
}
