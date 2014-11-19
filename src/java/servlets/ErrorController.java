/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Touche
 */
@WebServlet(name = "ErrorController", urlPatterns = {"/404", "/500"})
public class ErrorController extends HttpServlet {
    
    private String address;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        setAddress(request.getServletPath());
        
        switch (address) {
                            
            case Controller.ERROR_500:
                request.getRequestDispatcher(RootController.ERROR_PATH + "/500.jsp").forward(request, response);
                break;
                
            default: // as default app should redirect to /404
                request.getRequestDispatcher(RootController.ERROR_PATH + "/404.jsp").forward(request, response);
                break;
                
        }

    }

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
