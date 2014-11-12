/**
 * TODO comment
 * @author Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
 * @author Jiří Kulda, xkulda00@stud.fit.vutbr.cz
 */

package servlets;

import Database.EditDoctor;
import Database.EditUser;
import Models.Doctor;
import Models.User;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private User user;

    /**
     * TODO comment
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
        EditUser editUser = new EditUser();
        
        if (getAddress().equals("/actionAddDoc")) {
            setDoctor(new Doctor(request.getParameter("inputName"), request.getParameter("inputSurname"), 
                    request.getParameter("inputBirthNum"), request.getParameter("inputAddr"),
                    request.getParameter("inputCity"), null, request.getParameter("inputMail")));
            
            // first check if the tel. number is filled
            if (!request.getParameter("inputTel").isEmpty()) {
                try {
                    getDoctor().setTelNum(Integer.parseInt(request.getParameter("inputTel")));
                }

                catch (Exception ex) {
                    Controller.redirect(request, response, "/addDoc?telNum=True");
                    return;
                }
            }
            
            // check if all required values are correctly filled
            if (getDoctor().getName().isEmpty() || !doctor.getName().matches("[\\p{L}]+")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, "/addDoc?docName=True");
                return;
            }
            
            else if (getDoctor().getSurname().isEmpty() || !doctor.getSurname().matches("[\\p{L}]+")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, "/addDoc?docSurname=True");
                return;
            }
            
            else if (getDoctor().getBirthNum().isEmpty() || !doctor.getBirthNum().matches("[0-9]{6}/[0-9]{4}")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, "/addDoc?birthNum=True");
                return;
            }
            
            else if (getDoctor().getAddress().isEmpty()) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, "/addDoc?addr=True");
                return;
            }
            
            else if (getDoctor().getCity().isEmpty() || !doctor.getCity().matches("[\\p{L}]+")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, "/addDoc?city=True");
                return;
            }
            
            else if (getDoctor().getMail().isEmpty() || !doctor.getMail().matches(".+@.+")) {
                request.setAttribute("doctor", getDoctor());
                Controller.redirect(request, response, "/addDoc?mail=True");
                return;
            }
        
            try { // TODO: osetrit aby se provedlo vse
                editDoctor.addDoctor(getDoctor());
                
                setUser(new User("x" + getDoctor().getSurname() + getDoctor().getId(), 
                        Controller.ROLE_USER, stringToMd5("root")));
                editUser.addUser(getUser());
                editUser.addRole(getUser());
                
                Controller.redirect(request, response, "/addedItem");
            }
                
            catch (SQLException | NoSuchAlgorithmException ex) {
                Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    private String stringToMd5(String passwd) throws NoSuchAlgorithmException {
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(passwd.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        
        return sb.toString();
        
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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
}
