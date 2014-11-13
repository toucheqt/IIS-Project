/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import servlets.Controller;

/**
 *
 * @author Touche
 */
public class Doctor {
    
    private final String roleType;

    private String username;
    private String surname;
    private String birthNum;
    private String address;
    private String city;
    private String email;
    private Integer tel;
    private String password;
    
    public Doctor(String username, String surname, String birthNum, String address, String city, String email,
            Integer tel, String password) {
        this.username = username;
        this.surname = surname;
        this.birthNum = birthNum;
        this.address = address;
        this.city = city;
        this.email = email;
        this.tel = tel;
        this.roleType = Controller.ROLE_USER;
        this.password = password;
    }
    
    public void clearDoctor() {
        
        setUsername(null);
        setSurname(null);
        setBirthNum(null);
        setAddress(null);
        setCity(null);
        setEmail(null);
        setTel(null);
        
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the tel
     */
    public Integer getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(Integer tel) {
        this.tel = tel;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the roleType
     */
    public String getRoleType() {
        return roleType;
    }
    
}
