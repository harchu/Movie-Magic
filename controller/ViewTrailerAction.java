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

public class ViewTrailerAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private TrailerDAO trailerDAO;
	private UserDAO  userDAO;
	private MovieDAO  movieDAO;
	
    public ViewTrailerAction(Model model) {
    	trailerDAO = model.getTrailerDAO();
    	userDAO  = model.getUserDAO();
    	movieDAO  = model.getMovieDAO();
	}

    public String getName() { return "viewTrailer.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			HttpSession session = request.getSession(false);
	    	
			//lookup trailer by id and pass related data to view
			String id = request.getParameter("id");
			System.out.println("id in view"+id);
    		Trailer m = trailerDAO.lookup(id);
    		request.setAttribute("owner",m.getOwner().getUserName());
    		if (m == null) {
    			errors.add("No picture with id="+id);
    			return "error.jsp";
    		}
    		System.out.println(m.getId()+m.getCaption()+m.getFileName());
    		request.setAttribute("currentTrailer",m);
    		
            return "view_movie.jsp";
    	} catch (DAOException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}
