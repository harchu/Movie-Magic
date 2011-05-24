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
import model.MovieCrewDAO;
import model.UserMovieDAO;
import model.TrailerDAO;
import model.PhotoDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;

import databeans.CurrentSession;
import databeans.Movie;
import databeans.UserMovie;
import databeans.Photo;
import databeans.Trailer;
import databeans.MovieCrew;
import databeans.User;

public class DisplayMovieDetailsAction extends Action {
	
	private MovieDAO movieDAO;
	private MovieCrewDAO movieCrewDAO;
	private UserMovieDAO userMovieDAO;
	private TrailerDAO trailerDAO;
	private PhotoDAO photoDAO;
	private UserDAO userDAO;
	private CurrentSession currentSession;

	public DisplayMovieDetailsAction(Model model) {
		movieDAO = model.getMovieDAO();
		movieCrewDAO = model.getMovieCrewDAO();
		userMovieDAO = model.getUserMovieDAO();
		trailerDAO = model.getTrailerDAO();
		photoDAO = model.getPhotoDAO();
		userDAO = model.getUserDAO();
		currentSession = model.getSession();
	}
	
	public String getName() { return "displayMovie.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
        	
        	HttpSession session = request.getSession(false);
        	int searchedId = Integer.parseInt(request.getParameter("id"));
        	request.setAttribute("session", currentSession);
	        // lookup movie by id from the main movie database
        	Movie currentMovie = movieDAO.lookup(searchedId);
	
        	// get movie crew, photo, trailer information from the database
	        MovieCrew[] currentCrew = movieCrewDAO.getMovieCrew(searchedId);
	        ArrayList<String> actors = new ArrayList<String>();
	        ArrayList<String> actresses = new ArrayList<String>();
	        ArrayList<String> directors = new ArrayList<String>();
	        ArrayList<String> producers = new ArrayList<String>();
	        for (MovieCrew movCrew : currentCrew){
	        	if (movCrew.getRole().equals("actor"))
	        		actors.add(movCrew.getName());
	        	if (movCrew.getRole().equals("actress"))
	        		actresses.add(movCrew.getName());
	        	if (movCrew.getRole().equals("director"))
	        		directors.add(movCrew.getName());
	        	if (movCrew.getRole().equals("producer"))
	        		producers.add(movCrew.getName());
	        }
	        
	        Photo[] moviePhoto = (Photo[])photoDAO.getPhotos(userDAO.lookup("admin"),searchedId);
	        
	        Trailer[] movieTrailer = (Trailer[])trailerDAO.getTrailers(userDAO.lookup("admin"),searchedId);
	        
	        float avgRating = userMovieDAO.getRatings(searchedId);
	        
	        currentSession.setCurrentActors(actors);
	        currentSession.setCurrentActresses(actresses);
	        currentSession.setCurrentDirectors(directors);
	        currentSession.setCurrentProducers(producers);
	        currentSession.setCurrentMovie(currentMovie);
	        if(moviePhoto.length == 0)
	        	currentSession.setCurrentPhoto(null);
	        else
	        	currentSession.setCurrentPhoto(moviePhoto[0]);
	        if(movieTrailer.length == 0)
	        	currentSession.setCurrentTrailer(null);
	        else
	        	currentSession.setCurrentTrailer(movieTrailer[0]);
	        currentSession.setAvgRating(avgRating);
	        
//	        session.setAttribute("avgRating",avgRating);
//	        session.setAttribute("currentTrailer",movieTrailer[0]);
//	        session.setAttribute("currentPhoto",moviePhoto[0]);
//	        session.setAttribute("currentMovie", currentMovie);
//	        session.setAttribute("currentActors", actors);
//	        session.setAttribute("currentActresses", actresses);
//	        session.setAttribute("currentDirectors", directors);
//	        session.setAttribute("currentProducers", producers);
	        request.setAttribute("session",currentSession);
	        if (session.getAttribute("user")==null)
	        	return "visitor-movies.jsp";
			return "user-movies.jsp";
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	System.out.println(e.getMessage());
        	return "searchMovie.jsp";
        }
    }
}
