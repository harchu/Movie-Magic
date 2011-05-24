/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package databeans;

import java.sql.Time;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Movie{
	public static final List<String> EXTENSIONS = Collections.unmodifiableList(Arrays.asList( new String[] {
			".wmv", ".avi"
	} ));
	private int  movieId = 0;
	

	private String 	movieName 	= null;
	private float  	rating 		= 0;
	private String    releaseDate   ;
	private String  genre      	= null;
	private String  plotSummary = null;
	private int    runningTime	= 0;
	private String  language	= null;
	private String  certification	= null;
	
	
	private int    position    = 0;
	
//	public int compareTo(Movie other) {
//		// Order first by owner, then by position
//		if (owner == null && other.owner != null) return -1;
//		if (owner != null && other.owner == null) return 1;
//		int c = owner.compareTo(other.owner);
//		if (c != 0) return c;
//		return position - other.position;
//	}
	
//	public boolean equals(Object obj) {
//		if (obj instanceof Movie) {
//			Movie other = (Movie) obj;
//			return compareTo(other) == 0;
//		}
//		return false;
//	}
	
	
	public Movie() {
	}

	public int getMovieId() {
		return movieId;
	}


	public String getMovieName() {
		return movieName;
	}


	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	public float getRating() {
		return rating;
	}


	public void setRating(float rating) {
		this.rating = rating;
	}


	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getPlotSummary() {
		return plotSummary;
	}


	public void setPlotSummary(String plotSummary) {
		this.plotSummary = plotSummary;
	}


	public int getRunningTime() {
		return runningTime;
	}


	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getCertification() {
		return certification;
	}


	public void setCertification(String certification) {
		this.certification = certification;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	
}
	
