/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Examination;
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
public class EditExamination {
    
    public static final String SELECT_PATIENT_EXAMINATION = "SELECT E.id, E.description, E.examTime, D.username, D.surname,"
            + " R.resultDate, R.resultDsc"
            + " FROM examination E"
            + " JOIN usertable D ON D.email = E.doctor"
            + " LEFT JOIN results R ON R.examinationId = E.id"
            + " WHERE E.patientId = ?";
    public static final String ADD_EXAMINATION = "INSERT INTO examination (description, examTime, patientId, doctor)"
            + " VALUES (?, ?, ?, ?)";
   
    public static List<Examination> getPatientExamination(int patient) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        List<Examination> exams = new ArrayList();
        String[] columns = {"E.description", "E.examTime", "D.username", "D.surname", "R.resultDate", "R.resultDsc", "E.id"};
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_PATIENT_EXAMINATION);
        stmt.setInt(1, patient);
        rs = stmt.executeQuery();
        
        for (int i = 0; rs.next(); i++) {
            exams.add(new Examination(rs.getInt(columns[6]), rs.getString(columns[0]), rs.getDate(columns[1]), rs.getString(columns[2]),
                    rs.getString(columns[3]), rs.getDate(columns[4]), rs.getString(columns[5])));
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return exams;

    }
    
    public static void addExamination(String description, Date examTime, int patientid, String doctorEmail)
            throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(ADD_EXAMINATION);
        stmt.setString(1, description);
        stmt.setDate(2, examTime);
        stmt.setInt(3, patientid);
        stmt.setString(4, doctorEmail);
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
        
    }

    
}
