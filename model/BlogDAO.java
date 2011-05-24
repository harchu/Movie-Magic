/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package model;


import java.util.Arrays;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databeans.Blog;
import databeans.UserBlog;


public class BlogDAO {
private BeanFactory<Blog> factory;
	
	public BlogDAO(UserBlogDAO userBlogDAO) throws DAOException {
		try {
	        // Get a BeanTable to create the "photo" table
	        BeanTable<Blog> blogTable = BeanTable.getInstance(Blog.class,"blog",userBlogDAO.getFactory());
	        
	        if (!blogTable.exists()) blogTable.create("postId");
	        
	        // Long running web apps need to clean up idle database connections.
	        // So we can tell each BeanTable to clean them up.  (You would only notice
	        // a problem after leaving your web app running for several hours.)
	        blogTable.setIdleConnectionCleanup(true);
	
	        // Get a BeanFactory which the actions will use to read and write
	        // rows of the "photo" table
	        factory = blogTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	public void create(Blog newBlog) throws DAOException {
		try {
			Transaction.begin();			
			Blog dbBlog = factory.create();
			factory.copyInto(newBlog, dbBlog);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public Blog lookup(String blogSubject) throws DAOException {
		try {
			return factory.lookup(blogSubject);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
		
	protected BeanFactory<Blog> getFactory() { return factory; }
	
	public Blog[] getBlogs() throws DAOException {
		try {
			Blog[] users = factory.match();
			return users;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	public Blog[] getBlogs(String blogSubject) throws DAOException {
		try {
			Blog[] users = factory.match(MatchArg.equals("blogSubject",blogSubject));
			return users;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}

	

}
