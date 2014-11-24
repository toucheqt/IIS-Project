/**
 * @author Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
 * @author Jiří Kulda, xkulda00@stud.fit.vutbr.cz
 */

package servlets;
// TODO FRONT obnova hesla
import Database.EditDepartment;
import Database.EditDoctor;
import Database.EditIsWorking;
import Database.EditNurse;
import Database.util.MD5Generator;
import Models.Doctor;
import Models.Nurse;
import Utilities.MailSender;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
        "/addNurseDel", "/assignStaff", "/actionAddDoc", "/actionAddNurse", "actionAssignStaff", "/addedItem",
        "/showDoctor", "/showNurse", "/showDepartment", "/delDecWork"})
@ServletSecurity(@HttpConstraint(rolesAllowed = {"root"}))
public class RootController extends HttpServlet {
   
    public static final String ADD_DOC = "/addDoc";
    public static final String ADD_NURSE = "/addNurse";
    public static final String ADDED_ITEM = "/addedItem";
    public static final String ASSIGN_STAFF = "/assignStaff";
    
    public static final String ADD_DOC_DEL = "/addDocDel";
    public static final String ADD_NURSE_DEL = "/addNurseDel";
    
    public static final String ACTION_DOC = "/actionAddDoc";
    public static final String ACTION_NURSE = "/actionAddNurse";
    public static final String ACTION_ASSIGN_STAFF = "/actionAssignStaff";
    
    public static final String SHOW_DOCTOR = "/showDoctor";
    public static final String SHOW_NURSE = "/showNurse";
    public static final String SHOW_DEPARTMENT = "/showDepartment";
    
    public static final String DELETE_DOC_WORK = "/delDocWork";
    
    public static final String VIEW_PATH = "/WEB-INF/views";
    public static final String VIEW_ADD_PATH = "/WEB-INF/views/addItems";
    public static final String VIEW_SHOW_PATH = "/WEB-INF/views/showItems";
    public static final String ERROR_PATH = "/WEB-INF/views/errorPages";
  
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        setAddress(request.getServletPath());
        String attrDoc = "doctor";
        String attrNurse = "nurse";
        String attrDep = "department";
        
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
                
            case ADD_NURSE: {
                List<String> departments;
                try {
                     departments = EditDepartment.getDepartments();
                }
                
                catch (NamingException | SQLException ex) {
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    return;
                }
                
                request.setAttribute(attrDep, departments);
                request.setAttribute(attrNurse, getNurse());
                request.getRequestDispatcher(VIEW_ADD_PATH + "/addNurse.jsp").forward(request, response);
                break;
            }
                
            case ADD_NURSE_DEL: {
                if (getNurse() != null) getNurse().clearNurse();
                
                List<String> departments;
                try {
                     departments = EditDepartment.getDepartments();
                }
                
                catch (NamingException | SQLException ex) {
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    return;
                }
                
                request.setAttribute(attrDep, departments);
                request.setAttribute(attrNurse, getNurse());
                request.getRequestDispatcher(VIEW_ADD_PATH + "/addNurse.jsp").forward(request, response);
                break;
            }
                
            case ADDED_ITEM:
                request.getRequestDispatcher(VIEW_ADD_PATH + "/addedItem.jsp").forward(request, response);
                break;
                
            case ASSIGN_STAFF: {
                List<Doctor> doctors;
                List<String> departments;
                try {
                    doctors = EditDoctor.getDoctorInfo();
                    departments = EditDepartment.getDepartments();
                }

                catch (NamingException | SQLException ex) {
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    return;
                }
                // TODO FRONT predelat par veci v db z varchar na enum
                request.setAttribute(attrDoc, doctors);
                request.setAttribute(attrDep, departments);
                request.getRequestDispatcher(VIEW_ADD_PATH + "/assignStaff.jsp").forward(request, response);
                break;
            }
                
            case SHOW_DOCTOR:
                request.getRequestDispatcher(VIEW_SHOW_PATH + "/showDoctor.jsp").forward(request, response);
                break;
                
            case SHOW_NURSE:
                request.getRequestDispatcher(VIEW_SHOW_PATH + "/showNurse.jsp").forward(request, response);
                break;
                
            case SHOW_DEPARTMENT:
                List<String> departments;
                List<Doctor> doctors;
                List<Nurse> nurse;
                try {
                    departments = EditDepartment.getDepartments();
                    nurse = EditNurse.getNurseInfo();
                    doctors = EditDoctor.getDoctorWork();
                }
                
                catch (NamingException | SQLException ex) {
                    ex.printStackTrace();
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    return;
                }

                request.setAttribute(attrDoc, doctors);
                request.setAttribute(attrNurse, nurse);
                request.setAttribute(attrDep, departments);
                request.getRequestDispatcher(VIEW_SHOW_PATH + "/showDepartment.jsp").forward(request, response);
                break;
                
