/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.UsedDrug;
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
public class EditPrescription {
    // TODO Date predelat z date do normalniho formatu
    public static String SELECT_PATIENTS_DRUGS = "SELECT P.startTime, P.endTime, P.dosage, D.*"
            + " FROM prescriptions P"
            + " JOIN drugs D ON P.drugId = D.id"
            + " WHERE P.patientId = ?";
            
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
    
}
