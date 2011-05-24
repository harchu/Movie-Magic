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

import model.BlogDAO;
import model.Model;
import model.MovieQuizDAO;
import model.PhotoDAO;
import model.UserBlogDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Blog;
import databeans.MovieQuiz;
import databeans.Photo;
import databeans.User;
import databeans.UserBlog;
import formbeans.AddQuizQuestionForm;
import formbeans.CreateBlogForm;
import formbeans.UploadPhotoForm;

public class CreateBlogAction extends Action {
	private FormBeanFactory<CreateBlogForm> formBeanFactory = FormBeanFactory.getInstance(CreateBlogForm.class);

	private static int postId=0;
	private PhotoDAO photoDAO;
	private UserDAO  userDAO;
	private MovieQuizDAO movieQuizDAO;
	private UserBlogDAO userBlogDAO;
	private BlogDAO blogDAO;
	
	public CreateBlogAction(Model model) {
		userDAO  = model.getUserDAO();
		blogDAO = model.getBlogDAO();
		photoDAO = model.getPhotoDAO();
		userBlogDAO = model.getUserBlogDAO();
	}

	public String getName() { return "createBlog.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			
			CreateBlogForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			if (!form.isPresent()) {
	            return "create-blog.jsp";
	        }
			
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0){
	        	request.setAttribute("form", null);
	        	return "create-blog.jsp";
	        }
	        
	        HttpSession session = request.getSession(false);
	        User user = (User)session.getAttribute("user");
	        
	        // get blog data from the form entered by user
	        Blog movieBlog = new Blog();
	        UserBlog userBlog = new UserBlog(form.getBlogSubject());
	        
	        //Set Blog attributes
	        movieBlog.setBlogSubject(form.getBlogSubject());
	        movieBlog.setBlogPost(form.getBlogPost());
	        movieBlog.setUserName(user.getUserName());
	        blogDAO.create(movieBlog);
	        
	        //Set User Blog attributes
	        userBlog.setUserName(user.getUserName());
	        userBlogDAO.create(userBlog);
	        
	        // Update user blogList (there's now one more on the list)
	        UserBlog[] newUserBlogList = userBlogDAO.getUserBlogs(user.getUserName());
	        request.setAttribute("userBlogList",newUserBlogList);
	        return "user-home.jsp";
	 	} catch (DAOException e) {
			errors.add(e.getMessage());
			return "create-blog.jsp";
	 	} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "create-blog.jsp";
		}
    }
}
