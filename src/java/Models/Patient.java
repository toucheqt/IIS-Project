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
public class Patient {
    
    private Integer id;
    private String name;
    private String surname;
    private String birthNum;
    private String address;
    private String city;
    private String departmentName;
    private String doctorName;
    private String doctorSurname;
    
    public Patient(String name, String surname, String birthNum, String address, String city) {
        this.name = name;
        this.surname = surname;
        this.birthNum = birthNum;
        this.address = address;
        this.city = city;
    }
    
    public void clear() {
        this.name = null;
        this.surname = null;
        this.birthNum = null;
        this.address = null;
        this.city = null;
        this.id = null;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * @return the doctorName
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * @param doctorName the doctorName to set
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     * @return the doctorSurname
     */
    public String getDoctorSurname() {
        return doctorSurname;
    }

    /**
     * @param doctorSurname the doctorSurname to set
     */
    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }
    
}
