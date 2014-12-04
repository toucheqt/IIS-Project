/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Hospitalization;
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
public class EditHospitalization {
    
    public static final String SELECT_HOSPITALIZATION = "SELECT H.hospitalized, H.released, D.depName, U.username, U.surname"
            + " FROM hospitalization H"
            + " JOIN department D ON H.departmentNum = D.id"
            + " JOIN usertable U ON H.doctor = U.email"
            + " WHERE H.patientId = ?";
    
    public static List<Hospitalization> getHospitalizations(int patientId) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        List<Hospitalization> hospitalization = new ArrayList();
        String[] columns = {"H.hospitalized", "H.released", "D.depName", "U.username", "U.surname"};
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_HOSPITALIZATION);
        stmt.setInt(1, patientId);
        rs = stmt.executeQuery();
        
        for (int i = 0; rs.next(); i++) {
            hospitalization.add(new Hospitalization(rs.getDate(columns[0]), rs.getDate(columns[1]),
                    rs.getString(columns[2]), rs.getString(columns[3]), rs.getString(columns[4])));
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return hospitalization;
        
    }
    
}
