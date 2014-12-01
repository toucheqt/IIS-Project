/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Database.EditDoctor;
import Database.util.MD5Generator;
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
@WebServlet(name = "Autentificator", urlPatterns = {"/logout", "/updatePasswd", "/updateAbout"})
public class Autentificator extends HttpServlet {
    
    public static final String LOGOUT = "/logout";
    public static final String UPDATE_PASSWD = "/updatePasswd";
    public static final String UPDATE_ABOUT = "/updateAbout";

    private String address;


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
        
        setAddress(request.getServletPath());
        
        switch (getAddress()) {
            // TODO poresit aby se dal zadat vetsi mail nez 25 znaku :D
            case LOGOUT:
                
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect(Controller.DEFAULT_PATH);
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
        
        String user = request.getRemoteUser();
        setAddress(request.getServletPath());
        request.setCharacterEncoding("UTF-8");

        switch (getAddress()) {
            
            case UPDATE_PASSWD:
                
                String defaultPasswd;
                String newPasswd = request.getParameter("newPasswd1");
                String newPasswdCheck = request.getParameter("newPasswd2");
                
                // check if new passwords are identical
                if (!newPasswd.equals(newPasswdCheck)) {
                    Controller.redirect(request, response, "nesedi nova hesla");
                    break; // TODO nejak vymyslet error pri spatne zadanych heslech
                }
                
                // check if old password is correct
                try {
                    defaultPasswd =  EditDoctor.getPasswd(user);
                }
                
                catch (SQLException | NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
                
                if (!defaultPasswd.equals(MD5Generator.generatePassword(request.getParameter("oldPasswd")))) {
                    Controller.redirect(request, response, "nesedi stara hesla");
                    break;
                }
            
                // update passwd
                try {
                    EditDoctor.updatePasswd(user, MD5Generator.generatePassword(newPasswd));
                }
            
                catch (SQLException | NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                    // TODO error pri prihlasovani vycentrovat na stred
                }
            
                Controller.redirect(request, response, Controller.ROOT_CONTROLLER); // TODO presmerovat na success kid page
                break;
                
            case UPDATE_ABOUT:
                
                try {
                    // TODO: predvyplnit udaje ve zmene osobnich udaju
                    EditDoctor.updateDoctor(new Doctor(request.getParameter("inputName"),
                            request.getParameter("inputSurname"), request.getParameter("inputBirthNum"),
                            request.getParameter("inputAddr"), request.getParameter("inputCity"),
                            request.getParameter("inputEmail"), request.getParameter("inputTel"), null), user);
                }
            
                catch (NumberFormatException ex) {
                    Controller.redirect(request, response, "chybny telefon"); // TODO error
                    break;
                }
            
                catch (SQLException | NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
            
                Controller.redirect(request, response, "success update");
                break; // TODO redirect
                // TODO naplnit db daty
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

}
