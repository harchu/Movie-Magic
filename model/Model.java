/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package model;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanTable;

import databeans.CurrentSession;
import databeans.Mailer;

public class Model {
	private PhotoDAO photoDAO;
	private UserDAO  userDAO;
	private MovieDAO movieDAO;
	private MovieCrewDAO movieCrewDAO;
	private BlogDAO blogDAO;
	private MovieQuizDAO movieQuizDAO;
	private UserBlogDAO userBlogDAO;
	private UserMovieDAO userMovieDAO;
	private TrailerDAO trailerDAO;
	private WallDAO wallDAO;
	private CurrentSession currentSession;
	private Mailer  mailer;
	private boolean    requireSSL;


	public Model(ServletConfig config) throws ServletException {
		try {
			System.out.println("yoyo");
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			requireSSL = new Boolean(config.getInitParameter("require-ssl"));
			boolean validIdCheck = new Boolean(config.getInitParameter("valid-andrew-id-check"));
	        boolean sendMail = new Boolean(config.getInitParameter("send-mail"));
	        String smtpHost = config.getInitParameter("smtp-host");
	        String fromAddress = config.getInitParameter("from-address");
	        
	        mailer = null;
	        if (sendMail) mailer = new Mailer(smtpHost,fromAddress);
			
			BeanTable.useJDBC(jdbcDriver,jdbcURL);
			userDAO  = new UserDAO();
			photoDAO = new PhotoDAO(userDAO);
			movieDAO = new MovieDAO(userDAO);
			movieCrewDAO = new MovieCrewDAO(movieDAO);
			trailerDAO = new TrailerDAO(userDAO);
			movieQuizDAO = new MovieQuizDAO(userDAO);
			userBlogDAO = new UserBlogDAO(userDAO);
			blogDAO = new BlogDAO(userBlogDAO);
			userMovieDAO = new UserMovieDAO(movieDAO,userDAO);
			wallDAO = new WallDAO();
			currentSession = new CurrentSession();
			
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public PhotoDAO getPhotoDAO() { return photoDAO; }
	public UserDAO  getUserDAO()  { return userDAO;  }
	public MovieDAO  getMovieDAO()  { return movieDAO;  }
	public MovieCrewDAO  getMovieCrewDAO()  { return movieCrewDAO;  }
	public MovieQuizDAO  getMovieQuizDAO()  { return movieQuizDAO;  }
	public UserBlogDAO  getUserBlogDAO()  { return userBlogDAO;  }
	public BlogDAO  getBlogDAO()  { return blogDAO;  }
	public UserMovieDAO  getUserMovieDAO()  { return userMovieDAO;  }
	public TrailerDAO  getTrailerDAO()  { return trailerDAO;  }
	public WallDAO getWallDAO() { return wallDAO; }
	public CurrentSession getSession() { return currentSession; }
	public Mailer     getMailer()     { return mailer;     }

}
