/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.MovieDAO;
import model.PhotoDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;

import databeans.Photo;
import databeans.User;

public class ManageAction extends Action {

	private PhotoDAO photoDAO;
	private UserDAO  userDAO;
	private MovieDAO  movieDAO;

	public ManageAction(Model model) {
    	photoDAO = model.getPhotoDAO();
    	userDAO  = model.getUserDAO();
    	movieDAO  = model.getMovieDAO();
	}

	public String getName() { return "manage.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());

			// get users data and uploaded photos to be displayed by the view
			User user = (User) request.getSession(false).getAttribute("user");
        	Photo[] photoList = photoDAO.getPhotos(user);

	        request.setAttribute("photoList",photoList);

	        return "manage.jsp";
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
