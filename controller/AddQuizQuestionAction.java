/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.MovieQuizDAO;
import model.PhotoDAO;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.MovieQuiz;
import databeans.Photo;
import databeans.User;
import formbeans.AddQuizQuestionForm;
import formbeans.UploadPhotoForm;

public class AddQuizQuestionAction extends Action {
	private FormBeanFactory<AddQuizQuestionForm> formBeanFactory = FormBeanFactory.getInstance(AddQuizQuestionForm.class);

	private PhotoDAO photoDAO;
	private UserDAO  userDAO;
	private MovieQuizDAO movieQuizDAO;
	
	public AddQuizQuestionAction(Model model) {
    	photoDAO = model.getPhotoDAO();
    	userDAO  = model.getUserDAO();
    	movieQuizDAO = model.getMovieQuizDAO();
	}

	public String getName() { return "add_quiz_question.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			
			HttpSession session = request.getSession(false);
	        User user = (User)session.getAttribute("user");
			//User user = (User) request.getSession(false).getAttribute("user");
		
			
			AddQuizQuestionForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			if (!form.isPresent()) {
	            return "add-quiz-question.jsp";
	        }
			
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0){
	        	return "add-quiz-question.jsp";
	        }
	        
	        MovieQuiz movieQuiz = new MovieQuiz();
	        
	        //Set question attributes
	        movieQuiz.setUserName(user.getUserName());
	        movieQuiz.setQuestion(form.getQuestion());
	        movieQuiz.setAnswer(form.getAnswer());
	       
			movieQuizDAO.create(movieQuiz);

			// Update quiz List (there's now one more on the list)
        	MovieQuiz[] newMovieQuizList = movieQuizDAO.getQuestions();
	        
        	request.setAttribute("message","Success your question has been added");
	        return "success.jsp";
	        
	        
	 	} catch (DAOException e) {
			errors.add(e.getMessage());
			return "add-quiz-question.jsp";
	 	} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "add-quiz-question.jsp";
		}
    }
    
    private String fixBadChars(String s) {
		if (s == null || s.length() == 0) return s;
		
		Pattern p = Pattern.compile("[<>\"&]");
        Matcher m = p.matcher(s);
        StringBuffer b = null;
        while (m.find()) {
            if (b == null) b = new StringBuffer();
            switch (s.charAt(m.start())) {
                case '<':  m.appendReplacement(b,"&lt;");
                           break;
                case '>':  m.appendReplacement(b,"&gt;");
                           break;
                case '&':  m.appendReplacement(b,"&amp;");
                		   break;
                case '"':  m.appendReplacement(b,"&quot;");
                           break;
                default:   m.appendReplacement(b,"&#"+((int)s.charAt(m.start()))+';');
            }
        }
        
        if (b == null) return s;
        m.appendTail(b);
        return b.toString();
    }
}
