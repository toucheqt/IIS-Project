/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Database.EditDepartment;
import Database.EditDoctor;
import Database.EditDrugs;
import Database.EditExamination;
import Database.EditHospitalization;
import Database.EditPatient;
import Database.EditPrescription;
import Database.EditResults;
import Models.Doctor;
import Models.Examination;
import Models.Patient;
import Models.UsedDrug;
import Utilities.PatternParser;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Touche
 */
@WebServlet(name = "DocController", urlPatterns = {"/docController", "/addPatient", "/actionAddPatient",
        "/viewPatients", "/userSearch", "/searchFound", "/searchNotFound", "/updatePatient", "/deletePatient",
        "/addDrugs", "/addExamination", "/addResult", "/addHospitalization", "/patient", "/errorPatient"})
@ServletSecurity(@HttpConstraint(rolesAllowed = {"user"}))
public class DocController extends HttpServlet {
    
    // TODO oddeleni se nemeni, tak by ho stacilo nacist jen jednou
    
    public static final String ADD_PATIENT = "/addPatient";
    
    public static final String ACTION_ADD_PATIENT = "/actionAddPatient";
    public static final String VIEW_PATIENTS = "/viewPatients";
    public static final String DETAIL_PATIENT = "/patient";
    public static final String UPDATE_PATIENT = "/updatePatient";
    public static final String DELETE_PATIENT = "/deletePatient";
    public static final String ADD_DRUG_TO_PATIENT = "/addDrugs";
    public static final String ADD_EXAM_TO_PATIENT = "/addExamination";
    public static final String ADD_RESULT_TO_EXAM = "/addResult";
    public static final String ADD_HOSPITALIZATION = "/addHospitalization";
    public static final String ERROR_PATIENT = "/errorPatient";
    
    public static final String USER_SEARCH = "/userSearch";
    public static final String SEARCH_FOUND = "/searchFound";
    public static final String SEARCH_NOT_FOUND = "/searchNotFound";
    
    private List<Patient> patients;
    private List<String> drugs;
    private Doctor activeUser;
    