            default:
                response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_404);
                break;
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        setAddress(request.getServletPath());
        request.setCharacterEncoding("UTF-8");
             
        String regxpAlpha = "[\\p{L}]+";
        String regxpBirthNum = "[0-9]{6}/[0-9]{4}";
        String regxpEmail = ".+@.+";
                
        switch (getAddress()) {
            case ACTION_DOC: {

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
                    
                else if (!getDoctor().getBirthNum().isEmpty() && !getDoctor().getBirthNum().matches(regxpBirthNum)) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?birthNum=True");
                    return;
                }
                    
                else if (getDoctor().getEmail().isEmpty() || !getDoctor().getEmail().matches(regxpEmail)) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?email=True");
                    return;
                }
                                        
                else if (!getDoctor().getCity().isEmpty() && !getDoctor().getCity().matches(regxpAlpha)) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?city=True");
                    return;
                }
                     
                // try to add doctor and then send email with password to him
                try {
                    EditDoctor.addDoctor(getDoctor());
                    request.setAttribute(attribute, getDoctor());
                    MailSender.sendEmail(getDoctor().getEmail(), passwd.substring(0, 7));
                }

                catch (SQLException ex) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?used=True");
                    return;
                }
                
                catch (NamingException | MessagingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    return;
                }          
                       
                Controller.redirect(request, response, ADDED_ITEM + "?doc=True");
                break;
            }

            case ACTION_NURSE: {
                EditNurse editNurse = new EditNurse();
                int departmentId;
                String attribute = "nurse";
                
                setNurse(new Nurse(request.getParameter("inputName"), request.getParameter("inputSurname"),
                        request.getParameter("inputBirthNum"), request.getParameter("inputAddr"),
                        request.getParameter("inputCity"), -1));
                
                // department must be filled
                try {
                    departmentId = EditDepartment.getDepartmentId(request.getParameter("inputDepNum"));
                    getNurse().setDepartmentNum(departmentId);
                }
            
                catch (NamingException | SQLException ex) {
                    request.setAttribute(attribute, getNurse());
                    Controller.redirect(request, response, ADD_NURSE + "?depNum=True");
                    return;
                }       
                
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

                else if (!getNurse().getBirthNum().isEmpty() && !getNurse().getBirthNum().matches(regxpBirthNum)) {
                    request.setAttribute(attribute, getNurse());
                    Controller.redirect(request, response, ADD_NURSE + "?birthNum=True");
                    return;
                }

                else if (!getNurse().getCity().isEmpty() && !getNurse().getCity().matches(regxpAlpha)) {
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
            
            case ACTION_ASSIGN_STAFF: {
                
                int departmentId;
                Integer telNum = null;
                String attribute = "staff";
                String workingTime = null;
                String doctorInfo = null;

                // doctor, department and working time must be filled
                try {
                    doctorInfo = request.getParameter("inputDoctor");
                    
                    // get telephone
                    if (!request.getParameter("inputTelDep").isEmpty()) { 
                        telNum = Integer.parseInt(request.getParameter("inputTelDep"));
                    }
                    
                    // parse email from doctorinfo
                    for (int i = 0; i < doctorInfo.length(); i++) {
                        if (doctorInfo.charAt(i) == ',') {
                            doctorInfo = doctorInfo.substring(i + 2, doctorInfo.length());
                            break;
                        }
                    }
                                  
                    departmentId = EditDepartment.getDepartmentId(request.getParameter("inputDepNum"));
                    workingTime = request.getParameter("inputWorkingTime");
                    
                    if (workingTime == null) throw new NullPointerException();
                }
                
                catch (NamingException | SQLException | NullPointerException ex) {
                    // TODO FRONT ukladat telefon
                    Controller.redirect(request, response, ASSIGN_STAFF + "?other=True");
                    return;
                }
                
                catch (NumberFormatException ex) {
                    Controller.redirect(request, response, ASSIGN_STAFF + "?tel=True");
                    return;
                }
                // TODO FRONT nemel bych pri prirazeni zobrazovat lekare kteri uz plny uvazek maji
                // save data to db
                try {
                    EditIsWorking.assignDoctor(telNum, workingTime, departmentId, doctorInfo);
                    Controller.redirect(request, response, ADDED_ITEM + "?staff=True");
                }
                // TODO FRONT uchovavat aj selecty
                catch (SQLException ex) {
                    Controller.redirect(request, response, ASSIGN_STAFF + "?mail=True");
                    break;
                }
                
                catch (NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
                
                break;
            }
            // TODO: od sestry smazat odebrat a nechat tam jenom zmenit
            case DELETE_DOC_WORK:
                String email = request.getParameter("email");
                String depName = request.getParameter("depName");
                
                try {
                    EditIsWorking.removeDocWork(email, depName);
                }
                
                catch (SQLException | NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break; 
                }
                
                Controller.redirect(request, response, SHOW_DEPARTMENT);
                break;
            
            default:
                Controller.redirect(request, response, Controller.ERROR_500);
                break;
                 
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
