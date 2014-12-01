/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Database.util.Connect;
import Models.Patient;
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
public class EditPatient {
    
    public static final String ADD_PATIENT = "INSERT INTO patients (patientName, surname, birthNum, address, city)"
            + " VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_PATIENTS = "SELECT P.*, D.depName, U.username, U.surname FROM patients P"
            + " LEFT JOIN hospitalization H ON P.id = H.id"
            + " LEFT JOIN department D ON H.departmentNum = D.id"
            + " LEFT JOIN usertable U ON H.doctor = U.email";
    
    public static void addPatient(Patient patient) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(ADD_PATIENT);
        stmt.setString(1, patient.getName());
        stmt.setString(2, patient.getSurname());
        stmt.setString(3, patient.getBirthNum());
        stmt.setString(4, patient.getAddress());
        stmt.setString(5, patient.getCity());
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
        
    }
    
    public static List<Patient> getPatients() throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        List<Patient> patients = new ArrayList();
        String[] columns = {"P.id", "P.patientName", "P.surname", "P.birthNum", "P.address", "P.city",
                 "D.depName", "U.username", "U.surname"};
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_PATIENTS);
        rs = stmt.executeQuery();
        
        for (int i = 0; rs.next(); i++) {
            patients.add(new Patient(rs.getString(columns[1]), rs.getString(columns[2]),
                    rs.getString(columns[3]), rs.getString(columns[4]), rs.getString(columns[5])));
            patients.get(i).setId(rs.getInt(columns[0]));
            patients.get(i).setDepartmentName(rs.getString(columns[6]));
            patients.get(i).setDoctorName(rs.getString(columns[7]));
            patients.get(i).setDoctorSurname(rs.getString(columns[8]));
        }
        
        return patients;
        
    }
    
}
