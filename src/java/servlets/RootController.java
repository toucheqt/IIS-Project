/**
 * @author Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
 * @author Jiří Kulda, xkulda00@stud.fit.vutbr.cz
 */

package servlets;

import Database.EditDepartment;
import Database.EditDoctor;
import Database.EditNurse;
import Database.util.MD5Generator;
import Models.Doctor;
import Models.Nurse;
import Utilities.MailSender;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RootController", urlPatterns = {"/RootController", "/addDoc", "/addDocDel", "/addNurse",
        "/addNurseDel", "/addStaff", "/actionAddDoc", "/actionAddNurse", "/addedItem"})
@ServletSecurity(@HttpConstraint(rolesAllowed = {"root"}))
public class RootController extends HttpServlet {
   
    public static final String ADD_DOC = "/addDoc";
    public static final String ADD_NURSE = "/addNurse";
    public static final String ADDED_ITEM = "/addedItem";
    
    public static final String ADD_DOC_DEL = "/addDocDel";
    public static final String ADD_NURSE_DEL = "/addNurseDel";
    
    public static final String ACTION_DOC = "/actionAddDoc";
    public static final String ACTION_NURSE = "/actionAddNurse";
    
    public static final String VIEW_PATH = "/WEB-INF/views";
    public static final String VIEW_ADD_PATH = "/WEB-INF/views/addItems";
  
    private String address;
    private Doctor doctor;
    private Nurse nurse;

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        setAddress(request.getServletPath());
        String attrDoc = "doctor";
        String attrNurse = "nurse";
        
        switch (address) {
            case Controller.ROOT_CONTROLLER:
                request.getRequestDispatcher(VIEW_PATH + "/root.jsp").forward(request, response);
                break;
                
            case ADD_DOC:
                request.setAttribute(attrDoc, getDoctor());
                request.getRequestDispatcher(VIEW_ADD_PATH + "/addDoc.jsp").forward(request, response);
                break;
                
            case ADD_DOC_DEL:
                if (getDoctor() != null) getDoctor().clearDoctor();
                request.setAttribute(attrDoc, getDoctor());
                request.getRequestDispatcher(VIEW_ADD_PATH + "/addDoc.jsp").forward(request, response);
                break;
                
            case ADD_NURSE:
                request.setAttribute(attrNurse, getNurse());
                request.getRequestDispatcher(VIEW_ADD_PATH + "/addNurse.jsp").forward(request, response);
                break;
                
            case ADD_NURSE_DEL:
                if (getNurse() != null) getNurse().clearNurse();
                request.setAttribute(attrNurse, getNurse());
                request.getRequestDispatcher(VIEW_ADD_PATH + "/addNurse.jsp").forward(request, response);
                break;
                
            case ADDED_ITEM:
                request.getRequestDispatcher(VIEW_ADD_PATH + "/addedItem.jsp").forward(request, response);
                break;
                
            default:
                response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_404);
                break;
                // TODO napsat .jsp error 404 a 500
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        setAddress(request.getServletPath());
        request.setCharacterEncoding("UTF-8");
             
        String regxpAlpha = "[\\p{L}]+";
        String regxpBirthNum = "[0-9]{6}/[0-9]{4}";
        String regxpEmail = ".+@.+";
                
        switch (getAddress()) {
            case ACTION_DOC: {
                EditDoctor editDoctor = new EditDoctor();
                String passwd = UUID.randomUUID().toString();
                String attribute = "doctor";
                setDoctor(new Doctor(request.getParameter("inputName"), request.getParameter("inputSurname"),
                        request.getParameter("inputBirthNum"), request.getParameter("inputAddr"),
                        request.getParameter("inputCity"), request.getParameter("inputMail"),
                        null, MD5Generator.generatePassword(passwd.substring(0, 7))));
                
                // check if the tel. number is filled
                if (!request.getParameter("inputTel").isEmpty()) {
                    try {
                        getDoctor().setTel(Integer.parseInt(request.getParameter("inputTel")));
                    } 
                        
                    catch (NumberFormatException ex) {
                        Controller.redirect(request, response, ADD_DOC + "?tel=True");
                        return;
                    }
                }
                
                // check if all required values are correctly filled
                    
                if (getDoctor().getUsername().isEmpty() || !getDoctor().getUsername().matches(regxpAlpha)) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?username=True");
                    return;
                }
                    
                else if (getDoctor().getSurname().isEmpty() || !getDoctor().getSurname().matches(regxpAlpha)) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?surname=True");
                    return;
                }
                    
