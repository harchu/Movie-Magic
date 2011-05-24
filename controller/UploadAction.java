/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.PhotoDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CurrentSession;
import databeans.Photo;
import databeans.Movie;
import databeans.User;
import formbeans.UploadPhotoForm;


/*
 * Uploads a file from the user.  If successful, sets the "userList"
 * and "photoList" request attributes, creates a new Photo bean with the
 * image, and forward (back to) manage.jsp.
 * 
 * Note that to upload a file, the multipart encoding type is used
 * in the HTML form.  This needs to be specially parsed.  The FormBeanFactory
 * can do this, but to do it, the FormBeanFactory uses the Jakarta Commons FileUpload
 * package (org.apache.commons.fileupload).
 * These classes are in the commons-fileupload-x.x.jar file in the webapp's
 * WEB-INF/lib directory.  See the User Guide on
 * http://jakarta.apache.org/commons/fileupload for details.
 */
public class UploadAction extends Action {
	private FormBeanFactory<UploadPhotoForm> formBeanFactory = FormBeanFactory.getInstance(UploadPhotoForm.class);

	private PhotoDAO photoDAO;
	private CurrentSession currentSession;
	
	public UploadAction(Model model) {
    	photoDAO = model.getPhotoDAO();
    	currentSession = model.getSession();
	}

	public String getName() { return "upload.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			UploadPhotoForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			request.setAttribute("session", currentSession);
			User user = (User) request.getSession(false).getAttribute("user");
			if (!form.isPresent()) {
	            return "manage.jsp";
	        }
			
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) 
	        	return "manage.jsp";

	        //convert photo data in request to bytes and store in the database
	        HttpSession session = request.getSession(false);
	        Movie movie = currentSession.getCurrentMovie();
	        int movId = movie.getMovieId();
	        System.out.println("m id"+movId);
	        FileProperty fileProp = form.getFile();
	        System.out.println("creating");
	        Photo photo = new Photo();  // id & position will be set when created
			photo.setBytes(fileProp.getBytes());
			if (form.getCaption().length() > 0) {
				photo.setCaption(fixBadChars(form.getCaption()));
			} else {
				photo.setCaption(fixBadChars(fileProp.getFileName()));
			}
			photo.setContentType(fileProp.getContentType());
			photo.setOwner(user);
			photo.setMovieId(movId);
			photoDAO.create(photo);
			System.out.println("created");
			// Update photoList (there's now one more on the list)
			request.setAttribute("session", currentSession);
			request.setAttribute("form", null);
			if (user.getUserName().equals("admin"))
				return "manage_movies.jsp";
			else {
				request.setAttribute("message","Photo successfully uploaded");
				return "sucess-uploaded.jsp";
			}
	 	} catch (DAOException e) {
			errors.add(e.getMessage());
			return "user-movies.jsp";
	 	} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "user-movies.jsp";
		}
    }
    
    private String fixBadChars(String s) {
		if (s == null || s.length() == 0) return s;
		
		Pattern p = Pattern.compile("[<>\"&]");
        Matcher m = p.matcher(s);
        StringBuffer b = null;
        while (m.find()) {
            if (b == null) b = new StringBuffer();
            switch (s.charAt(m.start())) {
                case '<':  m.appendReplacement(b,"&lt;");
                           break;
                case '>':  m.appendReplacement(b,"&gt;");
                           break;
                case '&':  m.appendReplacement(b,"&amp;");
                		   break;
                case '"':  m.appendReplacement(b,"&quot;");
                           break;
                default:   m.appendReplacement(b,"&#"+((int)s.charAt(m.start()))+';');
            }
        }
        
        if (b == null) return s;
        m.appendTail(b);
        return b.toString();
    }
}
