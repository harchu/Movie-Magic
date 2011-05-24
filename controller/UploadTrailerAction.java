/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;


import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.MovieDAO;
import model.TrailerDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

import databeans.CurrentSession;
import databeans.User;
import databeans.Movie;
import databeans.Trailer;
import formbeans.UploadTrailerForm;
import formbeans.RegisterForm;



public class UploadTrailerAction extends Action {
	private FormBeanFactory<UploadTrailerForm> formBeanFactory = FormBeanFactory.getInstance(UploadTrailerForm.class);
	
	private MovieDAO movieDAO;
	private TrailerDAO trailerDAO;
	private UserDAO userDAO;
	private CurrentSession currentSession;

	
	public UploadTrailerAction(Model model) {
		movieDAO = model.getMovieDAO();
		trailerDAO = model.getTrailerDAO();
		currentSession = model.getSession();
	}
	
	public String getName() { return "uploadTrailer.do"; }
    
    @SuppressWarnings("deprecation")
	public String perform(HttpServletRequest request) {
    List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
        	request.setAttribute("session", currentSession);
	        HttpSession session = request.getSession(false);
	        User user = (User)session.getAttribute("user");
	        String filename = null;
			FilePart fpImg = null;
			MultipartParser mp = null;
	        int imageid = 0;
	        String caption=null;
	        
	        //create MultipartParser object from request containing video
	        mp = new MultipartParser(request, 100000000);
	        
	        fpImg = (FilePart)mp.readNextPart();
	       
	        //copy the video from client to the server ith id as file name
	        if(fpImg.getContentType().contains("video"))
	        {
	        	filename = fpImg.getFileName();
	        	errors.addAll(getValidationErrors(filename));
		        if (errors.size() > 0) return "manage_movies.jsp";
	            System.out.println("file: "+filename);
	            System.out.println("file: "+caption);
	            System.out.println("file: "+trailerDAO.getTrailers().length);
	            imageid = trailerDAO.getNewId();
	            //File f = new File("/usr/share/java/apache-tomcat-6.0.18/webapps/images" + File.separator + filename  + filename.substring(filename.indexOf(".")) );
	            File f = new File(request.getRealPath("/") + "images/"+ imageid + filename.substring(filename.indexOf(".")));
                fpImg.writeTo(f);
              
            }
	        else{
	        	errors.add("Please enter proper file");
	        	request.setAttribute("session", currentSession);
	        	return "manage_movies.jsp";
	        }
	        Movie movie = currentSession.getCurrentMovie();
	        int movId = movie.getMovieId();
	        User owner = (User)session.getAttribute("user");
	        ParamPart cap = (ParamPart)mp.readNextPart();
	        caption = cap.getStringValue();
	        errors.addAll(getCaptionValidationErrors(caption));
	        if (errors.size() > 0) return "manage_movies.jsp";
	        System.out.println("caption:"+caption);
	        Trailer trailer = new Trailer(caption);
	        
	        //add video related data in database
            String uploaded="";
            if(fpImg.getContentType().contains("video"))
            {
            	trailer.setFileName(filename);
            	trailer.setContentType(fpImg.getContentType());
            	trailer.setOwner(owner);
            	trailer.setId(imageid);
            	trailer.setMovieId(movId);
            	trailerDAO.create(trailer);
            	uploaded="Your file has been uploaded successfully";
            }
            else
                uploaded= "ERROR: Only image files can be uploaded";
            request.setAttribute("session", currentSession);
            if (user.getUserName().equals("admin"))
				return "addMovie.jsp";
			else {
				request.setAttribute("message","Video file successfully uploaded");
				return "sucess-uploaded.jsp";
			}
        }
        catch (Exception e){
        	System.out.println(e.getMessage());
        	return "user-movies.jsp";
        } 
    }
    
   

public List<String> getValidationErrors(String file) {
	List<String> errors = new ArrayList<String>();
	
	
	if (file.getBytes() == null || file.getBytes().length == 0) {
		errors.add("No file data was received");
	}

	if (file == null || file.length() == 0) {
		errors.add("No filename! ");
		return errors;
	}
	
	if (file.matches("[<>\"]")) {
		errors.add("File name may not contain angle brackets or quotes");
	}
	
	int lastDot = file.lastIndexOf('.');
	if (lastDot == -1) {
		errors.add("No extension on file name.  Must be one of: "+Movie.EXTENSIONS);
		return errors;
	}
	
	
//	String extension = file.getFileName().substring(lastDot);
//	if (!Movie.EXTENSIONS.contains(extension)) {
//		errors.add("Invalid extension on file name.  Must be one of: "+Movie.EXTENSIONS);
//	}

	return errors;
}

public List<String> getCaptionValidationErrors(String caption) {
	List<String> errors = new ArrayList<String>();
	if (caption == null || caption.length() == 0) {
		errors.add("A caption is required");
		return errors;
	}
	return errors;
}

}