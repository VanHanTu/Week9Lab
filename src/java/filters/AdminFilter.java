/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.UserService;

/**
 *
 * @author 759841
 */
@WebFilter(filterName = "AdminFilter", servletNames = {"UserServlet"})
public class AdminFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException 
    {
        
        HttpServletRequest hsr = (HttpServletRequest)request;
        HttpSession session = hsr.getSession();
        UserService us = new UserService();
        String email = (String) session.getAttribute("email");
        
//        if (session.getAttribute("email") == null) 
//        {
//            HttpServletResponse hsre = (HttpServletResponse)response;
//            hsre.sendRedirect("login");
//            return;
//        } 
        
        try 
        {
            User u = us.get(email);
            Role r = u.getRole();
            
            String name = r.getRoleName();
            
            if(!name.contains("admin"))
            {
                HttpServletResponse hsre = (HttpServletResponse)response;
                hsre.sendRedirect("home");
                return;
            }
        } 
        catch (Exception e) 
        {

        }
        
 
  
    }



    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) throws ServletException
    {        
      
     
    }

 
}
