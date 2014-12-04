/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.UsedDrug;
import java.sql.Connection;
import java.sql.Date;
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
public class EditPrescription {
    // TODO Date predelat z date do normalniho formatu
    public static final String SELECT_PATIENTS_DRUGS = "SELECT P.startTime, P.endTime, P.dosage, D.*"
            + " FROM prescriptions P"
            + " JOIN drugs D ON P.drugId = D.id"
            + " WHERE P.patientId = ?";
    public static final String ADD_PRESCRIPTION = "INSERT INTO prescriptions (startTime, endTime, dosage, patientId, drugId)"
            + " VALUES (?, ?, ?, ?, ?)";
            
    public static List<UsedDrug> getUsedDrugs(int patientId) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        List<UsedDrug> drugs = new ArrayList();
        String[] columns = {"P.startTime", "P.endTime", "P.dosage", "D.drugName", "D.contraindication",
                "D.description", "D.effectivity"};
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_PATIENTS_DRUGS);
        stmt.setInt(1, patientId);
        rs = stmt.executeQuery();
        
        for (int i = 0; rs.next(); i++) {
            drugs.add(new UsedDrug(rs.getString(columns[3]), rs.getString(columns[4]), rs.getString(columns[5]),
                    rs.getString(columns[6]), rs.getDate(columns[0]), rs.getDate(columns[1]), rs.getString(columns[2])));
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return drugs;
        
    }
    
    public static void addPrescription(int drugId, Date start, Date end, String dosage, int patientId) 
            throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(ADD_PRESCRIPTION);
        stmt.setDate(1, start);
        stmt.setDate(2, end);
        stmt.setString(3, dosage);
        stmt.setInt(4, patientId);
        stmt.setInt(5, drugId);
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
        
    }
    
}
