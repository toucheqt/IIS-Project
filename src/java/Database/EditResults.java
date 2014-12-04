/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Touche
 */
public class EditResults {
    
    public static final String ADD_RESULT = "INSERT INTO results (resultDate, resultDsc, examinationId)"
            + " VALUES (?, ?, ?)";
    
    public static void addResult(Date resultDate, String description, int examId) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(ADD_RESULT);
        stmt.setDate(1, resultDate);
        stmt.setString(2, description);
        stmt.setInt(3, examId);
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
        
    }
    
}
