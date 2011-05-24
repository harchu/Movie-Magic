/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class MovieCrewForm extends FormBean{

	private int movieId;
	private String actor1 = "";
	private String actor2 = "";
	private String actor3 = "";
	private String actress1 = "";
	private String actress2 = "";
	private String actress3 = "";
	private String director1 = "";
	private String director2 = "";
	private String producer1 = "";
	private String producer2 = "";
	
	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public String getActor1() {
		return actor1;
	}

	public void setActor1(String actor1) {
		this.actor1 = trimAndConvert(actor1,"<>\"");
	}

	public String getActor2() {
		return actor2;
	}

	public void setActor2(String actor2) {
		this.actor2 = trimAndConvert(actor2,"<>\"");
	}

	public String getActor3() {
		return actor3;
	}

	public void setActor3(String actor3) {
		this.actor3 = trimAndConvert(actor3,"<>\"");
	}

	public String getActress1() {
		return actress1;
	}

	public void setActress1(String actress1) {
		this.actress1 = trimAndConvert(actress1,"<>\"");
	}

	public String getActress2() {
		return actress2;
	}

	public void setActress2(String actress2) {
		this.actress2 = trimAndConvert(actress2,"<>\"");
	}

	public String getActress3() {
		return actress3;
	}

	public void setActress3(String actress3) {
		this.actress3 = trimAndConvert(actress3,"<>\"");
	}

	public String getDirector1() {
		return director1;
	}

	public void setDirector1(String director1) {
		this.director1 = trimAndConvert(director1,"<>\"");
	}

	public String getDirector2() {
		return director2;
	}

	public void setDirector2(String director2) {
		this.director2 = trimAndConvert(director2,"<>\"");
	}

	public String getProducer1() {
		return producer1;
	}

	public void setProducer1(String producer1) {
		this.producer1 = trimAndConvert(producer1,"<>\"");
	}

	public String getProducer2() {
		return producer2;
	}

	public void setProducer2(String producer2) {
		this.producer2 = trimAndConvert(producer2,"<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if ((actor1 == null || actor1.length() == 0) && (actor2 == null || actor2.length() == 0) && (actor3 == null || actor3.length() == 0)) {
			errors.add("Actor Name is required");
		}
		
		if (actor1.matches(".*[<>\"].*"))
			errors.add("actor1 may not contain angle brackets or quotes.");
		
		if (actor2.matches(".*[<>\"].*"))
			errors.add("actor2 may not contain angle brackets or quotes.");
		
		if (actor3.matches(".*[<>\"].*"))
			errors.add("actor3 may not contain angle brackets or quotes.");
		
		if ((actress1 == null || actress1.length() == 0) && (actress2 == null || actress2.length() == 0) && (actress3 == null || actress3.length() == 0)) {
			errors.add("Actress Name is required");
		}
		
		if (actress1.matches(".*[<>\"].*"))
			errors.add("actress1 may not contain angle brackets or quotes.");
		
		if (actress2.matches(".*[<>\"].*"))
			errors.add("actress2 may not contain angle brackets or quotes.");
		
		if (actress3.matches(".*[<>\"].*"))
			errors.add("actress3 may not contain angle brackets or quotes.");
		
		if ((producer1==null || producer1.length() == 0) && (producer2==null || producer2.length() == 0)) {
			errors.add("Producer Name is required");
		}
		
		if (producer1.matches(".*[<>\"].*"))
			errors.add("producer1 may not contain angle brackets or quotes.");
		
		if (producer2.matches(".*[<>\"].*"))
			errors.add("producer2 may not contain angle brackets or quotes.");
		
		if ((director1==null || director1.length() == 0) && (director2==null || director2.length() == 0)) {
			errors.add("Director Name is required");
		}
		
		if (director1.matches(".*[<>\"].*"))
			errors.add("director1 may not contain angle brackets or quotes.");
		
		if (director2.matches(".*[<>\"].*"))
			errors.add("director2 may not contain angle brackets or quotes.");
		
		return errors;
	}
	
	private String trimAndConvert(String s, String charsToConvert) {
		if (!s.matches("["+charsToConvert+"]")) {
			return s.trim();
		}
		
		StringBuffer b = new StringBuffer();
		for (char c : s.trim().toCharArray()) {
			switch (c) {
				case '<':
					if (charsToConvert.indexOf('<') != -1) {
						b.append("&lt;");
					} else {
						b.append(c);
					}
					break;
				case '>':
					if (charsToConvert.indexOf('>') != -1) {
						b.append("&gt;");
					} else {
						b.append(c);
					}
					break;
				case '&':
					if (charsToConvert.indexOf('&') != -1) {
						b.append("&amp;");
					} else {
						b.append(c);
					}
					break;
				case '"':
					if (charsToConvert.indexOf('"') != -1) {
						b.append("&quot;");
					} else {
						b.append(c);
					}
					break;
				default:
					if (charsToConvert.indexOf(c) != -1) {
						b.append("&#"+c+";");
					} else {
						b.append(c);
					}
			}
		}
		
		return b.toString();
	}
	
}
