/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package model;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databeans.Blog;
import databeans.Wall;

public class WallDAO {

	private BeanFactory<Wall> factory;
	
	public WallDAO() throws DAOException {
		try {
	        // Get a BeanTable to create the "photo" table
	        BeanTable<Wall> blogTable = BeanTable.getInstance(Wall.class,"user_wall");
	        
	        if (!blogTable.exists()) blogTable.create("wallId");
	        
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
	
	public void create(Wall newpost) throws DAOException {
		try {
			Transaction.begin();			
			Wall dbBlog = factory.create();
			factory.copyInto(newpost, dbBlog);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public Wall[] getPosts(String toUser) throws DAOException {
		try {
			Wall[] posts = factory.match(MatchArg.equals("toUser",toUser));
			return posts;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
}
