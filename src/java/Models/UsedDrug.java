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
public class UsedDrug {
    
    private String name;
    private String contraindication;
    private String description;
    private String effectivity;
    private Date startUsage;
    private Date endUsage;
    private String dosage;
    
    public UsedDrug(String name, String contraindication, String description, String effectivity, Date startUsage, 
            Date endUsage, String dosage) {
        
        this.name = name;
        this.contraindication = contraindication;
        this.description = description;
        this.effectivity = effectivity;
        this.startUsage = startUsage;
        this.endUsage = endUsage;
        this.dosage = dosage;
        
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
     * @return the contraindication
     */
    public String getContraindication() {
        return contraindication;
    }

    /**
     * @param contraindication the contraindication to set
     */
    public void setContraindication(String contraindication) {
        this.contraindication = contraindication;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the effectivity
     */
    public String getEffectivity() {
        return effectivity;
    }

    /**
     * @param effectivity the effectivity to set
     */
    public void setEffectivity(String effectivity) {
        this.effectivity = effectivity;
    }

    /**
     * @return the startUsage
     */
    public Date getStartUsage() {
        return startUsage;
    }

    /**
     * @param startUsage the startUsage to set
     */
    public void setStartUsage(Date startUsage) {
        this.startUsage = startUsage;
    }

    /**
     * @return the endUsage
     */
    public Date getEndUsage() {
        return endUsage;
    }

    /**
     * @param endUsage the endUsage to set
     */
    public void setEndUsage(Date endUsage) {
        this.endUsage = endUsage;
    }

    /**
     * @return the dosage
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * @param dosage the dosage to set
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
    
    
    
    
}
