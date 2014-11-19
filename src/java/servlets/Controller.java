/**
 * Class controls login requests from clients and redirects them to another servlets
 * according to their user role.
 * @author Ondřej Krpec, xkrpec01@stud.fit.vutbr.cz
 * @author Jiří Kulda, xkulda00@stud.fit.vutbr.cz
 */

package servlets;

import Database.EditDoctor;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
@ServletSecurity(@HttpConstraint(rolesAllowed = {"root", "user"}))
public class Controller extends HttpServlet {
   
    /** Role for admin of the information system */
    public static final String ROLE_ROOT = "root";
    /** Role for any user of the information system */
    public static final String ROLE_USER = "user";
    
    public static final String ROOT_CONTROLLER = "/RootController";
    public static final String USER_CONTROLLER = "/DocController";
    public static final String ERROR_404 = "/404";
    public static final String ERROR_500 = "/500";
    
    /** Default path where the server is launched */
    public static final String DEFAULT_PATH = "http://localhost:8084/IIS_Nemocnice";
    
    private String userRole;

    /**
     * Processes requests for both HTTP GET and POST methods and redirect to next servlets according to
     * user role.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            setUserRole(EditDoctor.getUserRole(request.getRemoteUser()));
        } 
        
        catch (SQLException | NamingException ex) {
            response.sendRedirect(DEFAULT_PATH + ERROR_500);
        }
          
        // redirect root and user accordingly
        switch (userRole) {
            case ROLE_ROOT:
                response.sendRedirect(DEFAULT_PATH + ROOT_CONTROLLER);
                break;
            case ROLE_USER:
                response.sendRedirect(DEFAULT_PATH + USER_CONTROLLER);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                response.sendRedirect(DEFAULT_PATH + ERROR_404);
                break;
        }
 
    }
    
    protected static void redirect(HttpServletRequest request, HttpServletResponse response, String url) {
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.setHeader("Location", request.getContextPath() + url);
    }
    
    /**
     * Sets user role of the current active user.
     * @param userRole string containing system role of the user (root or user) 
     */
    private void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    /**
     * Returns user role of the current active user.
     * @return String reutrns user role of the currently active user.
     */
    public String getUserRole() {
        return userRole;
    }
}
