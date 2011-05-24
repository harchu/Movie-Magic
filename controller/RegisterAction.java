/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Mailer;
import databeans.User;
import formbeans.RegisterForm;

/*
 * Processes the parameters from the form in register.jsp.
 * If successful:
 *   (1) creates a new User bean
 *   (2) sets the "user" session attribute to the new User bean
 *   (3) redirects to view the originally requested bookmark.
 * 
 */
public class RegisterAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);

	private UserDAO userDAO;
	private Mailer     mailer;
	
	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
		mailer = model.getMailer();
	}

	public String getName() { return "register.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
	        RegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	
	        request.setAttribute("userList",userDAO.getUsers());
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "register.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "register.jsp";
	        }
	
	        // Create the user bean
	        User user = new User(form.getUserName());
	        user.setFirstName(form.getFirstName());
	        user.setLastName(form.getLastName());
	        user.setDateOfBirth(form.getDateOfBirth());
	        user.setSex(form.getSex());
	        user.setPassword(form.getPassword());
	        user.setEmail(form.getEmail());
        	userDAO.create(user);
        
			// Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession(false);
	        session.setAttribute("user",user);
	
	        String message = "Welcome "+user.getFirstName()+" "+user.getLastName()+"! Your Login Id is:"+user.getUserName();
	        
//	        if (mailer == null) {
//		        request.setAttribute("message",message);
//	        	return "reg-no-mail.jsp";
//	        }
//	    
//	        mailer.sendMessage(user.getEmail(),
//	                                      "Welcome to Movie Magic!!",
//	                                      "text/html",
//	                                      message);
	        
	        // After successful registration (and login) send to...
	        String redirectTo = (String) session.getAttribute("redirectTo");
	        if (redirectTo != null) return redirectTo;
	        
	        // If redirectTo is null, redirect to the "manage" action
			String webapp = request.getContextPath();
			if (user.getUserName().equals("admin"))
				return webapp + "/addMovie.do";
			else 
				return webapp + "/homepage.do";
        } catch (DAOException e) {
        	System.out.println("11");
        	errors.add(e.getMessage());
        	return "register.jsp";
        } catch (FormBeanException e) {
        	System.out.println("2");
        	errors.add(e.getMessage());
        	return "register.jsp";
        }
    }
}