                else if (getDoctor().getBirthNum().isEmpty() || !getDoctor().getBirthNum().matches(regxpBirthNum)) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?birthNum=True");
                    return;
                }
                    
                else if (getDoctor().getEmail().isEmpty() || !getDoctor().getEmail().matches(regxpEmail)) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?email=True");
                    return;
                }
                    
                else if (getDoctor().getAddress().isEmpty()) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?address=True");
                    return;
                }
                    
                else if (getDoctor().getCity().isEmpty() || !getDoctor().getCity().matches(regxpAlpha)) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?city=True");
                    return;
                }       
                
                try {
                    editDoctor.addDoctor(getDoctor());
                    Controller.redirect(request, response, ADDED_ITEM + "?doc=True");
                }
                    
                catch (SQLException ex) {
                    try {
                        request.setAttribute(attribute, getDoctor());
                        MailSender.sendEmail(getDoctor().getEmail(), passwd.substring(0, 7));
                        Controller.redirect(request, response, ADD_DOC + "?used=True");
                    }
                    
                    catch (NamingException | MessagingException ex1) {
                        Controller.redirect(request, response, Controller.ERROR_500);
                    }
                }       
                
                break;
            }
        
            case ACTION_NURSE: {
                EditNurse editNurse = new EditNurse();
                int departmentId;
                String attribute = "nurse";
                
                // department must be filled
                try {
                    departmentId = EditDepartment.getDepartmentId(request.getParameter("inputDepNum"));
                }
            
                catch (NamingException | SQLException ex) {
                    request.setAttribute(attribute, getNurse());
                    Controller.redirect(request, response, ADD_NURSE + "?depNum=True");
                    return;
                }       setNurse(new Nurse(request.getParameter("inputName"), request.getParameter("inputSurname"),
                        request.getParameter("inputBirthNum"), request.getParameter("inputAddr"),
                        request.getParameter("inputCity"), departmentId));
                
                // check if all required values are correctly filled
                if (getNurse().getUsername().isEmpty() || !getNurse().getUsername().matches(regxpAlpha)) {
                    request.setAttribute(attribute, getNurse());
                    Controller.redirect(request, response, ADD_NURSE + "?username=True");
                    return;
                }

                else if (getNurse().getSurname().isEmpty() || !getNurse().getSurname().matches(regxpAlpha)) {
                    request.setAttribute(attribute, getNurse());
                    Controller.redirect(request, response, ADD_NURSE + "?surname=True");
                    return;
                }

                else if (getNurse().getBirthNum().isEmpty() || !getNurse().getBirthNum().matches(regxpBirthNum)) {
                    request.setAttribute(attribute, getNurse());
                    Controller.redirect(request, response, ADD_NURSE + "?birthNum=True");
                    return;
                }

                else if (getNurse().getAddress().isEmpty()) {
                    request.setAttribute(attribute, getNurse());
                    Controller.redirect(request, response, ADD_NURSE + "?address=True");
                    return;
                }

                else if (getNurse().getCity().isEmpty() || !getNurse().getCity().matches(regxpAlpha)) {
                    request.setAttribute(attribute, getNurse());
                    Controller.redirect(request, response, ADD_NURSE + "?city=True");
                    return;
                }       
                
                try {
                    editNurse.addNurse(getNurse());             
                    Controller.redirect(request, response, ADDED_ITEM + "?nurse=True");
                }

                catch (SQLException | NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                }       
                
                break;
            }
        }
        
    }
       
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    /**
     * @return the nurse
     */
    public Nurse getNurse() {
        return nurse;
    }

    /**
     * @param nurse the doctor to set
     */
    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }
    
}