    public DocController() {
        patients = new ArrayList();
        patients.add(new Patient());
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String attrDep = "department";
        String attrPatient = "patient";
        String attrDrug = "drugs";
        
        // TODO prepsat odkazy na adresy
        switch (request.getServletPath()) {
            
            case Controller.USER_CONTROLLER:
                
                // load active users info
                try {
                    activeUser = EditDoctor.getDoctor(request.getRemoteUser());
                }

                catch (SQLException | NamingException ex) {
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    return;
                }
                
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                request.getRequestDispatcher(RootController.VIEW_PATH + "/user.jsp").forward(request, response);
                break;
                
            case ADD_PATIENT:
                request.setAttribute(attrPatient, getPatient(0));
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                request.getRequestDispatcher(RootController.VIEW_ADD_PATH + "/addPatient.jsp").forward(request, response);
                break;
                
            case VIEW_PATIENTS: {
                List<String> departments; // TODO toto hodit VSUDE do konstruktoru
                
                try {
                    departments = EditDepartment.getDepartments();
                    setPatients(EditPatient.getPatients());
                }
                
                catch (SQLException | NamingException ex) {
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    break;
                }
                
                request.setAttribute(attrDep, departments);
                request.setAttribute(attrPatient, getPatients());
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                request.getRequestDispatcher(RootController.VIEW_SHOW_PATH + "/viewPatients.jsp").forward(request, response);
                break;  
            }
                
            case SEARCH_FOUND: {
                
                List<String> departments;
                                
                // found only one recored
                if (getPatients().size() == 1) {
                    
                    try {
                        setDrugs(EditDrugs.getDrugNames());
                        departments = EditDepartment.getDepartments();
                    }
                                        
                    catch (SQLException | NamingException ex) {
                        response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                        break;
                    }
                    
                    request.setAttribute(attrDep, departments);
                    request.setAttribute(attrDrug, getDrugs());
                    request.setAttribute(attrPatient, getPatient(0));
                    request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                    request.getRequestDispatcher(RootController.VIEW_SHOW_PATH + "/viewPatientInfo.jsp").forward(request, response);
                    break;
                }
            
                request.setAttribute(attrPatient, getPatients());
                request.getRequestDispatcher(RootController.VIEW_SHOW_PATH + "/viewPatientSearch.jsp").forward(request, response);
                break;
            }
                
            case SEARCH_NOT_FOUND:
                
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                request.getRequestDispatcher(RootController.ERROR_PATH + "/patientNotFound.jsp").forward(request, response);
                break;   
                
            case DETAIL_PATIENT: {
                
                /*PatternParser patternParser = new PatternParser();
                int patientId = null;
                try { // TODO checknout v db, jestli to id opravdu existuje
                    patientId = patternParser.parseQuery(request.getQueryString());
                }*/
                int patientId = Integer.parseInt(request.getParameter("patientId"));
                
                /*catch (NumberFormatException ex) {
                    response.sendRedirect(String.valueOf(patientId));
                    break;
                }*/
                        
                List<String> departments;
                
                try {
                    setDrugs(EditDrugs.getDrugNames());
                    departments = EditDepartment.getDepartments();
                    setPatient(EditPatient.getPatient(patientId));
                }
                                        
                catch (SQLException | NamingException ex) {
                    ex.printStackTrace();
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    break;
                }
                    
                request.setAttribute(attrDep, departments);
                request.setAttribute(attrDrug, getDrugs());
                request.setAttribute(attrPatient, getPatient(0));
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                request.getRequestDispatcher(RootController.VIEW_SHOW_PATH + "/viewPatientInfo.jsp").forward(request, response);
                break;
                
            }
            
            case ERROR_PATIENT: 
                request.getRequestDispatcher(RootController.ERROR_PATH + "/errorPatient.jsp").forward(request, response);
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
        String attribute = "patient";
        
        switch (request.getServletPath()) {
            
            case ACTION_ADD_PATIENT:
                
                setPatient(new Patient(request.getParameter("inputName"), 
                        request.getParameter("inputSurname"), request.getParameter("inputBirthNum"),
                        request.getParameter("inputAddr"), request.getParameter("inputCity")));
                
                // check if all required values are correctly filled
                if (getPatient(0).getName().isEmpty()) {
                    request.setAttribute(attribute, getPatient(0));
                    Controller.redirect(request, response, ADD_PATIENT + "?name=True");
                    break;
                }
                
                else if (getPatient(0).getSurname().isEmpty()) {
                    request.setAttribute(attribute, getPatient(0));
                    Controller.redirect(request, response, ADD_PATIENT + "?surname=True");
                    break;
                }
                
                // add patient to db
                try {
                    EditPatient.addPatient(getPatient(0));
                    getPatient(0).setId(EditPatient.getLastPatientId());
                }
                
                catch (SQLException | NamingException ex) {
                    ex.printStackTrace();
                    Controller.redirect(request, response, Controller.ERROR_500);
                    //getPatient(0).clear(); // TODO poresit clearovani pacientu
                    break;
                }
                                
                Controller.redirect(request, response, DETAIL_PATIENT + "?patientId=" + getPatient(0).getId());
                //getPatient(0).clear();
                break;    
                
            case USER_SEARCH:
                String pattern = request.getParameter("inputSearch");
                
                try {
                    setPatients(EditPatient.searchPatients(pattern));
                }
                
                catch (SQLException | NamingException ex) {
                    ex.printStackTrace();
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
                
                // if no match were found, redirect to not found page
                if (getPatients().isEmpty()) {
                    Controller.redirect(request, response, SEARCH_NOT_FOUND);
                    break;
                }
                
                Controller.redirect(request, response, SEARCH_FOUND);
                break;
                
            case UPDATE_PATIENT:
                
                if (request.getParameter("inputName").isEmpty() || request.getParameter("inputSurname").isEmpty()) {
                    Controller.redirect(request, response, ERROR_PATIENT);
                    break;
                }
                
                try { // TODO AAAAAAAAAAAAAAAAAAAAAA osetrit aby musela byt zadano jmeno a prijmenu u aktualizace
                    EditPatient.updatePatient(Integer.parseInt(request.getParameter("defaultId")), request.getParameter("inputName"), 
                            request.getParameter("inputSurname"), request.getParameter("inputBirthNum"), 
                            request.getParameter("inputAddr"), request.getParameter("inputCity"));
                }
                
                catch (SQLException | NamingException | NumberFormatException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                } 

                Controller.redirect(request, response, DETAIL_PATIENT + "?patientId=" + getPatient(0).getId());
                break;
                
            case DELETE_PATIENT:
                
                try {
                    EditPatient.deletePatient(Integer.parseInt(request.getParameter("defaultId")));
                }
            
                catch (SQLException | NamingException | NumberFormatException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
            
                Controller.redirect(request, response, Controller.USER_CONTROLLER);
                break;
                
            case ADD_DRUG_TO_PATIENT:
                
                if (request.getParameter("inputDosage") == null || request.getParameter("inputName") == null) {
                    Controller.redirect(request, response, ERROR_PATIENT);
                    break;
                }

                try {
                    EditPrescription.addPrescription(EditDrugs.getDrugId(request.getParameter("inputName")), 
                            java.sql.Date.valueOf(request.getParameter("inputStartUsage")), 
                            java.sql.Date.valueOf(request.getParameter("inputStopUsage")),
                            request.getParameter("inputDosage"),
                            Integer.parseInt(request.getParameter("patientId")));
                }
            
                catch (SQLException | NamingException | NumberFormatException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
                
                catch (IllegalArgumentException ex) {
                    Controller.redirect(request, response, ERROR_PATIENT);
                    break;
                }
            
                Controller.redirect(request, response, DETAIL_PATIENT + "?patientId=" + getPatient(0).getId());
                break;
                
            case ADD_EXAM_TO_PATIENT: // TODO zkontrolovat vsude JOINA a LEFT joiny
                // TODO napsat hodnoty IDcek do selectu jako value a ne pico si to tahat z db

                if (request.getParameter("description") == null || request.getParameter("description").isEmpty()) {
                    Controller.redirect(request, response, ERROR_PATIENT);
                    break;
                }
                
                try {
                    EditExamination.addExamination(request.getParameter("description"),
                            java.sql.Date.valueOf(request.getParameter("examTime")),
                            Integer.parseInt(request.getParameter("patientId")),
                            request.getRemoteUser());
                }
            
                catch (SQLException | NamingException | NumberFormatException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
                
                catch (IllegalArgumentException ex) {
                    Controller.redirect(request, response, ERROR_PATIENT);
                    break;
                }
            
                Controller.redirect(request, response, DETAIL_PATIENT + "?patientId=" + getPatient(0).getId());
                break;
                
            case ADD_RESULT_TO_EXAM:
                
                if (request.getParameter("resultDsc") == null || request.getParameter("examId") == null) {
                    Controller.redirect(request, response, ERROR_PATIENT);
                    break;
                }
                
                try {
                    EditResults.addResult(java.sql.Date.valueOf(request.getParameter("resultDate")),
                            request.getParameter("resultDsc"),
                            Integer.parseInt(request.getParameter("examId")));
                }
            
                catch (SQLException | NamingException | NumberFormatException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
                
                catch (IllegalArgumentException ex) {
                    Controller.redirect(request, response, ERROR_PATIENT);
                    break;
                }
            
                Controller.redirect(request, response, DETAIL_PATIENT + "?patientId=" + getPatient(0).getId());
                break;
                
            case ADD_HOSPITALIZATION:
                
                if (request.getParameter("enterDate") == null || request.getParameter("depId") == null) {
                    Controller.redirect(request, response, ERROR_PATIENT);
                    break;
                }
                
                try {
                    
                    Date releaseDate = null;
                    if (!request.getParameter("releaseDate").isEmpty()) {
                        releaseDate = java.sql.Date.valueOf(request.getParameter("releaseDate"));
                    }
                    
                    EditHospitalization.addHospitalization(java.sql.Date.valueOf(request.getParameter("enterDate")),
                            releaseDate, Integer.parseInt(request.getParameter("patientId")),
                            Integer.parseInt(request.getParameter("depId")),
                            request.getRemoteUser());
                }
            
                catch (SQLException | NamingException | NumberFormatException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
                
                catch (IllegalArgumentException ex) {
                    Controller.redirect(request, response, ERROR_PATIENT);
                    break;
                }
            
                Controller.redirect(request, response, DETAIL_PATIENT + "?patientId=" + getPatient(0).getId());
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
     * @return the patient
     */
    public Patient getPatient(int index) {
        return patients.get(index);
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patients.set(0, patient);
    }
    
    public List<Patient> getPatients() {
        return patients;
    }
    
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    /**
     * @return the drugs
     */
    public List<String> getDrugs() {
        return drugs;
    }

    /**
     * @param drugs the drugs to set
     */
    public void setDrugs(List<String> drugs) {
        this.drugs = drugs;
    }
}
