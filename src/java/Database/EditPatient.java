/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Patient;
import Utilities.PatternParser;
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
    // TODO pridat pacienta se mi musi cistit omg
    public static final String ADD_PATIENT = "INSERT INTO patients (patientName, surname, birthNum, address, city)"
            + " VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_PATIENTS = "SELECT P.*, D.depName, U.username, U.surname FROM patients P"
            + " LEFT JOIN hospitalization H ON P.id = H.id"
            + " LEFT JOIN department D ON H.departmentNum = D.id AND H.released IS NULL"
            + " LEFT JOIN usertable U ON H.doctor = U.email";
    public static final String SELECT_LAST_PATIENT = "SELECT patientName, surname FROM patients ORDER BY id DESC LIMIT 1";
    public static final String SEARCH_PATIENTS = "SELECT * FROM patients"
            + " WHERE (patientName LIKE ? AND surname LIKE ?)"
            + " OR (patientName LIKE ? AND surname LIKE ?)";
    
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
            patients.get(i).setDrugs(EditPrescription.getUsedDrugs(patients.get(i).getId()));
            patients.get(i).setExams(EditExamination.getPatientExamination(patients.get(i).getId()));
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return patients;
        
    }
    
    public static Patient getLastPatient() throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        Patient patient;
        String[] columns = {"P.id", "P.patientName", "P.surname", "P.birthNum", "P.address", "P.city",
                 "D.depName", "U.username", "U.surname"};
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_LAST_PATIENT);
        rs = stmt.executeQuery();
        rs.next();
        
        patient = new Patient(rs.getString(columns[1]), rs.getString(columns[2]), rs.getString(columns[3]), 
                rs.getString(columns[4]), rs.getString(columns[5]));
        patient.setId(rs.getInt(columns[0]));
        patient.setDepartmentName(rs.getString(columns[6]));
        patient.setDoctorName(rs.getString(columns[7]));
        patient.setDoctorSurname(rs.getString(columns[8]));
        
        rs.close();
        stmt.close();
        connection.close();
        
        return patient;
        
    }
    
    public static List<Patient> searchPatients(String pattern) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        List<Patient> patients = new ArrayList();
        String[] columns = {"id", "patientName", "surname", "birthNum", "address", "city"};
        PatternParser patternParser = new PatternParser();
        patternParser.parsePattern(pattern);

        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SEARCH_PATIENTS);
        stmt.setString(1, '%' + patternParser.getName() + '%');
        stmt.setString(2, '%' + patternParser.getSurname() + '%');
        stmt.setString(3, '%' + patternParser.getSurname() + '%');
        stmt.setString(4, '%' + patternParser.getName() + '%');
        rs = stmt.executeQuery();
        
        for (int i = 0; rs.next(); i++) {
            patients.add(new Patient(rs.getString(columns[1]), rs.getString(columns[2]), rs.getString(columns[3]), 
                rs.getString(columns[4]), rs.getString(columns[5])));
            patients.get(i).setId(rs.getInt(columns[0]));
            patients.get(i).setDrugs(EditPrescription.getUsedDrugs(patients.get(i).getId()));
            patients.get(i).setExams(EditExamination.getPatientExamination(patients.get(i).getId()));
            patients.get(i).setHospitalization(EditHospitalization.getHospitalizations(patients.get(i).getId()));
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return patients;
        
    }
    
}
