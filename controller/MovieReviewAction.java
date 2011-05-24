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

public class MovieReviewAction extends Action{

	private FormBeanFactory<ReviewForm> formBeanFactory = FormBeanFactory.getInstance(ReviewForm.class);
	
	public String getName() { return "movieReview.do"; }
	
	private UserMovieDAO userMovieDAO;
	private CurrentSession currentSession;
	
	public MovieReviewAction(Model model) {
		userMovieDAO = model.getUserMovieDAO();
	 	currentSession = model.getSession();
	}

	public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
        	ReviewForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        HttpSession session = request.getSession(false);
	        request.setAttribute("session", currentSession);
	        if (!form.isPresent()) {
	            return "movie-review.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "movie-review.jsp";
	        }
	        
	        // get review and rating from the form and store in database
	        int moveid = (currentSession.getCurrentMovie()).getMovieId();
	        String username = ((User)session.getAttribute("user")).getUserName();
	        UserMovie movie = new UserMovie(moveid,username);
	        movie.setUserReview(form.getReview());
	        movie.setUserRating(Integer.parseInt(form.getRating()));
	        request.setAttribute("session", currentSession);
	        userMovieDAO.create(movie);
	        
	        request.setAttribute("message","Your review is successfully uploaded");
	        return "sucess-uploaded.jsp";
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	request.setAttribute("message",e.getMessage());
        	return "sucess-uploaded.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "movie-review.jsp";
        }
    }
}
