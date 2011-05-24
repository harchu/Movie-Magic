/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package model;

import databeans.Movie;
import java.util.Arrays;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databeans.Movie;
import databeans.MovieCrew;
import databeans.Photo;
import databeans.User;

public class MovieDAO {
	private BeanFactory<Movie> factory;
	
	public MovieDAO(UserDAO userDAO) throws DAOException {
		try {
	        // Get a BeanTable to create the "photo" table
	        BeanTable<Movie> movieDetailsTable = BeanTable.getInstance(Movie.class,"movie",userDAO.getFactory());
	        
	        if (!movieDetailsTable.exists()) movieDetailsTable.create("movieId");
	        
	        // Long running web apps need to clean up idle database connections.
	        // So we can tell each BeanTable to clean them up.  (You would only notice
	        // a problem after leaving your web app running for several hours.)
	        movieDetailsTable.setIdleConnectionCleanup(true);
	
	        // Get a BeanFactory which the actions will use to read and write
	        // rows of the "photo" table
	        factory = movieDetailsTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}

	protected BeanFactory<Movie> getFactory() { return factory; }
	
	public void create(Movie newMovie) throws DAOException {
		try {
			Transaction.begin();
			Movie dbMovie = factory.create();
			factory.copyInto(newMovie,dbMovie);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	
	
	public Movie[] getMovies(String column, String name) throws DAOException {
		try {
			Movie[] list = factory.match(MatchArg.containsIgnoreCase(column,name));
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	
	public Movie[] getMovies() throws DAOException {
		try {
			Movie[] list = factory.match();
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	
	
	public Movie lookup(int id) throws DAOException {
		try {
			return factory.lookup(id);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public int getCurrentId(Movie movie) throws DAOException {
		try{
			Object[] primaryIdList = (Object[])factory.getPrimaryKeyValues(movie);
			int size = primaryIdList.length;
			System.out.println("last pk:"+primaryIdList[size-1]);
			return (Integer)primaryIdList[size-1];
		}catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
}
