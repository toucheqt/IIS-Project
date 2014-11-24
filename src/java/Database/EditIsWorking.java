/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Database.util.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Touche
 */
public class EditIsWorking {
    
    public static String ASSIGN_DOCTOR_W_TEL = "INSERT INTO isworking (tel, workingTime, departmentNum, doctor)"
            + " VALUES (?, ?, ?, ?)";
    public static String ASSIGN_DOCTOR = "INSERT INTO isworking (workingTime, departmentNum, doctor)"
            + " VALUES(?, ?, ?)";
    public static String DELETE_DOC_WORK = "DELETE FROM isworking WHERE departmentNum = ? AND doctor = ?";
    
    private EditIsWorking() {};
            
    public static void assignDoctor(Integer telNum, String workingTime, int departmentNum, String docEmail) 
            throws NamingException, SQLException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        connection = Connect.getConnection();
        
        if (telNum == null) {
            stmt = connection.prepareStatement(ASSIGN_DOCTOR);
            stmt.setString(1, workingTime);
            stmt.setInt(2, departmentNum);
            stmt.setString(3, docEmail);
        }
        
        else {
            stmt = connection.prepareStatement(ASSIGN_DOCTOR_W_TEL);
            stmt.setInt(1, telNum);
            stmt.setString(2, workingTime);
            stmt.setInt(3, departmentNum);
            stmt.setString(4, docEmail);
        }
        
        stmt.executeUpdate();
        
        if (stmt != null) stmt.close();
        if (connection != null) connection.close();
        
        return;
        
    }
    
    public static void removeDocWork(String email, String depName) throws SQLException, NamingException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(DELETE_DOC_WORK);
        stmt.setInt(1, EditDepartment.getDepartmentId(depName));
        stmt.setString(2, email);
        stmt.executeUpdate();
        
        if (stmt != null) stmt.close();
        if (connection != null) stmt.close();
                
    }
           
}
