/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Touche
 */
public class Nurse {
    
    private Integer id;
    private String username;
    private String surname;
    private String birthNum;
    private String address;
    private String city;
    private Integer departmentNum;
    private String departmentName;
    
    public Nurse(String username, String surname, String birthNum, String address, String city, Integer deparmentNum) {
        
        this.username = username;
        this.surname = surname;
        this.birthNum = birthNum;
        this.address = address;
        this.city = city;
        this.departmentNum = deparmentNum;
        
    }
    
    public void clearNurse() {
        
        this.id = null;
        this.username = null;
        this.surname = null;
        this.birthNum = null;
        this.address = null;
        this.city = null;
        this.departmentNum = null;
        
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the birthNum
     */
    public String getBirthNum() {
        return birthNum;
    }

    /**
     * @param birthNum the birthNum to set
     */
    public void setBirthNum(String birthNum) {
        this.birthNum = birthNum;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the departmentNum
     */
    public Integer getDepartmentNum() {
        return departmentNum;
    }

    /**
     * @param departmentNum the departmentNum to set
     */
    public void setDepartmentNum(Integer departmentNum) {
        this.departmentNum = departmentNum;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
        
}
