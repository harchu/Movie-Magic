/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package databeans;

public class Blog {
	
	private String blogSubject = null;
	private String userName = null;
	private String blogPost = null;
	private int postId = -1;
	

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public Blog() {
	}
	
	public int getPostId() {
		return postId;
	}
	
	public String getBlogSubject() {
		return blogSubject;
	}
	
	public void setBlogSubject(String blogSubject) {
		this.blogSubject = blogSubject;
	}
	

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getBlogPost() {
		return blogPost;
	}
	public void setBlogPost(String blogPost) {
		this.blogPost = blogPost;
	}

	
	
	

}
