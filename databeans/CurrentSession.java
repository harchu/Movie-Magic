/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package databeans;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrentSession {
	private Movie currentMovie;
	private Photo currentPhoto;
	private Trailer currentTrailer;
	private float avgRating;
	private String pageOwner;
	private Wall[] userPosts;
	private HashMap<String,String> movieReview;
	public HashMap<String,String> getMovieReview() {
		return movieReview;
	}

	public void setMovieReview(HashMap<String,String> movieReview) {
		this.movieReview = movieReview;
	}

	public String getPageOwner() {
		return pageOwner;
	}

	public void setPageOwner(String pageOwner) {
		this.pageOwner = pageOwner;
	}

	public Wall[] getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(Wall[] userPosts) {
		this.userPosts = userPosts;
	}

	private ArrayList<String> currentActors;
	private ArrayList<String> currentActresses;
	private ArrayList<String> currentDirectors;
	private ArrayList<String> currentProducers;
	private String blogSubject;
	private Blog[] blogList;
	private UserBlog[] userBlogList;
	public UserBlog[] getUserBlogList() {
		return userBlogList;
	}

	public void setUserBlogList(UserBlog[] userBlogList) {
		this.userBlogList = userBlogList;
	}
	
	private MovieQuiz[] quizList;
	public MovieQuiz[] getQuizList() {
		return quizList;
	}

	public void setQuizList(MovieQuiz[] quizList) {
		this.quizList = quizList;
	}

	private UserMovie[] user_reviews;
	private Photo[] user_photos;
	private Trailer[] user_trailer;
	
	private ArrayList<Integer> idList;	
	private ArrayList<String> nameList;
	private ArrayList<String> movieList;
	
	public CurrentSession(){
	}
	
	public ArrayList<Integer> getIdList() {
		return idList;
	}

	public void setIdList(ArrayList<Integer> idList) {
		this.idList = idList;
	}

	public ArrayList<String> getNameList() {
		return nameList;
	}

	public void setNameList(ArrayList<String> nameList) {
		this.nameList = nameList;
	}

	public ArrayList<String> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<String> movieList) {
		this.movieList = movieList;
	}
	
	public Trailer getCurrentTrailer() {
		return currentTrailer;
	}

	public void setCurrentTrailer(Trailer currentTrailer) {
		this.currentTrailer = currentTrailer;
	}

	public float getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}
	public Movie getCurrentMovie() {
		return currentMovie;
	}
	public void setCurrentMovie(Movie currentMovie) {
		this.currentMovie = currentMovie;
	}
	public Photo getCurrentPhoto() {
		return currentPhoto;
	}
	public void setCurrentPhoto(Photo currentPhoto) {
		this.currentPhoto = currentPhoto;
	}
	public ArrayList<String> getCurrentActors() {
		return currentActors;
	}
	public void setCurrentActors(ArrayList<String> currentActors) {
		this.currentActors = currentActors;
	}
	public ArrayList<String> getCurrentActresses() {
		return currentActresses;
	}
	public void setCurrentActresses(ArrayList<String> currentActresses) {
		this.currentActresses = currentActresses;
	}
	public ArrayList<String> getCurrentDirectors() {
		return currentDirectors;
	}
	public void setCurrentDirectors(ArrayList<String> currentDirectors) {
		this.currentDirectors = currentDirectors;
	}
	public ArrayList<String> getCurrentProducers() {
		return currentProducers;
	}
	public void setCurrentProducers(ArrayList<String> currentProducers) {
		this.currentProducers = currentProducers;
	}
	public String getBlogSubject() {
		return blogSubject;
	}
	public void setBlogSubject(String blogSubject) {
		this.blogSubject = blogSubject;
	}
	public Blog[] getBlogList() {
		return blogList;
	}
	public void setBlogList(Blog[] blogList) {
		this.blogList = blogList;
	}
	public UserMovie[] getUser_reviews() {
		return user_reviews;
	}
	public void setUser_reviews(UserMovie[] user_reviews) {
		this.user_reviews = user_reviews;
	}
	public Photo[] getUser_photos() {
		return user_photos;
	}
	public void setUser_photos(Photo[] user_photos) {
		this.user_photos = user_photos;
	}
	public Trailer[] getUser_trailer() {
		return user_trailer;
	}
	public void setUser_trailer(Trailer[] user_trailer) {
		this.user_trailer = user_trailer;
	}
	
	
}
