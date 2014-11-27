/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Database.util.Connect;
import Models.Nurse;
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
public class EditNurse {
    
    public static final String INSERT_NURSE = "INSERT INTO nurse (username, surname, birthNum, address, city,"
            + "departmentNum) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SELECT_NURSE = "SELECT * FROM nurse";
    public static final String SELECT_NURSE_INFO = "SELECT username, surname, depName, nurse.id FROM nurse JOIN department"
            + " WHERE department.id = nurse.departmentNum";
    public static final String UPDATE_NURSE_WORK = "UPDATE nurse SET departmentNum = ? WHERE id = ?";
    public static final String UPDATE_NURSE = "UPDATE nurse SET username = ?, surname = ?, birthNum = ?,"
            + " address = ?, city = ? WHERE id = ?";
    public static final String DELETE_NURSE = "DELETE FROM nurse WHERE id = ?";
    
    
    public static void addNurse(Nurse nurse) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        
        connection = Connect.getConnection();
        stmt = connection.prepareCall(INSERT_NURSE);
        stmt.setString(1, nurse.getUsername());
        stmt.setString(2, nurse.getSurname());
        stmt.setString(3, nurse.getBirthNum());
        stmt.setString(4, nurse.getAddress());
        stmt.setString(5, nurse.getCity());
        stmt.setInt(6, nurse.getDepartmentNum());
            
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
        
    }
    
    public static List<Nurse> getNurse() throws NamingException, SQLException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        String[] columns = {"id", "username", "surname", "birthNum", "address", "city", "departmentNum"};
        List <Nurse> nurse = new ArrayList();
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_NURSE);
        rs = stmt.executeQuery();
        
        int i = 0;
        while (rs.next()) {
            nurse.add(new Nurse(rs.getInt(columns[0]), rs.getString(columns[1]), rs.getString(columns[2]),
                    rs.getString(columns[3]), rs.getString(columns[4]), rs.getString(columns[5]),
                    rs.getInt(columns[6])));
            nurse.get(i).setDepartmentName(EditDepartment.getNameFromId(nurse.get(i).getDepartmentNum()));
            i++;
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return nurse;
        
    }
    
    public static List<Nurse> getNurseInfo() throws NamingException, SQLException {
        
        Connection connection;
        PreparedStatement stmt;
        ResultSet rs;
        String[] columns = {"username", "surname", "depName", "nurse.id"};
        List<Nurse> nurse = new ArrayList();

        connection = Connect.getConnection();
        stmt = connection.prepareStatement(SELECT_NURSE_INFO);
        rs = stmt.executeQuery();
        
        int i = 0;
        while (rs.next()) {
            nurse.add(new Nurse(rs.getString(columns[0]), rs.getString(columns[1]), null, null, null, null));
            nurse.get(i).setDepartmentName(rs.getString(columns[2]));
            nurse.get(i).setId(rs.getInt(columns[3]));
            i++;
        }
        
        rs.close();
        stmt.close();
        connection.close();
        
        return nurse;
    }
    
    public static void updateWork(int nurseId, int depId) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(UPDATE_NURSE_WORK);
        stmt.setInt(1, depId);
        stmt.setInt(2, nurseId);
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
        
    }
    
    public static void updateNurse(Nurse nurse) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(UPDATE_NURSE);
        stmt.setString(1, nurse.getUsername());
        stmt.setString(2, nurse.getSurname());
        stmt.setString(3, nurse.getBirthNum());
        stmt.setString(4, nurse.getAddress());
        stmt.setString(5, nurse.getCity());
        stmt.setInt(6, nurse.getId());
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
        
    }
    
    public static void deleteNurse(int id) throws SQLException, NamingException {
        
        Connection connection;
        PreparedStatement stmt;
        
        connection = Connect.getConnection();
        stmt = connection.prepareStatement(DELETE_NURSE);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
        
    }
        
}
