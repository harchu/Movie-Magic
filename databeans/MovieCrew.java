/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package databeans;

public class MovieCrew {
	
	private int movieId;
	private int castId = 0;
	private String role = null;
	private String name = null;
	private int    position    = 0;
	 
	public MovieCrew(){
	}
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getCastId() {
		return castId;
	}
	public void setCastId(int castId) {
		this.castId = castId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	

}
