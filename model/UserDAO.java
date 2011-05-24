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
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databeans.User;


public class UserDAO {
	private BeanFactory<User> factory;
	
	public UserDAO() throws DAOException {
		try {
			// Get a BeanTable so we can create the "user" table
	        BeanTable<User> userTable = BeanTable.getInstance(User.class,"user");
	        
	        if (!userTable.exists()) userTable.create("userName");
	        
	        // Long running web apps need to clean up idle database connections.
	        // So we can tell each BeanTable to clean them up.  (You would only notice
	        // a problem after leaving your web app running for several hours.)
	        userTable.setIdleConnectionCleanup(true);
	
	        // Get a BeanFactory which the actions will use to read and write rows of the "user" table
	        factory = userTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}
	
	public void create(User user) throws DAOException {
        try {
        	Transaction.begin();
			User dbUser = factory.create(user.getUserName());
			factory.copyInto(user,dbUser);
			Transaction.commit();
		} catch (DuplicateKeyException e) {
			throw new DAOException("A user named "+user.getUserName()+" already exists");
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public User lookup(String userName) throws DAOException {
		try {
			return factory.lookup(userName);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	protected BeanFactory<User> getFactory() { return factory; }
	
	public User[] getUsers() throws DAOException {
		try {
			User[] users = factory.match();
			Arrays.sort(users);  // We want them sorted by last and first names (as per User.compareTo());
			return users;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public User[] getUsers(String userName) throws DAOException {
		try {
			User[] users = factory.match(MatchArg.containsIgnoreCase("userName",userName));
			return users;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public void setPassword(String userName, String password) throws DAOException {
        try {
        	Transaction.begin();
			User dbUser = factory.lookup(userName);
			
			if (dbUser == null) {
				throw new DAOException("User "+userName+" no longer exists");
			}
			
			dbUser.setPassword(password);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void setFirstName(String userName, String firstName) throws DAOException {
        try {
        	Transaction.begin();
			User dbUser = factory.lookup(userName);
			
			if (dbUser == null) {
				throw new DAOException("User "+userName+" no longer exists");
			}
			
			dbUser.setFirstName(firstName);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void setLastName(String userName, String lastName) throws DAOException {
        try {
        	Transaction.begin();
			User dbUser = factory.lookup(userName);
			
			if (dbUser == null) {
				throw new DAOException("User "+userName+" no longer exists");
			}
			
			dbUser.setLastName(lastName);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void setSex(String userName, String sex) throws DAOException {
        try {
        	Transaction.begin();
			User dbUser = factory.lookup(userName);
			
			if (dbUser == null) {
				throw new DAOException("User "+userName+" no longer exists");
			}
			
			dbUser.setSex(sex);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void setDateOfBirth(String userName, String dateOfBirth) throws DAOException {
        try {
        	Transaction.begin();
			User dbUser = factory.lookup(userName);
			
			if (dbUser == null) {
				throw new DAOException("User "+userName+" no longer exists");
			}
			
			dbUser.setDateOfBirth(dateOfBirth);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
