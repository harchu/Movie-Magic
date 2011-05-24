/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.PhotoDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Photo;
import formbeans.IdForm;

/*
 * Action called when viewing a photo given it's "id" number.
 * 
 * If successful, looks up the Photo bean by id and sets the
 * "photo" attribute to that bean.  Also sets the "title"
 * attribute to the photo's caption and the "userList"
 * attribute to the list of users.
 * 
 * On success, forward to view.jsp for formatting.
 * 
 * Note: view.jsp simply generates an html <img> tag
 * that uses ImageAction to fetch the bytes for the image.
 * ViewAction allows us to have the template around the image.
 */
public class ViewAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private PhotoDAO photoDAO;
	private UserDAO  userDAO;
	
    public ViewAction(Model model) {
    	photoDAO = model.getPhotoDAO();
    	userDAO  = model.getUserDAO();
	}

    public String getName() { return "view.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());
	    	IdForm form = formBeanFactory.create(request);
	    	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "error.jsp";
	        }
        
    		int id = form.getIdAsInt();
    		Photo p = photoDAO.lookup(id);
    		if (p == null) {
    			errors.add("No picture with id="+id);
    			return "error.jsp";
    		}
    		
    		request.setAttribute("photo",p);
    		request.setAttribute("title",p.getCaption());

            return "view.jsp";
    	} catch (DAOException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}
