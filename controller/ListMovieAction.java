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
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Movie;
import databeans.Photo;
import databeans.User;
import formbeans.UserForm;

/*
 * Looks up the movies for a given "user".
 * 
 * If successful:
 *   (1) Sets the "userList" request attribute in order to display
 *       the list of users on the navbar.
 *   (2) Sets the "photoList" request attribute in order to display
 *       the list of given user's photos for selection.
 *   (3) Forwards to list.jsp.
 */
public class ListMovieAction extends Action {
	private FormBeanFactory<UserForm> formBeanFactory = FormBeanFactory.getInstance(UserForm.class);

	private PhotoDAO photoDAO;
	private UserDAO  userDAO;
	private MovieDAO  movieDAO;

    public ListMovieAction(Model model) {
    	photoDAO = model.getPhotoDAO();
    	userDAO  = model.getUserDAO();
    	movieDAO  = model.getMovieDAO();
	}

    public String getName() { return "list_movie.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());

			UserForm form = formBeanFactory.create(request);
	    	
			String userName = form.getUserName();
			if (userName == null || userName.length() == 0) {
				errors.add("User must be specified");
				return "error.jsp";
			}
	
        	User user = userDAO.lookup(userName);
        	if (user == null) {
    			errors.add("Invalid User: "+userName);
    			return "error.jsp";
    		}

        //	Movie[] movieList = movieDAO.getMovies(user);
	       // request.setAttribute("movieList",movieList);
	        return "list_movie.jsp";
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
