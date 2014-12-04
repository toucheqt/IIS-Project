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
public class Examination {
    
    private int examId;
    private String description;
    private Date examTime;
    private String doctorName;
    private String doctorSurname;
    private Date resultDate;
    private String result;
    
    public Examination(int examId, String description, Date examTime, String doctorName, String doctorSurname, Date resultDate, 
            String result) {
        this.examId = examId;
        this.description = description;
        this.examTime = examTime;
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.resultDate = resultDate;
        this.result = result;
    }

    /**
     * @return the decription
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param decription the decription to set
     */
    public void setDescription(String decription) {
        this.description = decription;
    }

    /**
     * @return the examTime
     */
    public Date getExamTime() {
        return examTime;
    }

    /**
     * @param examTime the examTime to set
     */
    public void setExamTime(Date examTime) {
        this.examTime = examTime;
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

    /**
     * @return the resultDate
     */
    public Date getResultDate() {
        return resultDate;
    }

    /**
     * @param resultDate the resultDate to set
     */
    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the examId
     */
    public int getExamId() {
        return examId;
    }

    /**
     * @param examId the examId to set
     */
    public void setExamId(int examId) {
        this.examId = examId;
    }
    
}
