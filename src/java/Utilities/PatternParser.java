/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Touche
 */
public class PatternParser {
    
    private String name;
    private String surname;
    
    public PatternParser() {
        this.name = "";
        this.surname = "";
    }
    
    public void parsePattern(String pattern) {
        
        String pomStr = ""; // TODO pouzit string builder / string buffer
        boolean loadingName = true;
        
        for (int i = 0; i < pattern.length(); i++) {
            if (loadingName) {
                if (pattern.charAt(i) == ' ') {
                    loadingName = false;
                    setName(pomStr);
                    pomStr = "";
                    continue;
                }
                
                pomStr += pattern.charAt(i);
            }
            
            else pomStr += pattern.charAt(i);
        }
        
        setSurname(pomStr);
          
    }
    
    public int parseQuery(String query) throws NumberFormatException {
        
        String id = ""; // todo string builder zase
        
        for (int i = 2; i < query.length(); i++) {
            id += query.charAt(i);
        }
        
        return Integer.parseInt(id);
        
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
    
    
}
