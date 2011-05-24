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

import databeans.Photo;
import databeans.User;

public class PhotoDAO {
	private BeanFactory<Photo> factory;
	
	public PhotoDAO(UserDAO userDAO) throws DAOException {
		try {
	        // Get a BeanTable to create the "photo" table
	        BeanTable<Photo> photoTable = BeanTable.getInstance(Photo.class,"photo",userDAO.getFactory());
	        
	        if (!photoTable.exists()) photoTable.create("id");
	        
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

	public void create(Photo newPhoto) throws DAOException {
		try {
			Transaction.begin();
			Photo[] oldList = factory.match(MatchArg.equals("owner",newPhoto.getOwner()));
			int maxPos = 0;
			for (Photo p : oldList) {
				if (p.getPosition() > maxPos) maxPos = p.getPosition();
			}
			
			Photo dbPhoto = factory.create();
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
    		Photo p = factory.lookup(id);

    		if (p == null) {
				throw new DAOException("Photo does not exist: id="+id);
    		}

//    		if (!owner.equals(p.getOwner())) {
//				throw new DAOException("Photo not owned by "+owner.getUserName());
//    		}

			factory.delete(id);
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public Photo[] getPhotos(User owner) throws DAOException {
		try {
			Photo[] list = factory.match(MatchArg.equals("owner",owner));
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Photo[] getPhotos(User owner, int movieId) throws DAOException {
		try {
			Photo[] list = factory.match(MatchArg.equals("owner",owner),MatchArg.equals("movieId",movieId));
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Photo[] getPhotos() throws DAOException {
		try {
			Photo[] list = factory.match();
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Photo lookup(int id) throws DAOException {
		try {
			return factory.lookup(id);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}
	
	public Photo[] moveDown(int id, User owner) throws DAOException {
		try {
			Transaction.begin();
			Photo[] list = factory.match(MatchArg.equals("owner",owner));
			Arrays.sort(list);
			
			int index = -1;
			for (int i=0; i<list.length; i++) {
				if (list[i].getId() == id) index = i;
			}
			
			if (index == -1) throw new DAOException("Photo not owned by "+owner.getUserName());
			if (index == list.length-1) throw new DAOException("Photo already at bottom of list");

			swapPositions(list[index],list[index+1]);
			Transaction.commit();
			
			// Resort and return
			Arrays.sort(list);
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public Photo[] moveUp(int id, User owner) throws DAOException {
		try {
			Transaction.begin();
			Photo[] list = factory.match(MatchArg.equals("owner",owner));
			Arrays.sort(list);
			
			int index = -1;
			for (int i=0; i<list.length; i++) {
				if (list[i].getId() == id) index = i;
			}
			
			if (index == -1) throw new DAOException("Photo not owned by "+owner.getUserName());
			if (index == 0) throw new DAOException("Photo already at top of list");

			swapPositions(list[index],list[index-1]);
			Transaction.commit();
			
			// Resort and return
			Arrays.sort(list);
			return list;
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	private void swapPositions(Photo p1, Photo p2) {
		int temp = p1.getPosition();
		p1.setPosition(p2.getPosition());
		p2.setPosition(temp);
	}
}
