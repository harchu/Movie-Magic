/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.MovieDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.User;
import databeans.Movie;

import formbeans.AddMovieForm;
import formbeans.SearchUserForm;

public class SearchUserAction extends Action{
	private FormBeanFactory<SearchUserForm> formBeanFactory = FormBeanFactory.getInstance(SearchUserForm.class);
	
	private MovieDAO movieDAO;
	private UserDAO userDAO;

	public SearchUserAction(Model model) {
		movieDAO = model.getMovieDAO();
		userDAO = model.getUserDAO();
	}
	
	public String getName() { return "searchUsers.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
        	SearchUserForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "searchUsers.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "searchUsers.jsp";
	        }
	        
	        String searchFor = form.getSearchByName();
	        
	        HttpSession session = request.getSession(false);
	        
	        //lookup database to get data of users by performing partial search
	        User[] users = (User[])userDAO.getUsers(searchFor);
	        
	        request.setAttribute("searchedUserList",users);
	        request.setAttribute("search",searchFor);
	        
	        return "list-users.jsp";
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "searchUsers.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "searchUsers.jsp";
        }
    }
}


