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
import databeans.User;
import databeans.MovieQuiz;


public class MovieQuizDAO {
	private BeanFactory<MovieQuiz> factory;
	
	public MovieQuizDAO(UserDAO userDAO) throws DAOException {
		try {
			// Get a BeanTable so we can create the "user" table
	        BeanTable<MovieQuiz> movieQuizTable = BeanTable.getInstance(MovieQuiz.class,"movie_quiz", userDAO.getFactory());
	        
	        if (!movieQuizTable.exists()) movieQuizTable.create("questionNumber");
	        
	        // Long running web apps need to clean up idle database connections.
	        // So we can tell each BeanTable to clean them up.  (You would only notice
	        // a problem after leaving your web app running for several hours.)
	        movieQuizTable.setIdleConnectionCleanup(true);
	
	        // Get a BeanFactory which the actions will use to read and write rows of the "user" table
	        factory = movieQuizTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	public void create(MovieQuiz newQuestion) throws DAOException {
		try {
			Transaction.begin();
			
			MovieQuiz dbQuestion = factory.create();
			factory.copyInto(newQuestion,dbQuestion);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	
	protected BeanFactory<MovieQuiz> getFactory() { return factory; }
	
	public MovieQuiz[] getQuestions() throws DAOException {
		try {
			MovieQuiz[] users = factory.match();
			return users;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
}