/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Database.EditDepartment;
import Database.EditPatient;
import Models.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "DocController", urlPatterns = {"/docController", "/addPatient", "/actionAddPatient",
        "/viewPatients"})
public class DocController extends HttpServlet {
    
    public static final String ADD_PATIENT = "/addPatient";
    
    public static final String ACTION_ADD_PATIENT = "/actionAddPatient";
    public static final String VIEW_PATIENTS = "/viewPatients";
    
    private String address;
    private Patient patient;


   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String attrDep = "department";
        String attrPatient = "patient";
        
        // TODO pridat vyhledavani
        setAddress(request.getServletPath()); // TODO to by tu nemuselo vubec byt
        // TODO prepsat odkazy na adresy
        switch (getAddress()) {
            
            case Controller.USER_CONTROLLER:
                request.getRequestDispatcher(RootController.VIEW_PATH + "/user.jsp").forward(request, response);
                break;
                
            case ADD_PATIENT:
                request.setAttribute(attrPatient, getPatient());
                request.getRequestDispatcher(RootController.VIEW_ADD_PATH + "/addPatient.jsp").forward(request, response);
                break;
                
            case VIEW_PATIENTS:
                List<String> departments;
                List<Patient> patients;
                
                try {
                    departments = EditDepartment.getDepartments();
                    patients = EditPatient.getPatients();
                }
                
                catch (SQLException | NamingException ex) {
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    break;
                }
                
                request.setAttribute(attrDep, departments);
                request.setAttribute(attrPatient, patients);
                request.getRequestDispatcher(RootController.VIEW_SHOW_PATH + "/viewPatients.jsp").forward(request, response);
                break;
                
            default:
                response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_404);
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
        
        switch (request.getServletPath()) {
            
            case ACTION_ADD_PATIENT:
                
                String attribute = "patient";
                setPatient(new Patient(request.getParameter("inputName"), 
                        request.getParameter("inputSurname"), request.getParameter("inputBirthNum"),
                        request.getParameter("inputAddr"), request.getParameter("inputCity")));
                
                // check if all required values are correctly filled
                if (getPatient().getName().isEmpty()) {
                    request.setAttribute(attribute, getPatient());
                    Controller.redirect(request, response, ADD_PATIENT + "?name=True");
                    break;
                }
                
                else if (getPatient().getSurname().isEmpty()) {
                    request.setAttribute(attribute, getPatient());
                    Controller.redirect(request, response, ADD_PATIENT + "?surname=True");
                    break;
                }
                
                // add patient to db
                // TODO ADDED ITEM by mel by pristupny i pro docController
                try {
                    EditPatient.addPatient(getPatient());
                }
                
                catch (SQLException | NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
                
                finally {
                    getPatient().clear();
                }
                
                Controller.redirect(request, response, RootController.ADDED_ITEM + "?patient=True");
                break;
                
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
// TODO dopsat do controlleru get servletinfo metodu

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
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
