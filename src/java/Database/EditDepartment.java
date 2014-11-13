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
import javax.naming.NamingException;

/**
 *
 * @author Touche
 */
public class EditDepartment {
    
    public static String SELECT_ID = "SELECT id FROM department WHERE depName = ?";
    
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
    
}
