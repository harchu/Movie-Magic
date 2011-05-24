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
import model.UserBlogDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Movie;
import databeans.Photo;
import databeans.UserBlog;
import formbeans.IdForm;

public class ViewBlogsAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private UserDAO  userDAO;
	private UserBlogDAO  userBlogDAO;
	
    public ViewBlogsAction(Model model) {
    	userDAO  = model.getUserDAO();
    	userBlogDAO  = model.getUserBlogDAO();
	}

    public String getName() { return "view_blogs.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());
			
			//lookup all the users' blogs data from database and pass data to view
			UserBlog[] userBlogList = userBlogDAO.getUserBlogs();
			if (userBlogList == null) {
    			errors.add("No user blogs");
    			return "error.jsp";
    		}
    		request.setAttribute("userBlogList",userBlogList);
            return "blog-list.jsp";
    	} catch (DAOException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} /*catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}*/
    }
}
