/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Touche
 */
public class Hospitalization {
    
    private Date hospitalized;
    private Date released;
    private String departmentName;
    private String doctorName;
    private String doctorSurname;
    
    public Hospitalization(Date hospitalized, Date released, String depName, String docName, String docSurname) {
        this.hospitalized = hospitalized;
        this.released = released;
        this.departmentName = depName;
        this.doctorName = docName;
        this.doctorSurname = docSurname;
    }

    /**
     * @return the hospitalized
     */
    public Date getHospitalized() {
        return hospitalized;
    }

    /**
     * @param hospitalized the hospitalized to set
     */
    public void setHospitalized(Date hospitalized) {
        this.hospitalized = hospitalized;
    }

    /**
     * @return the released
     */
    public Date getReleased() {
        return released;
    }

    /**
     * @param released the released to set
     */
    public void setReleased(Date released) {
        this.released = released;
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
