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
public class Doctor {
    
    private int id;
    private String name;
    private String surname;
    private String birthNum;
    private String address;
    private String city;
    private Integer telNum;
    private String mail;
    
    public Doctor(String name, String surname, String birthNum, String address, String city, Integer telNum, String mail) {
        this.name = name;
        this.surname = surname;
        this.birthNum = birthNum;
        this.address = address;
        this.city = city;
        this.telNum = telNum;
        this.mail = mail;
    }
    
    public Doctor(int id, String name, String surname, String birthNum, String address, String city, Integer telNum, String mail) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthNum = birthNum;
        this.address = address;
        this.city = city;
        this.telNum = telNum;
        this.mail = mail;
    }
    
    public void clearDoctor() {
        this.id = 0;
        this.name = null;
        this.surname = null;
        this.birthNum = null;
        this.address = null;
        this.city = null;
        this.telNum = null;
        this.setMail(null);
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
     * @return the telNum
     */
    public Integer getTelNum() {
        return telNum;
    }

    /**
     * @param telNum the telNum to set
     */
    public void setTelNum(Integer telNum) {
        this.telNum = telNum;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    
}
