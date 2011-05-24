/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.BlogDAO;
import model.Model;
import model.MovieDAO;
import model.PhotoDAO;
import model.UserBlogDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Blog;
import databeans.CurrentSession;
import databeans.Movie;
import databeans.Photo;
import databeans.User;
import databeans.UserBlog;
import formbeans.IdForm;
import formbeans.ReplyBlogForm;

public class ReplyBlogAction extends Action {
	private FormBeanFactory<ReplyBlogForm> formBeanFactory = FormBeanFactory.getInstance(ReplyBlogForm.class);

	private UserDAO  userDAO;
	private BlogDAO  blogDAO;
	private UserBlogDAO  userBlogDAO;
	private CurrentSession currentSession;
	
    public ReplyBlogAction(Model model) {
    	userDAO  = model.getUserDAO();
    	blogDAO = model.getBlogDAO();
    	userBlogDAO  = model.getUserBlogDAO();
    	currentSession = model.getSession();
	}

    public String getName() { return "replyBlog.do"; }

    public String perform(HttpServletRequest request) {
    	HttpSession session     = request.getSession(false);
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        User user = (User) request.getSession(false).getAttribute("user"); 
        Blog[] blogList = null;
		try {
			ReplyBlogForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        request.setAttribute("session", currentSession);
	        if (!form.isPresent()) {
	            return "view-post-reply.jsp";
	        }

	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
	        	request.setAttribute("form",null);
	            return "view-post-reply.jsp";
	        }
	        
	        //store the user reply posts under the blog subject in the database
	        Blog movieBlog = new Blog();
	        String subject = currentSession.getBlogSubject();
	        movieBlog.setBlogSubject(subject);
	        movieBlog.setBlogPost(form.getReplyPost());
	        movieBlog.setUserName(user.getUserName());
	        blogDAO.create(movieBlog);
	        
			blogList = blogDAO.getBlogs(subject);
			currentSession.setBlogSubject(subject);
			currentSession.setBlogList(blogList);
			
			request.setAttribute("session", currentSession);
			
			//if user is not logged in do not give option to post a reply on the blog
			if(user == null){
				return "view-only-post.jsp";
			}
			else{
				return "view-post-reply.jsp";
			}
		}catch (DAOException e) {
			e.printStackTrace();
			return "error.jsp";
		}catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "manage.jsp";
		}
    }
}

