/**
 * @author Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
 * @author Jiří Kulda, xkulda00@stud.fit.vutbr.cz
 */
// TODO do zobrazeni pacientu pridat datum vysetreni
// TODO pri vyhledani pacienta jeste predavat oddeleni, leky, osetrujici lekare atd.
package servlets;
// TODO FRONT obnova hesla
import Database.EditDepartment;
import Database.EditDoctor;
import Database.EditIsWorking;
import Database.EditNurse;
import Utilities.MD5Generator;
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
        "/showDoctor", "/showNurse", "/showDepartment", "/delDecWork", "/updateDocWork", "/updateNurseWork",
        "/delNurse", "/updateNurse", "updateDoctor", "delDoctor"})
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
    public static final String DELETE_NURSE = "/delNurse";
    public static final String DELETE_DOCTOR = "/delDoctor";
    
    public static final String UPDATE_DOC_WORK = "/updateDocWork";
    public static final String UPDATE_NURSE_WORK = "/updateNurseWork";
    public static final String UPDATE_NURSE = "/updateNurse";
    public static final String UPDATE_DOCTOR = "/updateDoctor";
    
    public static final String VIEW_PATH = "/WEB-INF/views"; // TODO tohle by melo byt v hlavnim controlleru
    public static final String VIEW_ADD_PATH = "/WEB-INF/views/addItems";
    public static final String VIEW_SHOW_PATH = "/WEB-INF/views/showItems";
    public static final String ERROR_PATH = "/WEB-INF/views/errorPages";
    public static final String SUCCESS_PATH = "/WEB-INF/views/successPages";
  
    private String address;
    private Doctor doctor;
    private Nurse nurse;
    private Doctor activeUser;
    

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        setAddress(request.getServletPath()); // TODO refaktor
        String attrDoc = "doctor";
        String attrNurse = "nurse";
        String attrDep = "department";
        String attrActiveUser = "activeUser";
         
                
        switch (address) {
            case Controller.ROOT_CONTROLLER:
                
                // load active users info
                try {
                    activeUser = EditDoctor.getDoctor(request.getRemoteUser());
                }

                catch (SQLException | NamingException ex) {
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    return;
                }
                
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                request.getRequestDispatcher(VIEW_PATH + "/root.jsp").forward(request, response);
                break;
                
            case ADD_DOC:
                request.setAttribute(attrDoc, getDoctor());
                request.setAttribute(attrActiveUser, activeUser);
                request.getRequestDispatcher(VIEW_ADD_PATH + "/addDoc.jsp").forward(request, response);
                break;
                
            case ADD_DOC_DEL:
                if (getDoctor() != null) getDoctor().clearDoctor();
                request.setAttribute(attrDoc, getDoctor());
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                request.getRequestDispatcher(VIEW_ADD_PATH + "/addDoc.jsp").forward(request, response);
                break;
                
            case ADD_NURSE: {
                List<String> departments; // TOTO muze byt klido v konstruktoru
                try {
                     departments = EditDepartment.getDepartments();
                }
                
                catch (NamingException | SQLException ex) {
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    return;
                }
                
                request.setAttribute(attrDep, departments);
                request.setAttribute(attrNurse, getNurse());
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
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
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
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
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                request.getRequestDispatcher(VIEW_ADD_PATH + "/assignStaff.jsp").forward(request, response);
                break;
            }
                
            case SHOW_DOCTOR: {
                List<Doctor> doctors;
                List<String> departments;
                
                try {
                    doctors = EditDoctor.getDoctors();
                    departments = EditDepartment.getDepartments();
                }
                
                catch (SQLException | NamingException ex) {
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    break; // TODO nahradit returny za break;
                }
                
                request.setAttribute(attrDoc, doctors);
                request.setAttribute(attrDep, departments);
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                request.getRequestDispatcher(VIEW_SHOW_PATH + "/showDoctor.jsp").forward(request, response);
                break;
            }
                
            case SHOW_NURSE: {
                List<Nurse> nurses;
                List<String> departments;
                
                try {
                    departments = EditDepartment.getDepartments();
                    nurses = EditNurse.getNurse();
                }
                
                catch (SQLException | NamingException ex) {
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    return;
                }
                
                request.setAttribute(attrNurse, nurses);
                request.setAttribute(attrDep, departments);
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
                request.getRequestDispatcher(VIEW_SHOW_PATH + "/showNurse.jsp").forward(request, response);
                break;
            }
                
            case SHOW_DEPARTMENT:
                List<String> departments;
                List<Doctor> doctors;
                List<Nurse> nurses;
                try {
                    departments = EditDepartment.getDepartments();
                    nurses = EditNurse.getNurseInfo();
                    doctors = EditDoctor.getDoctorWork();
                }
                
                catch (NamingException | SQLException ex) {
                    response.sendRedirect(Controller.DEFAULT_PATH + Controller.ERROR_500);
                    return;
                }
                
                request.setAttribute(attrNurse, nurses);
                request.setAttribute(attrDoc, doctors);
                request.setAttribute(attrDep, departments);
                request.setAttribute(Controller.ATTR_ACTIVE_USER, activeUser);
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
             
        String regxpEmail = ".+@.+";
        String regxpNotLetters = "[^a-zA-z]+";

        switch (getAddress()) {
            case ACTION_DOC: {

                String passwd = UUID.randomUUID().toString();
                String attribute = "doctor"; // TODO prepsat tady ty atrributy na tridni prommene konstany
                setDoctor(new Doctor(request.getParameter("inputName"), request.getParameter("inputSurname"),
                        request.getParameter("inputBirthNum"), request.getParameter("inputAddr"),
                        request.getParameter("inputCity"), request.getParameter("inputMail"),
                        request.getParameter("inputTel"), MD5Generator.generatePassword(passwd.substring(0, 7))));   
                
                // check if all required values are correctly filled
                if (getDoctor().getUsername().isEmpty()) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?username=True");
                    return;
                }
                    
                else if (getDoctor().getSurname().isEmpty()) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?surname=True");
                    return;
                }

                else if (getDoctor().getEmail().isEmpty() || !getDoctor().getEmail().matches(regxpEmail)) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?email=True");
                    return;
                }                      
                
                else if (!getDoctor().getTel().isEmpty() && !getDoctor().getTel().matches(regxpNotLetters)) {
                    request.setAttribute(attribute, getDoctor());
                    Controller.redirect(request, response, ADD_DOC + "?tel=True");
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
                    ex.printStackTrace();
                    Controller.redirect(request, response, Controller.ERROR_500);
                    return;
                }          
                       
                Controller.redirect(request, response, ADDED_ITEM + "?doc=True");
                break;
            }

            case ACTION_NURSE: {
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
                if (getNurse().getUsername().isEmpty()) {
                    request.setAttribute(attribute, getNurse());
                    Controller.redirect(request, response, ADD_NURSE + "?username=True");
                    return;
                }

                else if (getNurse().getSurname().isEmpty()) {
                    request.setAttribute(attribute, getNurse());
                    Controller.redirect(request, response, ADD_NURSE + "?surname=True");
                    return;
                }      
                
                try {
                    EditNurse.addNurse(getNurse());             
                    Controller.redirect(request, response, ADDED_ITEM + "?nurse=True");
                }

                catch (SQLException | NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                }       
                
                break;
            }
            
            case ACTION_ASSIGN_STAFF: {
                
                int departmentId;
                String telNum = null;
                String attribute = "staff";
                String workingTime;
                String doctorInfo;

                // doctor, department and working time must be filled
                try {
                    doctorInfo = request.getParameter("inputDoctor");
                    
                    // get telephone
                    if (!request.getParameter("inputTelDep").isEmpty()) { 
                        telNum = request.getParameter("inputTelDep");

                        if (!telNum.matches(regxpNotLetters)) {
                            Controller.redirect(request, response, ASSIGN_STAFF + "?tel=True");
                            break;
                        }
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
                
                // TODO FRONT nemel bych pri prirazeni zobrazovat lekare kteri uz plny uvazek maji
                // save data to db
                // TODO chtelo by to lekare zobrazovat jen jednou
                // TODO kontrolovat zadavani poli i na to, jestli nejsou moc dlouhe
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

            case DELETE_DOC_WORK: {
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
            }
                
            case UPDATE_DOC_WORK:
                // TODO uvolnovat nepouzivane objekty
                // TODO refaktorovat vsechny IDCKA u selectu (jsp)
                try {
                    EditIsWorking.updateDocWork(request.getParameter("inputTel"),
                            request.getParameter("inputWorkingTime"), 
                            EditDepartment.getDepartmentId(request.getParameter("inputDepName")), // TOTO REFAKTOROVAT TVLE
                            EditDepartment.getDepartmentId(request.getParameter("defaultDepName")),
                            request.getParameter("defaultEmail"));
                }
// TODO: odstranit osetreni jmen a cisel pomoci regulaku
                // TODO A: poresit aby se u updatovani nemohlo stat, ze nezadam hodnotu

            // TODO zavest praci s db jen tam, kde je to nutne, udelat si tridni promenne do kterych nacist data z db
                // nekdy na zacatku a pak to jen aktualizovat, pokud se provede delete nebo update
                catch (SQLException | NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
            
                Controller.redirect(request, response, SHOW_DEPARTMENT);
                break;
                
            case UPDATE_NURSE_WORK:
                
                try {
                    EditNurse.updateWork(Integer.parseInt(request.getParameter("defaultId")),
                            EditDepartment.getDepartmentId(request.getParameter("inputDepName")));
                }
            
                catch (NumberFormatException | SQLException | NamingException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
            
                Controller.redirect(request, response, SHOW_DEPARTMENT);
                break;
                
            case DELETE_DOCTOR:
                
                try {
                    EditDoctor.deleteDoctor(request.getParameter("defaultEmail"));
                }
            
                catch (SQLException | NamingException ex) {
                    ex.printStackTrace();
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
            
                Controller.redirect(request, response, SHOW_DOCTOR);
                break;
                
            case DELETE_NURSE:
                                
                try {
                    EditNurse.deleteNurse(Integer.parseInt(request.getParameter("defaultId-delete")));
                }
            
                catch (SQLException | NamingException | NumberFormatException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
            
                Controller.redirect(request, response, SHOW_NURSE);
                break;
                
            case UPDATE_DOCTOR:
                
                try {
                    EditDoctor.updateDoctor(new Doctor(request.getParameter("inputName"), 
                            request.getParameter("inputSurname"), request.getParameter("inputBirthNum"), 
                            request.getParameter("inputAddr"), request.getParameter("inputCity"), 
                            request.getParameter("inputEmail"), request.getParameter("inputTel"), null),
                            request.getParameter("defaultEmail"));
                }
            
                catch (SQLException | NamingException | NumberFormatException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break; // TODO pri numberFormatEx vypsat error, ne chybu serveru
                }
            
                Controller.redirect(request, response, SHOW_DOCTOR);
                break;
                
            case UPDATE_NURSE:
                
                try {
                    EditNurse.updateNurse(new Nurse(Integer.parseInt(request.getParameter("defaultId-change")), 
                            request.getParameter("inputName"), request.getParameter("inputSurname"),
                            request.getParameter("inputBirthNum"), request.getParameter("inputAddr"),
                            request.getParameter("inputCity"), null));
                }
            
                catch (SQLException | NamingException | NumberFormatException ex) {
                    Controller.redirect(request, response, Controller.ERROR_500);
                    break;
                }
            
                Controller.redirect(request, response, SHOW_NURSE);
                break;
                // TODO refaktorovat html
                // TODO nevracet pouze na hlavni stranku zobrazeni, ale vracet i podle oddeleni                
                // TODO predelat html modaly do tabulek
                // TODO mit jen jeden div a hazet do nej data dynamicky
            default:
                Controller.redirect(request, response, Controller.ERROR_404);
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
