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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static final String ROOT_CONTROLLER = "/RootController";
    
    public static final String ADD_DOC = "/addDoc";
    public static final String ADD_NURSE = "/addNurse";
    public static final String ADDED_ITEM = "/addedItem";
    
    public static final String ADD_DOC_DEL = "/addDocDel";
    public static final String ADD_NURSE_DEL = "/addNurseDel";
    
    public static final String ACTION_DOC = "/actionAddDoc";
    public static final String ACTION_NURSE = "/actionAddNurse";
  
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
        
        switch (address) {
            case ROOT_CONTROLLER:
                request.getRequestDispatcher("/WEB-INF/views/root.jsp").forward(request, response);
                break;
                
            case ADD_DOC:
                request.setAttribute("doctor", getDoctor());
                request.getRequestDispatcher("/WEB-INF/views/addDoc.jsp").forward(request, response);
                break;
                
            case ADD_DOC_DEL:
                if (getDoctor() != null) getDoctor().clearDoctor();
                request.setAttribute("doctor", getDoctor());
                request.getRequestDispatcher("/WEB-INF/views/addDoc.jsp").forward(request, response);
                break;
                
            case ADD_NURSE:
                request.setAttribute("nurse", getNurse());
                request.getRequestDispatcher("/WEB-INF/views/addNurse.jsp").forward(request, response);
                break;
                
            case ADD_NURSE_DEL:
                if (getNurse() != null) getNurse().clearNurse();
                request.setAttribute("nurse", getNurse());
                request.getRequestDispatcher("/WEB-INF/views/addNurse.jsp").forward(request, response);
                break;
                
            case ADDED_ITEM:
                request.getRequestDispatcher("/WEB-INF/views/addedItem.jsp").forward(request, response);
                break;
                
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
                
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAddress(request.getServletPath());
        request.setCharacterEncoding("UTF-8");
        
        if (getAddress().equals(ACTION_DOC)) {
            
            EditDoctor editDoctor = new EditDoctor();
            String passwd = UUID.randomUUID().toString();
                
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
            
            if (getDoctor().getUsername().isEmpty() || !getDoctor().getUsername().matches("[\\p{L}]+")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, ADD_DOC + "?username=True");
                return;
            }
            
            else if (getDoctor().getSurname().isEmpty() || !getDoctor().getSurname().matches("[\\p{L}]+")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, ADD_DOC + "?surname=True");
                return;
            }
                        
            else if (getDoctor().getBirthNum().isEmpty() || !getDoctor().getBirthNum().matches("[0-9]{6}/[0-9]{4}")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, ADD_DOC + "?birthNum=True");
                return;
            }
            
            else if (getDoctor().getEmail().isEmpty() || !getDoctor().getEmail().matches(".+@.+")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, ADD_DOC + "?email=True");
                return;
            }
                        
            else if (getDoctor().getAddress().isEmpty()) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, ADD_DOC + "?address=True");
                return;
            }
            
            else if (getDoctor().getCity().isEmpty() || !getDoctor().getCity().matches("[\\p{L}]+")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, ADD_DOC + "?city=True");
                return;
            }
        
            try {
                editDoctor.addDoctor(getDoctor());               
                Controller.redirect(request, response, ADDED_ITEM);
            }
                
            catch (SQLException ex) {
                try {
                    request.setAttribute("doctor", getDoctor());
                    MailSender.sendEmail(getDoctor().getEmail(), passwd.substring(0, 7));
                    Controller.redirect(request, response, ADD_DOC + "?used=True");
                } catch (NamingException | MessagingException ex1) {
                    Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        else if (getAddress().equals(ACTION_NURSE)) {
            // TODO prepsat uspesne pridani lekare/sesty na promenou
            EditNurse editNurse = new EditNurse();
            int departmentId; 
            
            try {
                departmentId = EditDepartment.getDepartmentId(request.getParameter("inputDepNum"));
            }
            
            catch (NamingException | SQLException ex) {
                ex.printStackTrace();
                Controller.redirect(request, response, "ERROR"); // todo exception handle MUSI SE VYBRAT NEJAKE ODDELENI
                return;
            }
                       
            setNurse(new Nurse(request.getParameter("inputName"), request.getParameter("inputSurname"),
                    request.getParameter("inputBirthNum"), request.getParameter("inputAddr"),
                    request.getParameter("inputCity"), departmentId));
                 
            // check if all required values are correctly filled

            if (getNurse().getUsername().isEmpty() || !getNurse().getUsername().matches("[\\p{L}]+")) {
                request.setAttribute("nurse", getNurse());
                Controller.redirect(request, response, ADD_NURSE + "?username=True");
                return;
            }
            
            else if (getNurse().getSurname().isEmpty() || !getNurse().getSurname().matches("[\\p{L}]+")) {
                request.setAttribute("nurse", getNurse());
                Controller.redirect(request, response, ADD_NURSE + "?surname=True");
                return;
            }
                        
            else if (getNurse().getBirthNum().isEmpty() || !getNurse().getBirthNum().matches("[0-9]{6}/[0-9]{4}")) {
                request.setAttribute("nurse", getNurse());
                Controller.redirect(request, response, ADD_NURSE + "?birthNum=True");
                return;
            }
                        
            else if (getNurse().getAddress().isEmpty()) {
                request.setAttribute("nurse", getNurse());
                Controller.redirect(request, response, ADD_NURSE + "?address=True");
                return;
            }
            
            else if (getNurse().getCity().isEmpty() || !getNurse().getCity().matches("[\\p{L}]+")) {
                request.setAttribute("nurse", getNurse());
                Controller.redirect(request, response, ADD_NURSE + "?city=True");
                return;
            }
        
            try {
                editNurse.addNurse(getNurse());             
                Controller.redirect(request, response, ADDED_ITEM);
            }
                
            catch (SQLException ex) {
                ex.printStackTrace();
                return;
                // todo redirect error
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
