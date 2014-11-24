/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Database.util.Connect;
import Models.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import servlets.Controller;

/**
 *
 * @author Touche
 */
public class EditDoctor {
    
    public static final String INSERT_USER = "INSERT INTO usertable (username, surname, birthNum, address,"
            + " city, email, tel, roleType, password) VALUES (?, ?, ?, ?, ?, ?, NULL, ?, ?)";
    public static final String INSERT_USER_WITH_TEL = "INSERT INTO usertable (username, surname, birthNum,"
            + " address, city, email, tel, roleType, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
    public static final String SELECT_ROLE_TYPE = "SELECT roleType FROM usertable WHERE email = ?";
    public static final String SELECT_DOC_INFO = "SELECT username, surname, email FROM usertable"
            + " WHERE roleType = ? ORDER BY username";
    public static final String SELECT_DOC_DEP = "SELECT username, surname, email, depName, workingTime, isworking.tel"
            + " FROM usertable"
            + " JOIN isworking ON usertable.email = isworking.doctor"
            + " JOIN department ON isworking.departmentNum = department.id"
            + " ORDER BY username";
    // TODO prepsat selecty
            /*select s.name as Student, c.name as Course 
from student s
inner join bridge b on s.id = b.sid
inner join course c on b.cid  = c.id 
order by s.name*/
    public static void addDoctor(Doctor doctor) throws SQLException, NamingException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        String query;
            
        if (doctor.getTel()!= null) query = INSERT_USER_WITH_TEL;
        else query = INSERT_USER;
            
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(query);
        stmt.setString(1, doctor.getUsername());
        stmt.setString(2, doctor.getSurname());
        stmt.setString(3, doctor.getBirthNum());
        stmt.setString(4, doctor.getAddress());
        stmt.setString(5, doctor.getCity());
        stmt.setString(6, doctor.getEmail());
            
        if (doctor.getTel() != null) {
            stmt.setInt(7, doctor.getTel());
            stmt.setString(8, doctor.getRoleType());
            stmt.setString(9, doctor.getPassword());
        }
            
        else {
            stmt.setString(7, doctor.getRoleType());
            stmt.setString(8, doctor.getPassword());
        }
        
        stmt.executeUpdate();
        
        if (stmt != null) stmt.close();
        if (connection != null) connection.close();
 
    }
    
    public static String getUserRole(String user) throws SQLException, NamingException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String role = null;
        String column = "roleType";
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_ROLE_TYPE);
        stmt.setString(1, user);
        rs = stmt.executeQuery();      
        rs.next();
        role = rs.getString(column);
        
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (connection != null) connection.close();
        
        return role;
        
    }
    
    public static List<Doctor> getDoctorInfo() throws NamingException, SQLException {

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String columns[] = {"username", "surname", "email"};
        List<Doctor> doctors = new ArrayList();
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_DOC_INFO);
        stmt.setString(1, Controller.ROLE_USER);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            doctors.add(new Doctor(rs.getString(columns[0]), rs.getString(columns[1]), null, null, null, 
                    rs.getString(columns[2]), null, null));
        }
        
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (connection != null) connection.close();
        
        return doctors;
        
    }
    
    public static List<Doctor> getDoctorWork() throws NamingException, SQLException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Doctor> doctors = new ArrayList();
        String[] columns = {"username", "surname", "email", "depName", "workingTime", "isworking.tel"};
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_DOC_DEP);
        rs = stmt.executeQuery();
        
        int i = 0;
        while (rs.next()) {
            doctors.add(new Doctor(rs.getString(columns[0]), rs.getString(columns[1]), null, null, null,
                    rs.getString(columns[2]), rs.getInt(columns[5]), null));
            doctors.get(i).setDepartmentName(rs.getString(columns[3]));
            doctors.get(i).setWorkingTime(rs.getString(columns[4]));
            i++;
        }
        
        if (connection != null) connection.close();
        if (stmt != null) stmt.close();
        if (rs != null) rs.close();
        
        return doctors;
        
    }
    
}
