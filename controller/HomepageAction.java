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

import model.BlogDAO;
import model.Model;
import model.MovieDAO;
import model.PhotoDAO;
import model.TrailerDAO;
import model.UserBlogDAO;
import model.UserDAO;
import model.UserMovieDAO;
import model.WallDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CurrentSession;
import databeans.Mailer;
import databeans.Photo;
import databeans.Trailer;
import databeans.User;
import databeans.UserBlog;
import databeans.UserMovie;
import databeans.Wall;

import formbeans.MovieCrewForm;
import formbeans.WallPostForm;

public class HomepageAction extends Action{
	private FormBeanFactory<WallPostForm> formBeanFactory = FormBeanFactory.getInstance(WallPostForm.class);
	public String getName() {
		return "homepage.do"; 
	}
	
	private UserDAO  userDAO;
	private UserBlogDAO  userBlogDAO;
	private UserMovieDAO userMovieDAO;
	private PhotoDAO photoDAO;
	private TrailerDAO trailerDAO;
	private MovieDAO movieDAO;
	private WallDAO wallDAO;
	private CurrentSession currentSession;
	private Mailer     mailer;
	
	
    public HomepageAction(Model model) {
    	userDAO  = model.getUserDAO();
    	userBlogDAO  = model.getUserBlogDAO();
    	userMovieDAO = model.getUserMovieDAO();
    	photoDAO = model.getPhotoDAO();
    	trailerDAO = model.getTrailerDAO();
    	movieDAO = model.getMovieDAO();
    	wallDAO = model.getWallDAO();
    	currentSession = model.getSession();
    	mailer = model.getMailer();
	}
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
	    request.setAttribute("errors",errors);
	    
	    try {
	    	
	    	// Get all data related to the user by looking up all the databases including,
	    	// wall data, photos/trailers uploaded, reviews, blog posts etc and pass data
	    	// to the view to be displayed by the user
	    	
	    	WallPostForm form = formBeanFactory.create(request);
	    	request.setAttribute("form",form);
	    	
	    	request.setAttribute("session", currentSession);
	    	// If no params were passed, return with no errors so that the form will be
	    	// presented (we assume for the first time).
	    	
	    	HttpSession session = request.getSession(false);
	    	User user;
	    	String id = request.getParameter("id");
	    	
	    	if (id!=null){
	    		user = userDAO.lookup(id);
	    	}
	    	else {
	    		if (form.getButton()!=null && form.getButton().equals("Scratch"))
	    			user = userDAO.lookup(form.getToName());
	    		else
	    			user = (User)session.getAttribute("user");
	    	}
	    	
	    	if (form.getButton()!=null && form.getButton().equals("Scratch")){
	    		errors.addAll(form.getValidationErrors());
		        if (errors.size() != 0) {
		        	
		            return "homepage.jsp";
		        }
    			Wall curr = new Wall();
    			curr.setFromSender(((User)session.getAttribute("user")).getUserName());
    			curr.setPost(form.getPost());
    			curr.setToUser(form.getToName());
    			wallDAO.create(curr);
    			
    			String message = "You have a new post on your Wall from "+curr.getFromSender();
    	        
//    	        if (mailer == null) {
//    		        request.setAttribute("message",message);
//    	        	return "reg-no-mail.jsp";
//    	        }
//    	    
//    	        mailer.sendMessage(user.getEmail(),
//    	                                      "Movie Magic Update",
//    	                                      "text/html",
//    	                                      message);
    		}
	    	
	    	UserMovie[] user_reviews = userMovieDAO.getMovies(user);
	    	HashMap<String,String> movieReview = new HashMap<String,String>();
	    	for (UserMovie review : user_reviews) {

	    	movieReview.put(movieDAO.lookup(review.getMovieId()).getMovieName(), review.getUserReview());
	    	}
	    	//movieReview.keySet().toArray()[0]
	    	currentSession.setMovieReview(movieReview);
	    	
	    	UserBlog[] blogList = userBlogDAO.getUserBlogs(user.getUserName());	    	
	    	//UserMovie[] user_reviews = userMovieDAO.getMovies(user);
	    	Photo[] user_photos = photoDAO.getPhotos(user);
	    	Trailer[] user_trailer = trailerDAO.getTrailers(user);
	    		    	
	    	currentSession.setUserPosts(wallDAO.getPosts(user.getUserName()));
	    	currentSession.setUserBlogList(blogList);
	    	currentSession.setUser_reviews(user_reviews);
	    	currentSession.setUser_photos(user_photos);
	    	currentSession.setUser_trailer(user_trailer);
	    	currentSession.setPageOwner(user.getUserName());
	    	request.setAttribute("session", currentSession);
		    return "homepage.jsp";
	    } catch (DAOException e) {
			e.printStackTrace();
			return "error.jsp";
		} catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
	}

}
