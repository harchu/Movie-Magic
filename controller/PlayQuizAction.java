/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.MovieCrewDAO;
import model.MovieQuizDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CurrentSession;
import databeans.MovieQuiz;

import formbeans.PlayQuizForm;

public class PlayQuizAction extends Action{
	
	private FormBeanFactory<PlayQuizForm> formBeanFactory = FormBeanFactory.getInstance(PlayQuizForm.class);
	
	public String getName() { return "playQuiz.do"; }
	private MovieQuizDAO  movieQuizDAO;
	private CurrentSession currentSession;
	
    public PlayQuizAction(Model model) {
    	movieQuizDAO  = model.getMovieQuizDAO();
    	currentSession = model.getSession();
	}
    

    public String perform(HttpServletRequest request) {
    List<String> errors = new ArrayList<String>();
    request.setAttribute("errors",errors);
    try {   
    		request.setAttribute("session", currentSession);
    		
    		// get randomly selected questions from the database 
            MovieQuiz[] questionList = movieQuizDAO.getQuestions();
            MovieQuiz[] quizList = new MovieQuiz[5];
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(questionList.length);
            for(int index=0; index < 5; index++){
            	quizList[index] = questionList[randomInt%questionList.length];
            	randomInt++;
            }
    
            HttpSession session = request.getSession(false);
            
            // pass the questions to the view page to be displayed 
            currentSession.setQuizList(quizList);
            request.setAttribute("session", currentSession);
	        request.setAttribute("form", null);
	       return "play-Quiz.jsp";
        }
     catch (DAOException e) {
    	errors.add(e.getMessage());
    	return "error.jsp";
    } catch (Exception e) {
    	errors.add(e.getMessage());
    	return "error.jsp";
    }
}
}