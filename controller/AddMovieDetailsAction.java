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
import model.MovieDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CurrentSession;
import databeans.User;
import databeans.Movie;
import formbeans.AddMovieForm;
import formbeans.RegisterForm;


public class AddMovieDetailsAction extends Action {
	private FormBeanFactory<AddMovieForm> formBeanFactory = FormBeanFactory.getInstance(AddMovieForm.class);
	
	private MovieDAO movieDAO;
	private CurrentSession currentSession;

	public AddMovieDetailsAction(Model model) {
		movieDAO = model.getMovieDAO();
		currentSession = model.getSession();
	}
	
	public String getName() { return "addMovie.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
	        AddMovieForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        request.setAttribute("session", currentSession);
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "addMovie.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "addMovie.jsp";
	        }
	        
	        // get all movie details entered from the form and enter it into database
	        Movie movie = new Movie();
	        movie.setLanguage(form.getLanguage());
	        movie.setGenre(form.getGenre());
	        movie.setPlotSummary(form.getPlotSummary());
	        movie.setCertification(form.getCertification());
	        movie.setRunningTime(Integer.parseInt(form.getRunTime()));
	        movie.setMovieName(form.getMovieName());
	        movie.setReleaseDate(form.getReleaseDate());
	        movieDAO.create(movie);
	        
	        Movie[] movieList = (Movie[])movieDAO.getMovies();
	        int size = movieList.length;
	        movie = movieList[size-1];
	        System.out.println("id new:"+movie.getMovieId());
			// Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession(false);
	        currentSession.setCurrentMovie(movie);
	        System.out.println("Id:"+movie.getMovieId());
	        
	        request.setAttribute("form", null);
	        request.setAttribute("session", currentSession);
	        return "addMovieCrew.jsp";
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "addMovie.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "addMovie.jsp";
        }
    }
}
