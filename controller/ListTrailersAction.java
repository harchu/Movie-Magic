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
import model.TrailerDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Movie;
import databeans.Trailer;
import formbeans.IdForm;

public class ListTrailersAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private TrailerDAO trailerDAO;
	private UserDAO  userDAO;
	private MovieDAO  movieDAO;
	
    public ListTrailersAction(Model model) {
    	trailerDAO = model.getTrailerDAO();
    	userDAO  = model.getUserDAO();
    	movieDAO  = model.getMovieDAO();
	}

    public String getName() { return "listTrailer.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			HttpSession session = request.getSession(false);
	    	
			// get all the trailers from the database to be displayed by the view
			Trailer[] trailerList = trailerDAO.getTrailers();
			
    		request.setAttribute("trailerList",trailerList);

            return "list_trailer.jsp";
    	} catch (DAOException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} 
    }
}
