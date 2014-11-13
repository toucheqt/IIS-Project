/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Touche
 */
public class Connect {
    
    public static Connection getConnection() throws NamingException, SQLException {
        
        Context initCtx = new InitialContext();
        Context ctx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) ctx.lookup("jdbc/mysql");
        return ds.getConnection();
      
    }
    
}
