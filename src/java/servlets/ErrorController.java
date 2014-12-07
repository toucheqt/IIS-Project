/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Database.EditDoctor;
import Models.Doctor;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Touche
 */
@WebServlet(name = "ErrorController", urlPatterns = {"/404", "/500"})
public class ErrorController extends HttpServlet {
    
    private Doctor activeUser; 
    private String userRole;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // load active users info
        try {
            activeUser = EditDoctor.getDoctor(request.getRemoteUser());
            userRole = EditDoctor.getUserRole(request.getRemoteUser());
        }
        
        catch (SQLException | NamingException ex) {
            response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
            return;
        }
                
        switch (request.getServletPath()) {
                            
            case Controller.ERROR_500:
                if (userRole.equals(Controller.ROLE_USER)) {
                    request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                    request.getRequestDispatcher(RootController.ERROR_PATH + "/500x.jsp").forward(request, response);
                    break;
                }
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                request.getRequestDispatcher(RootController.ERROR_PATH + "/500.jsp").forward(request, response);
                break;
                
            default: // as default app should redirect to /404
                if (userRole.equals(Controller.ROLE_USER)) {
                    request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser); // TODO toto udelaj jako jeden soubor jeeez
                    request.getRequestDispatcher(RootController.ERROR_PATH + "/404x.jsp").forward(request, response);
                    break;
                }
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                request.getRequestDispatcher(RootController.ERROR_PATH + "/404.jsp").forward(request, response);
                break;
                
        }

    }

}
