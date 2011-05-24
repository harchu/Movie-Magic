/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package databeans;

public class UserMovie {

	private String userName;
	private int movieId;
	private int userRating;
	private String userReview;
	
	public UserMovie(int movieId,String userName){
		this.userName = userName;
		this.movieId = movieId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getMovieId() {
		return movieId;
	}
	
	public int getUserRating() {
		return userRating;
	}
	public void setUserRating(int userRating) {
		this.userRating = userRating;
	}
	public String getUserReview() {
		return userReview;
	}
	public void setUserReview(String userReview) {
		this.userReview = userReview;
	}
	
	
}
