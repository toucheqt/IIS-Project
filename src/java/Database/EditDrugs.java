/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

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
public class EditDrugs {
    
    private static final String SELECT_DRUG_NAMES = "SELECT drugName FROM drugs";
    private static final String SELECT_DRUG_ID = "SELECT id FROM drugs WHERE drugName = ?";
    
    public static List<String> getDrugNames() throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        List<String> drugs = new ArrayList();
        String column = "drugName";
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_DRUG_NAMES);
        rs = stmt.executeQuery();
        
        for (int i = 0; rs.next(); i++) {
            drugs.add(rs.getString(column));
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return drugs;
        
    }
    
    public static int getDrugId(String name) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        String column = "id";
        int drugId;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_DRUG_ID);
        stmt.setString(1, name);
        rs = stmt.executeQuery();
        rs.next();
        
        drugId = rs.getInt(column);
        
        rs.close();
        stmt.close();
        connection.close();
        
        return drugId;
        
    }
    
}
