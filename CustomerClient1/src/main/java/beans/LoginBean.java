/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Albus
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {
    @Inject SecurityContext sc;
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    public void login() throws ServletException, IOException {
        try{
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            Credential credential = new UsernamePasswordCredential(getUserName(), new Password(getPassword()));
            AuthenticationStatus status = sc.authenticate(request, response, withParams().credential(credential));
            request.getSession().setAttribute("role", "");
            
            if(status.equals(SUCCESS)) 
            {
                if(sc.isCallerInRole("Admin"))
                {
                    System.out.println("Admin");
                    request.getSession().setAttribute("role", "Admin");
                    request.getSession().setAttribute("username", getUserName());
                    RequestDispatcher rd = request.getRequestDispatcher("CustomerServlet");
                    rd.forward(request, response);
                }
                else if(sc.isCallerInRole("Student"))
                {
                    System.out.println("Student");
                    request.getSession().setAttribute("role", "Student");
                    request.getSession().setAttribute("username", getUserName());
                    RequestDispatcher rd = request.getRequestDispatcher("NewServlet");
                    rd.forward(request, response);
                }
                else
                {
                    response.sendRedirect("login.xhtml");
                }
            }
            else if(status.equals(SEND_FAILURE))
            {
                response.sendRedirect("login.xhtml");
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
