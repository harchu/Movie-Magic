/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package model;

import databeans.Movie;
import java.util.Arrays;
import java.util.ArrayList;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databeans.Movie;
import databeans.MovieCrew;
import databeans.Photo;
import databeans.User;
import databeans.UserMovie;

public class UserMovieDAO {
	private BeanFactory<UserMovie> factory;
	
	public UserMovieDAO(MovieDAO movieDAO,UserDAO userDAO) throws DAOException {
		try {
	        
			// Get a BeanTable to create the "photo" table
	        BeanTable<UserMovie> userMovieDetailsTable = BeanTable.getInstance(UserMovie.class,"user_movie",movieDAO.getFactory(),userDAO.getFactory());
	        
	        if (!userMovieDetailsTable.exists()) userMovieDetailsTable.create("movieId","userName");
	        
	        // Long running web apps need to clean up idle database connections.
	        // So we can tell each BeanTable to clean them up.  (You would only notice
	        // a problem after leaving your web app running for several hours.)
	        userMovieDetailsTable.setIdleConnectionCleanup(true);
	
	        // Get a BeanFactory which the actions will use to read and write
	        // rows of the "photo" table
	        factory = userMovieDetailsTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}


	public void create(UserMovie newUserMovie) throws DAOException {
		try {
        	Transaction.begin();
			UserMovie dbUser = factory.create(newUserMovie.getMovieId(),newUserMovie.getUserName());
			factory.copyInto(newUserMovie,dbUser);
			Transaction.commit();
		} catch (DuplicateKeyException e) {
			throw new DAOException("You have already reviewed this movie.");
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public void delete(int id ,String userName) throws DAOException {
		try {
			Transaction.begin();
			UserMovie u = factory.lookup(id,userName);

    		if (u == null) {
				throw new DAOException("Photo does not exist: id="+id);
    		}

			factory.delete(id);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public UserMovie[] getMovies(User owner) throws DAOException {
		try {
		UserMovie[] list = factory.match(MatchArg.equals("userName",owner.getUserName()));
		return list;
		} catch (RollbackException e) {
		throw new DAOException(e);
		}
	}
	public float getRatings(int movieId) throws DAOException {
		try {
		UserMovie[] list = factory.match(MatchArg.equals("movieId",movieId));
		float total=0;
		if (list.length==0) return 0;
		for(UserMovie entry : list) {
			total = total + entry.getUserRating();
		}
		return total/list.length;
		
		} catch (RollbackException e) {
		throw new DAOException(e);
		}
	}

	
	public UserMovie lookup(int id, String userName) throws DAOException {
		try {
			return factory.lookup(id,userName);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
		
		protected BeanFactory<UserMovie> getFactory() { return factory; }
}


