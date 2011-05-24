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

public class MovieCrewDAO {
	private BeanFactory<MovieCrew> factory;
	
	public MovieCrewDAO(MovieDAO movieDAO) throws DAOException {
		try {
	        // Get a BeanTable to create the "photo" table
	        BeanTable<MovieCrew> movieCrewDetailsTable = BeanTable.getInstance(MovieCrew.class,"movie_crew",movieDAO.getFactory());
	        
	        if (!movieCrewDetailsTable.exists()) movieCrewDetailsTable.create("castId");
	        
	        // Long running web apps need to clean up idle database connections.
	        // So we can tell each BeanTable to clean them up.  (You would only notice
	        // a problem after leaving your web app running for several hours.)
	        movieCrewDetailsTable.setIdleConnectionCleanup(true);
	
	        // Get a BeanFactory which the actions will use to read and write
	        // rows of the "photo" table
	        factory = movieCrewDetailsTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}


	public void create(MovieCrew newMovieCrew) throws DAOException {
		try {
			Transaction.begin();
			MovieCrew dbMovie = factory.create();
			factory.copyInto(newMovieCrew,dbMovie);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	
	public MovieCrew[] getMovieCrew() throws DAOException {
		try {
			MovieCrew[] list = factory.match();
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public MovieCrew[] getMovieCrew(int movieId) throws DAOException {
		try {
			MovieCrew[] list = factory.match(MatchArg.equals("movieId",movieId));
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public MovieCrew[] getMovieCrew(String role, String name) throws DAOException {
		try {
			MovieCrew[] list = factory.match(MatchArg.containsIgnoreCase("role",role),MatchArg.containsIgnoreCase("name",name));
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public MovieCrew lookup(int id) throws DAOException {
		try {
			return factory.lookup(id);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
		
		protected BeanFactory<MovieCrew> getFactory() { return factory; }
}

