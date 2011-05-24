/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package databeans;

public class UserBlog {
	private String userName = null;
	private String blogSubject = null;
	
	public UserBlog(String blogSubject) {
		this.blogSubject = blogSubject;
	} 
	
	public String getBlogSubject() {
		return blogSubject;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
