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
import model.UserMovieDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CurrentSession;
import databeans.User;
import databeans.Movie;
import databeans.UserMovie;

import formbeans.RegisterForm;
import formbeans.ReviewForm;

public class GoAction extends Action{

	private FormBeanFactory<ReviewForm> formBeanFactory = FormBeanFactory.getInstance(ReviewForm.class);
	
	public String getName() { return "go.do"; }
	
	private UserMovieDAO userMovieDAO;
	private CurrentSession currentSession;
	
	public GoAction(Model model) {
		userMovieDAO = model.getUserMovieDAO();
	 	currentSession = model.getSession();
	}

	public String perform(HttpServletRequest request) {
        String next = request.getParameter("next");
        request.setAttribute("session",currentSession);
        return next;
    }
}
