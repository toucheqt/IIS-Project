/**
 * @author Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
 * @author Jiří Kulda, xkulda00@stud.fit.vutbr.cz
 */

package servlets;

import Database.EditDoctor;
import Database.util.MD5Generator;
import Models.Doctor;
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
        "/addStaff", "/removeDoc", "/removeNurse", "/removeStaff", "/actionAddDoc", "/addedItem"})
@ServletSecurity(@HttpConstraint(rolesAllowed = {"root"}))
public class RootController extends HttpServlet {

    public static final String ROOT_CONTROLLER = "/RootController";
    public static final String ADD_DOC = "/addDoc";
    public static final String ADD_DOC_DEL = "/addDocDel";
    public static final String ADDED_ITEM = "/addedItem";
    
    private String address;
    private Doctor doctor;

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
        EditDoctor editDoctor = new EditDoctor();
        
        if (getAddress().equals("/actionAddDoc")) {
            
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
                    Controller.redirect(request, response, "/addDoc?tel=True");
                    return;
                }
            } 
                        
            // check if all required values are correctly filled
            
            if (getDoctor().getUsername().isEmpty() || !getDoctor().getUsername().matches("[\\p{L}]+")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, "/addDoc?username=True");
                return;
            }
            
            else if (getDoctor().getSurname().isEmpty() || !getDoctor().getSurname().matches("[\\p{L}]+")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, "/addDoc?surname=True");
                return;
            }
                        
            else if (getDoctor().getBirthNum().isEmpty() || !getDoctor().getBirthNum().matches("[0-9]{6}/[0-9]{4}")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, "/addDoc?birthNum=True");
                return;
            }
            
            else if (getDoctor().getEmail().isEmpty() || !getDoctor().getEmail().matches(".+@.+")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, "/addDoc?email=True");
                return;
            }
                        
            else if (getDoctor().getAddress().isEmpty()) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, "/addDoc?address=True");
                return;
            }
            
            else if (getDoctor().getCity().isEmpty() || !getDoctor().getCity().matches("[\\p{L}]+")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, "/addDoc?city=True");
                return;
            }
        
            try {
                editDoctor.addDoctor(getDoctor());               
                Controller.redirect(request, response, "/addedItem");
            }
                
            catch (SQLException ex) {
                try {
                    request.setAttribute("doctor", getDoctor());
                    MailSender.sendEmail(getDoctor().getEmail(), passwd.substring(0, 7));
                    Controller.redirect(request, response, "/addDoc?used=True");
                } catch (NamingException ex1) {
                    Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (MessagingException ex1) {
                    Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex1);
                }
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
    
}
