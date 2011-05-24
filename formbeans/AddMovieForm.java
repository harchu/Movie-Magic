/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package formbeans;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybeans.form.FormBean;

public class AddMovieForm extends FormBean{
	private String movieName = null;
	private String releaseDate = null;
	private String genre = null;
	private String plotSummary = null;
	private String runTime = null;
	private String language = null;
	private String certification = null;
	
	public String getMovieName() {
		return movieName;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public String getGenre() {
		return genre;
	}
	public String getPlotSummary() {
		return plotSummary;
	}
	public String getRunTime() {
		return runTime;
	}
	public String getLanguage() {
		return language;
	}
	public String getCertification() {
		return certification;
	}
	public void setMovieName(String movieName) {
		this.movieName = trimAndConvert(movieName,"<>\"");
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = trimAndConvert(releaseDate,"<>\"");
	}
	public void setGenre(String genre) {
		this.genre = trimAndConvert(genre,"<>\"");
	}
	public void setPlotSummary(String plotSummary) {
		this.plotSummary = trimAndConvert(plotSummary,"<>\"");
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public void setLanguage(String language) {
		this.language = trimAndConvert(language,"<>\"");
	}
	public void setCertification(String certification) {
		this.certification = trimAndConvert(certification,"<>\"");
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (movieName == null || movieName.length() == 0) {
			errors.add("Movie Name is required");
		}
		
		if (movieName.matches(".*[<>\"].*"))
			errors.add("Movie Name may not contain angle brackets or quotes.");
		
		if (genre == null || genre.length() == 0) {
			errors.add("Genre is required");
		}
		
		if (genre.matches(".*[<>\"].*"))
			errors.add("genre may not contain angle brackets or quotes.");
		
		if (plotSummary == null || plotSummary.length() == 0) {
			errors.add("Plot Summary is required");
		}
		
		if (plotSummary.matches(".*[<>\"].*"))
			errors.add("plotSummary may not contain angle brackets or quotes.");
		
		
		if (language == null || language.length() == 0) {
			errors.add("Language is required");
		}
		
		if (language.matches(".*[<>\"].*"))
			errors.add("language may not contain angle brackets or quotes.");
		
		
		if (certification == null || certification.length() == 0) {
			errors.add("Certification is required");
		}
		
		if (releaseDate == null || releaseDate.toString().length() == 0) {
			errors.add("Release Date is required");
		}
		
		if(runTime == null || runTime.length() == 0)
			errors.add("Run Time should be a positive integer");
		
		if (runTime.matches(".*[<>\"].*"))
			errors.add("runTime may not contain angle brackets or quotes.");
		
		try{
			int runtime = Integer.parseInt(runTime);
		}
		catch (NumberFormatException e){
			errors.add("Run Time should be a positive integer");
		}
			
		
		
		if (releaseDate.matches(".*[<>\"].*"))
			errors.add("releaseDate may not contain angle brackets or quotes.");
		
		//Validate date of birth
		String delims = "[-]";
		String tokens[] = releaseDate.split(delims);
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(releaseDate);
				
		} catch (ParseException e1) {
			errors.add("Invalid input or format of date. Proper date format is mm-dd-yyyy and specify " +
					"proper range values for date, month and year");
		}
		//End of date of birth validation
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
