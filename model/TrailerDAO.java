/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databeans.Trailer;
import databeans.User;

public class TrailerDAO {
	private BeanFactory<Trailer> factory;
	
	public TrailerDAO(UserDAO userDAO) throws DAOException {
		try {
	        // Get a BeanTable to create the "photo" table
	        BeanTable<Trailer> photoTable = BeanTable.getInstance(Trailer.class,"trailer",userDAO.getFactory());
	        
	        if (!photoTable.exists()) photoTable.create("caption");
	        
	        // Long running web apps need to clean up idle database connections.
	        // So we can tell each BeanTable to clean them up.  (You would only notice
	        // a problem after leaving your web app running for several hours.)
	        photoTable.setIdleConnectionCleanup(true);
	
	        // Get a BeanFactory which the actions will use to read and write
	        // rows of the "photo" table
	        factory = photoTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}

	public void create(Trailer newPhoto) throws DAOException {
		try {
			Transaction.begin();
			Trailer[] oldList = factory.match(MatchArg.equals("owner",newPhoto.getOwner()));
			int maxPos = 0;
			for (Trailer p : oldList) {
				if (p.getPosition() > maxPos) maxPos = p.getPosition();
			}
			
			Trailer dbPhoto = factory.create(newPhoto.getCaption());
			factory.copyInto(newPhoto,dbPhoto);
			dbPhoto.setPosition(maxPos+1);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public void delete(int id, User owner) throws DAOException {
		try {
			Transaction.begin();
			Trailer p = factory.lookup(id);

    		if (p == null) {
				throw new DAOException("Trailer does not exist: id="+id);
    		}

    		if (!owner.equals(p.getOwner())) {
				throw new DAOException("Trailer not owned by "+owner.getUserName());
    		}

			factory.delete(id);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public Trailer[] getTrailers(User owner) throws DAOException {
		try {
			Trailer[] list = factory.match(MatchArg.equals("owner",owner));
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Trailer[] getTrailers(User owner, int movieId) throws DAOException {
		try {
			Trailer[] list = factory.match(MatchArg.equals("owner",owner),MatchArg.equals("movieId",movieId));
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Trailer[] getTrailers() throws DAOException {
		try {
			Trailer[] list = factory.match();
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public int getNewId() throws DAOException {
		try {
			Trailer[] list = factory.match();
			ArrayList<Integer> idList = new ArrayList<Integer>();
			int max=0;
			for(Trailer entry : list){
				if (entry.getId()>max)
					max = entry.getId();
			}
			return (max+1);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Trailer lookup(String id) throws DAOException {
		try {
			return factory.lookup(id);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
}
