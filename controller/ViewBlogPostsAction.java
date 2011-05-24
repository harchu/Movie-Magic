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

public class ViewBlogPostsAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private UserDAO  userDAO;
	private BlogDAO  blogDAO;
	private UserBlogDAO  userBlogDAO;
	private CurrentSession currentSession;
	
    public ViewBlogPostsAction(Model model) {
    	userDAO  = model.getUserDAO();
    	blogDAO = model.getBlogDAO();
    	userBlogDAO  = model.getUserBlogDAO();
    	currentSession = model.getSession();
	}

    public String getName() { return "view_blog_posts.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        Blog[] blogList = null;
		try {
			request.setAttribute("session", currentSession);
			String blogSubject = request.getParameter("blogSubject");
			String reply = request.getParameter("reply");
			
			//get all the blog posts under the requested blog subject from database
			blogList = blogDAO.getBlogs(blogSubject);
			HttpSession session     = request.getSession(false);
			User user = (User)session.getAttribute("user"); 
			currentSession.setBlogSubject(blogSubject);
			currentSession.setBlogList(blogList);

			if (reply!=null) {
				session.setAttribute("reply", reply);
			}
			else session.setAttribute("reply", "false");
			if (blogList == null) {
				errors.add("No user blogs");
				return "error.jsp";
			}
			request.setAttribute("session", currentSession);
			
			//if user is not logged in do not give option to post on the blog
			if(user == null){
				return "view-only-post.jsp";
			}
			else{
				return "view-post-reply.jsp";
			}
		
		}catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		}
    }
}

