/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package databeans;

public class Wall {
	
	private String toUser;
	private String fromSender;
	private String post = null;
	private int wallId = -1;
	

	public String getToUser() {
		return toUser;
	}


	public void setToUser(String toUser) {
		this.toUser = toUser;
	}


	public String getFromSender() {
		return fromSender;
	}


	public void setFromSender(String fromSender) {
		this.fromSender = fromSender;
	}


	public String getPost() {
		return post;
	}


	public void setPost(String post) {
		this.post = post;
	}


	public int getWallId() {
		return wallId;
	}


	public void setWallId(int wallId) {
		this.wallId = wallId;
	}
	public Wall() {
		
	}

	
}
