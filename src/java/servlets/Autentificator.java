/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Database.EditDoctor;
import Utilities.MD5Generator;
import Models.Doctor;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Touche
 */
@WebServlet(name = "Autentificator", urlPatterns = {"/logout", "/updatePasswd", "/updateAbout", "/errOldPasswd",
        "/errNewPasswd", "/sucPasswdUpdate", "/errAboutUpdate", "/sucAboutUpdate"})
public class Autentificator extends HttpServlet {
    
    public static final String LOGOUT = "/logout";
    public static final String UPDATE_PASSWD = "/updatePasswd";
    public static final String UPDATE_ABOUT = "/updateAbout";
    
    public static final String ERROR_OLD_PASSWD = "/errOldPasswd";
    public static final String ERROR_NEW_PASSWD = "/errNewPasswd";
    public static final String ERROR_EMAIL = "/errAboutUpdate";
    
    public static final String SUCCESS_PASSWD_UPDATE = "/sucPasswdUpdate";
    public static final String SUCCESS_ABOUT_UPDATE = "/sucAboutUpdate";
    
    private String userRole;
    

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        switch (request.getServletPath()) {
            case LOGOUT:
                
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect(Controller.DEFAULT_PATH);
                break;
                
            case ERROR_OLD_PASSWD:
                request.getRequestDispatcher(RootController.ERROR_PATH + "/passwdOldNotMatch.jsp").forward(request, response);
                break;
                
            case ERROR_NEW_PASSWD:
                request.getRequestDispatcher(RootController.ERROR_PATH + "/passwdNewNotMatch.jsp").forward(request, response);
                break;
                
            case ERROR_EMAIL:
                request.getRequestDispatcher(RootController.ERROR_PATH + "/updateAbout.jsp").forward(request, response);
                break;
                
            case SUCCESS_ABOUT_UPDATE:
                request.getRequestDispatcher(RootController.SUCCESS_PATH + "/updateAbout.jsp").forward(request, response);
                break; // TODO tyhle errorovy a successovy stranky spojit do jedny
                
            case SUCCESS_PASSWD_UPDATE:
                request.getRequestDispatcher(RootController.SUCCESS_PATH + "/passwdUpdate.jsp").forward(request, response);
                break;
            
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        try {
            userRole = EditDoctor.getUserRole(request.getRemoteUser());
        }
                    
        catch (SQLException | NamingException ex) {
            Controller.redirect(request, response, Controller.ERROR_500);
            return;
        }

        switch (request.getServletPath()) {
            
            case UPDATE_PASSWD:
                
                String defaultPasswd;
                String newPasswd = request.getParameter("newPasswd1");
                String newPasswdCheck = request.getParameter("newPasswd2");
                
                // check if new passwords are identical
                if (!newPasswd.equals(newPasswdCheck)) {

                    if (userRole.equals(Controller.ROLE_ROOT)) {
                        Controller.redirect(request, response, ERROR_NEW_PASSWD + "?root=True");
                    }
                    
                    else Controller.redirect(request, response, ERROR_NEW_PASSWD);
                    break;
                }
                
                // check if old password is correct
                try {
                    defaultPasswd =  EditDoctor.getPasswd(request.getRemoteUser());
                }
                
                catch (SQLException | NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
                
                if (!defaultPasswd.equals(MD5Generator.generatePassword(request.getParameter("oldPasswd")))) {
                    if (userRole.equals(Controller.ROLE_ROOT)) {
                        Controller.redirect(request, response, ERROR_OLD_PASSWD + "?root=True");
                    }
                    
                    else Controller.redirect(request, response, ERROR_OLD_PASSWD);
                    break;
                }
            
                // update passwd
                try {
                    EditDoctor.updatePasswd(request.getRemoteUser(), MD5Generator.generatePassword(newPasswd));
                }
            
                catch (SQLException | NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
            
                
                if (userRole.equals(Controller.ROLE_ROOT)) {
                    Controller.redirect(request, response, SUCCESS_PASSWD_UPDATE + "?root=True");
                }
                    
                else Controller.redirect(request, response, SUCCESS_PASSWD_UPDATE);
                break;
                
            case UPDATE_ABOUT:
                
                if (request.getParameter("inputEmail").isEmpty()) { // TODO osetrit to poradne
                    if (userRole.equals(Controller.ROLE_ROOT)) {
                        Controller.redirect(request, response, ERROR_EMAIL + "?root=True");
                    }
                    
                    else Controller.redirect(request, response, ERROR_EMAIL);
                    break;
                }
                
                try {
                    EditDoctor.updateDoctor(new Doctor(request.getParameter("inputName"),
                            request.getParameter("inputSurname"), request.getParameter("inputBirthNum"),
                            request.getParameter("inputAddr"), request.getParameter("inputCity"),
                            request.getParameter("inputEmail"), request.getParameter("inputTel"), null), request.getRemoteUser());
                }     
            
                catch (SQLException | NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
            
                if (userRole.equals(Controller.ROLE_ROOT)) {
                        Controller.redirect(request, response, SUCCESS_ABOUT_UPDATE + "?root=True");
                    }
                    
                else Controller.redirect(request, response, SUCCESS_ABOUT_UPDATE);
                break;
                // TODO kontrolovat na korektni data i osobni udaje
                // TODO rodne cislo predelat na regulak obsahujici 0-9/
                
            default:
                Controller.redirect(request, response, Controller.ERROR_404);
                break;
                
        }
         
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
