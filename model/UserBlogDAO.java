/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package model;

import java.util.Arrays;
import java.util.Date;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databeans.Movie;
import databeans.MovieQuiz;
import databeans.User;
import databeans.UserBlog;

public class UserBlogDAO {
private BeanFactory<UserBlog> factory;
	
	public UserBlogDAO(UserDAO userDAO) throws DAOException {
		try {
			// Get a BeanTable so we can create the "user" table
	        BeanTable<UserBlog> userBlogTable = BeanTable.getInstance(UserBlog.class,"user_blog", userDAO.getFactory());
	        
	        if (!userBlogTable.exists()) userBlogTable.create("blogSubject");
	        
	        // Long running web apps need to clean up idle database connections.
	        // So we can tell each BeanTable to clean them up.  (You would only notice
	        // a problem after leaving your web app running for several hours.)
	        userBlogTable.setIdleConnectionCleanup(true);
	
	        // Get a BeanFactory which the actions will use to read and write rows of the "user" table
	        factory = userBlogTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	public void create(UserBlog newBlog) throws DAOException {
		try {
			Transaction.begin();
			UserBlog dbBlog = factory.create(newBlog.getBlogSubject());
			factory.copyInto(newBlog,dbBlog);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	
	protected BeanFactory<UserBlog> getFactory() { return factory; }
	
	public UserBlog[] getUserBlogs() throws DAOException {
		try {
			UserBlog[] users = factory.match();
			return users;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	
	public UserBlog[] getUserBlogs(String userName) throws DAOException {
		try {
			UserBlog[] users = factory.match(MatchArg.equals("userName",userName));
			return users;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	

	}
}