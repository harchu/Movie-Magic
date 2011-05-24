/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.MovieQuizDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CurrentSession;
import databeans.MovieQuiz;
import databeans.User;

import formbeans.AddQuizQuestionForm;
import formbeans.LoginForm;
import formbeans.PlayQuizForm;

public class EvaluateQuizAction extends Action {

private FormBeanFactory<PlayQuizForm> formBeanFactory = FormBeanFactory.getInstance(PlayQuizForm.class);
	
	private MovieQuizDAO  movieQuizDAO;
	 Integer count = -1;
	 private CurrentSession currentSession;
	 

	public EvaluateQuizAction(Model model) {
	   	movieQuizDAO  = model.getMovieQuizDAO();
	   	currentSession = model.getSession();
	}
	 
	public String getName() { return "evaluateQuiz.do"; }
   
    public String perform(HttpServletRequest request) {
    	
    List<String> errors = new ArrayList<String>();
    request.setAttribute("errors",errors);
    try {
        // Set up user list for nav bar
    	HttpSession session = request.getSession(false);
    	MovieQuiz[] questionList = currentSession.getQuizList();
		PlayQuizForm form = formBeanFactory.create(request);
		request.setAttribute("form",form);
       
		if (!form.isPresent()) {
            return "play-Quiz.jsp";
        }
       String[] ans = new String[5];
       ans[0] = form.getAnswer1();
       ans[1] = form.getAnswer2();
       ans[2] = form.getAnswer3();
       ans[3] = form.getAnswer4();
       ans[4] = form.getAnswer5();
        count = 0;
        if(form.getAnswer1().equalsIgnoreCase(questionList[0].getAnswer()))
        	count = count + 1;
        if(form.getAnswer2().equalsIgnoreCase(questionList[1].getAnswer()))
        	count = count + 1;
        if(form.getAnswer3().equalsIgnoreCase(questionList[2].getAnswer()))
        	count = count + 1;
        if(form.getAnswer4().equalsIgnoreCase(questionList[3].getAnswer()))
        	count = count + 1;
        if(form.getAnswer5().equalsIgnoreCase(questionList[4].getAnswer()))
        	count = count + 1;
        request.setAttribute("count", count);
        request.setAttribute("session", currentSession);
        request.setAttribute("ans",ans);
        return "quiz-results.jsp";
    }catch (FormBeanException e) {
    	errors.add(e.getMessage());
    	return "error.jsp";
    }
    }
}
