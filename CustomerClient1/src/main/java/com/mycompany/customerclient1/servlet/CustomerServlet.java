/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.customerclient1.servlet;

import com.mycompany.customerclient1.service.CustomerClient;
import com.mycompany.customerservice.entity.CustomerMaster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author Albus
 */
public class CustomerServlet extends HttpServlet {
    @Inject @RestClient CustomerClient customerClient;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<form method='GET'>");
            out.println("Condition: <select name='condition'>");
            out.println("<option value='0' selected>Select</option>");
            out.println("<option value='lt'>LessThan</option>");
            out.println("<option value='gt'>GreaterThan</option>");
            out.println("<option value='lte'>LessThanEqualTo</option>");
            out.println("<option value='gte'>GreaterThanEqualTo</option>");
            out.println("<option value='eq'>EqualsTo</option>");
            out.println("</select>");
            out.println("Rating: <input type='number' name='rating'>");
            out.println("<input type='submit' value='Go'>");
            out.println("</form>");
            
            if(request.getParameter("condition") != null && request.getParameter("rating") != null)
            {
                Collection<CustomerMaster> customerMasters = customerClient.getCustomerMasters(request.getParameter("condition"), Integer.parseInt(request.getParameter("rating")));
                out.println("<table>");
                out.println("<tr><td>Email</td><td>Name</td><td>Rating</td><td>Address</td><td>Phone_Number</td></tr>");                
                customerMasters.forEach(customerMaster -> {
                    out.println("<tr><td>"+customerMaster.getEmail()+"</td><td>"+customerMaster.getName()+"</td><td>"+customerMaster.getRating()+"</td><td>"+customerMaster.getAddress()+"</td><td>"+customerMaster.getPhoneNumber()+"</td></tr>");
                });
                out.println("</table>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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

}
