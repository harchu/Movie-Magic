/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import databeans.CurrentSession;
import databeans.MovieCrew;
import databeans.Movie;
import formbeans.MovieCrewForm;
import model.Model;
import model.MovieCrewDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

public class AddMovieCrewAction extends Action{
	private FormBeanFactory<MovieCrewForm> formBeanFactory = FormBeanFactory.getInstance(MovieCrewForm.class);
	
	public String getName() { return "addMovieCrew.do"; }

	private MovieCrewDAO  movieCrewDAO;
	 private CurrentSession currentSession;
	
    public AddMovieCrewAction(Model model) {
    	movieCrewDAO  = model.getMovieCrewDAO();
    	currentSession = model.getSession();
	}
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
        	MovieCrewForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        request.setAttribute("session", currentSession);
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "addMovieCrew.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "addMovieCrew.jsp";
	        }
	        
	        HttpSession session = request.getSession(false);
	        Movie movie = currentSession.getCurrentMovie();
	        System.out.println("yoyo:"+movie.getMovieId());
	        
	        //get data from forms and add data in database
	        MovieCrew crew1 = new MovieCrew();
			crew1.setName(form.getActor1());
			crew1.setMovieId(movie.getMovieId());
			crew1.setRole("actor");
			movieCrewDAO.create(crew1);
					
			MovieCrew crew2 = new MovieCrew();
			crew2.setName(form.getActress1());
			crew2.setMovieId(movie.getMovieId());
			crew2.setRole("actress");
			movieCrewDAO.create(crew2);
			
			MovieCrew crew3 = new MovieCrew();
			crew3.setName(form.getDirector1());
			crew3.setMovieId(movie.getMovieId());
			crew3.setRole("director");
			movieCrewDAO.create(crew3);
			
			MovieCrew crew4 = new MovieCrew();
			crew4.setName(form.getProducer1());
			crew4.setMovieId(movie.getMovieId());
			crew4.setRole("producer");
			movieCrewDAO.create(crew4);
			
			if(form.getActor2() != ""){
				MovieCrew crew5 = new MovieCrew();
				crew5.setName(form.getActor2());
				crew5.setMovieId(movie.getMovieId());
				crew5.setRole("actor");
				movieCrewDAO.create(crew5);
			}
			
			if(form.getActor3() != ""){
				MovieCrew crew6 = new MovieCrew();
				crew6.setName(form.getActor3());
				crew6.setMovieId(movie.getMovieId());
				crew6.setRole("actor");
				movieCrewDAO.create(crew6);
			}
			
			if(form.getActress2() != ""){
				MovieCrew crew7 = new MovieCrew();
				crew7.setName(form.getActress2());
				crew7.setMovieId(movie.getMovieId());
				crew7.setRole("actress");
				movieCrewDAO.create(crew7);
			}
			if(form.getActress3() != ""){
				MovieCrew crew8 = new MovieCrew();
				crew8.setName(form.getActress3());
				crew8.setMovieId(movie.getMovieId());
				crew8.setRole("actress");
				movieCrewDAO.create(crew8);
			}
			

			if(form.getDirector2() != ""){
				MovieCrew crew9 = new MovieCrew();
				crew9.setName(form.getDirector2());
				crew9.setMovieId(movie.getMovieId());
				crew9.setRole("director");
				movieCrewDAO.create(crew9);
			}
			

			if(form.getProducer2() != ""){
				MovieCrew crew10 = new MovieCrew();
				crew10.setName(form.getProducer2());
				crew10.setMovieId(movie.getMovieId());
				crew10.setRole("actress");
				movieCrewDAO.create(crew10);
			}
	       
			request.setAttribute("form", null);
			request.setAttribute("session", currentSession);
	        // After successful registration (and login) send to...
			return "manage.jsp";
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "addMovieCrew.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "addMovieCrew.jsp";
        }
    }
    
}
