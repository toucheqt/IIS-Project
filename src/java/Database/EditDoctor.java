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
    
    public static final String INSERT_USER = "INSERT INTO usertable (username, surname, birthNum,"
            + " address, city, email, tel, roleType, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
    public static final String SELECT_ROLE_TYPE = "SELECT roleType FROM usertable WHERE email = ?";
    public static final String SELECT_DOC_INFO = "SELECT username, surname, email FROM usertable"
            + " WHERE roleType = ? ORDER BY username";
    public static final String SELECT_DOC_DEP = "SELECT username, surname, email, depName, workingTime, isworking.tel"
            + " FROM usertable"
            + " JOIN isworking ON usertable.email = isworking.doctor"
            + " JOIN department ON isworking.departmentNum = department.id"
            + " ORDER BY username";
    public static final String SELECT_DOCTOR = "SELECT username, surname, birthNum, address, city, email, usertable.tel,"
            + " depName"
            + " FROM usertable"
            + " LEFT JOIN isworking ON usertable.email = isworking.doctor"
            + " LEFT JOIN department ON isworking.departmentNum = department.id"
            + " WHERE roleType = ?"
            + " ORDER BY username";
    public static final String DELETE_DOCTOR = "DELETE FROM usertable WHERE email = ?";
    public static final String UPDATE_DOCTOR = "UPDATE usertable SET username = ?, surname = ?, birthNum = ?,"
            + " address = ?, city = ?, email = ?, tel = ? WHERE email = ?";
    public static final String SELECT_PASSWD = "SELECT password FROM usertable WHERE email = ?";
    public static final String UPDATE_PASSWD = "UPDATE usertable SET password = ? WHERE email = ?";
    // TODO prepsat selecty tj. pouzivat v nich ON
            /*select s.name as Student, c.name as Course 
from student s
inner join bridge b on s.id = b.sid
inner join course c on b.cid  = c.id 
order by s.name*/
    public static void addDoctor(Doctor doctor) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
            
        connection = Connect.getConnection();
        
        stmt = connection.prepareStatement(INSERT_USER);
        
        stmt.setString(1, doctor.getUsername());
        stmt.setString(2, doctor.getSurname());
        stmt.setString(3, doctor.getBirthNum());
        stmt.setString(4, doctor.getAddress());
        stmt.setString(5, doctor.getCity());
        stmt.setString(6, doctor.getEmail());
        stmt.setString(7, doctor.getTel());
        stmt.setString(8, doctor.getRoleType());
        stmt.setString(9, doctor.getPassword());      
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
 
    }
    
    public static String getUserRole(String user) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        String role;
        String column = "roleType";
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_ROLE_TYPE);
        stmt.setString(1, user);
        rs = stmt.executeQuery();      
        rs.next();
        role = rs.getString(column);
        
        rs.close();
        stmt.close();
        connection.close();
        
        return role;
        
    }
    
    public static List<Doctor> getDoctorInfo() throws NamingException, SQLException {

        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        String[] columns = {"username", "surname", "email"};
        List<Doctor> doctors = new ArrayList();
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_DOC_INFO);
        stmt.setString(1, Controller.ROLE_USER);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            doctors.add(new Doctor(rs.getString(columns[0]), rs.getString(columns[1]), null, null, null, 
                    rs.getString(columns[2]), null, null));
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return doctors;
        
    }
    
    public static List<Doctor> getDoctors() throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        List<Doctor> doctors = new ArrayList();
        String[] columns = {"username", "surname", "birthNum", "address", "city", "email", "tel", "depName"};
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_DOCTOR);
        stmt.setString(1, Controller.ROLE_USER);
        rs = stmt.executeQuery();
        
        // TODO zkusit najit nejakou nahradu za ten hnusnej while
        for (int i = 0; rs.next(); i++) {     
            doctors.add(new Doctor(rs.getString(columns[0]), rs.getString(columns[1]),
                    rs.getString(columns[2]), rs.getString(columns[3]), rs.getString(columns[4]),
                    rs.getString(columns[5]), rs.getString(columns[6]), null)); 
            doctors.get(i).setDepartmentName(rs.getString(columns[7]));
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return doctors;
        
    }
    
    public static List<Doctor> getDoctorWork() throws NamingException, SQLException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        List<Doctor> doctors = new ArrayList();
        String[] columns = {"username", "surname", "email", "depName", "workingTime", "isworking.tel"};
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_DOC_DEP);
        rs = stmt.executeQuery();
        
        int i = 0;
        while (rs.next()) {
            doctors.add(new Doctor(rs.getString(columns[0]), rs.getString(columns[1]), null, null, null,
                    rs.getString(columns[2]), rs.getString(columns[5]), null));
            doctors.get(i).setDepartmentName(rs.getString(columns[3]));
            doctors.get(i).setWorkingTime(rs.getString(columns[4]));
            i++;
        }
        
        connection.close();
        stmt.close();
        rs.close();
        
        return doctors;
        
    }
    
    public static void deleteDoctor(String email) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(DELETE_DOCTOR);
        stmt.setString(1, email);
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
        
    }
    
    public static void updateDoctor(Doctor doctor, String defaultEmail) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(UPDATE_DOCTOR);
        stmt.setString(1, doctor.getUsername());
        stmt.setString(2, doctor.getSurname());
        stmt.setString(3, doctor.getBirthNum());
        stmt.setString(4, doctor.getAddress());
        stmt.setString(5, doctor.getCity());
        stmt.setString(6, doctor.getEmail());
        stmt.setString(7, doctor.getTel());
        stmt.setString(8, defaultEmail);
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
        
    }
    
    public static String getPasswd(String user) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        String column = "password";
        String password;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_PASSWD);
        stmt.setString(1, user);
        rs = stmt.executeQuery();
        
        rs.next();
        password = rs.getString(column);
        
        connection.close();
        stmt.close();
        rs.close();
        
        return password;
        
    }
    
    public static void updatePasswd(String user, String passwd) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(UPDATE_PASSWD);
        stmt.setString(1, passwd);
        stmt.setString(2, user);
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
        
    }
    
}
