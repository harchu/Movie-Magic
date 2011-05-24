/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package databeans;

public class MovieQuiz {
	private String userName = null;
	
	private int questionNumber = 0;
	
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	private String question = null;
	private String answer = null;
	
	public MovieQuiz() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
}
