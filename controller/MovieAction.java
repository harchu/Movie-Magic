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

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Movie;
import databeans.Photo;
import formbeans.IdForm;

public class MovieAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private MovieDAO movieDAO;

    public MovieAction(Model model) {
    	movieDAO = model.getMovieDAO();
	}

    public String getName() { return "movie.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
        	IdForm form = formBeanFactory.create(request);
        	
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "error.jsp";
            }
            
            // get movie data from database and pass the dta to the view 
        	Movie m = movieDAO.lookup(form.getIdAsInt());
    		if (m != null) request.setAttribute("movie",m);
    		
    		return "movie";
    	} catch (DAOException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}
