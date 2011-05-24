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
import model.MovieCrewDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CurrentSession;
import databeans.User;
import databeans.Movie;
import databeans.MovieCrew;
import formbeans.AddMovieForm;
import formbeans.SearchMovieForm;

public class SearchAction extends Action{
	private FormBeanFactory<SearchMovieForm> formBeanFactory = FormBeanFactory.getInstance(SearchMovieForm.class);
	
	private MovieDAO movieDAO;
	private MovieCrewDAO movieCrewDAO;
	private CurrentSession currentSession;

	public SearchAction(Model model) {
		movieDAO = model.getMovieDAO();
		movieCrewDAO = model.getMovieCrewDAO();
		currentSession = new CurrentSession();
	}
	
	public String getName() { return "searchMovie.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
        	SearchMovieForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        request.setAttribute("session", currentSession);
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "searchMovie.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "searchMovie.jsp";
	        }
	        
	        String searchFor = form.getSearchByName();
	        String option = form.getOption();

	        // perform partial search on database records on basis of names of actors, actresses,
	        // genre, movie etc.
	        ArrayList<Integer> idList = new ArrayList<Integer>();
	        ArrayList<String> movieList = new ArrayList<String>();
	        ArrayList<String> nameList = new ArrayList<String>();
	        System.out.println(movieCrewDAO.lookup(1).getRole());
	        if (form.getOption().equals("actor") || form.getOption().equals("actress")) {
	        	MovieCrew[] movieCrewList = movieCrewDAO.getMovieCrew(option,searchFor);
	        	System.out.println(movieCrewList.length);
	        	for(MovieCrew movCrew : movieCrewList){
	        		nameList.add(movCrew.getName());
	        		idList.add(movCrew.getMovieId());
	        		movieList.add(movieDAO.lookup(movCrew.getMovieId()).getMovieName());
	        	}
	        }
	        if (form.getOption().equals("genre")) {
	        	Movie[] genreMovieList = movieDAO.getMovies("genre",searchFor);
	        	for(Movie mov : genreMovieList){
	        		nameList.add(mov.getGenre());
	        		idList.add(mov.getMovieId());
	        		movieList.add(movieDAO.lookup(mov.getMovieId()).getMovieName());
	        	}
	        }
	        if (form.getOption().equals("movie name")) {
	        	Movie[] nameMovieList = movieDAO.getMovies("movieName",searchFor);
	        	for(Movie mov : nameMovieList){
	        		idList.add(mov.getMovieId());
	        		movieList.add(movieDAO.lookup(mov.getMovieId()).getMovieName());
	        	}
	        }
			
	        // send the search results to view to display
	        HttpSession session = request.getSession(false);
	        request.setAttribute("search", searchFor);
	        currentSession.setMovieList(movieList);
	        currentSession.setIdList(idList);
	        currentSession.setNameList(nameList);
	        request.setAttribute("session",currentSession);
	        return "list-movies.jsp";
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "addMovie.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "addMovie.jsp";
        }
    }
}


