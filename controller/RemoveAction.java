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
import databeans.User;
import formbeans.IdForm;

/*
 * Removes a photo.  Given an "id" parameter.
 * Checks to see that id is valid number for a photo and that
 * the logged user is the owner.
 * 
 * Sets up the "userList" and "photoList" request attributes
 * and if successful, forwards back to to "manage.jsp".
 */
public class RemoveAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private PhotoDAO photoDAO;
	private UserDAO  userDAO;

    public RemoveAction(Model model) {
    	photoDAO = model.getPhotoDAO();
    	userDAO  = model.getUserDAO();
	}

    public String getName() { return "remove.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());

	    	IdForm form = formBeanFactory.create(request);
	    	
	    	User user = (User) request.getSession().getAttribute("user");
	    	
	    	// get the photo id from the form and delete the appropriate photo
			int id = form.getIdAsInt();
    		photoDAO.delete(id,user);

    		// Be sure to get the photoList after the delete
        	Photo[] photoList = photoDAO.getPhotos();
	        request.setAttribute("photoList",photoList);

	        return "photo-gallery.jsp";
		} catch (DAOException e) {
    		errors.add(e.getMessage());
    		return "photo-gallery.jsp";
		} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "photo-gallery.jsp";
    	}
    }
}
