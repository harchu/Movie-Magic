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

public class SearchMovieForm extends FormBean{
	private String option;
	private String searchByName;
	
	public String getOption() {
		return option;
	}
	public String getSearchByName() {
		return searchByName;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public void setSearchByName(String s) {
		this.searchByName = trimAndConvert(s,"<>\"");
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (option == null || option.length() == 0) {
			errors.add("Option is required");
		}
		if (searchByName == null || searchByName.length() == 0) {
			errors.add("Name is required");
		}
		
		if (searchByName.matches(".*[<>\"].*"))
			errors.add("searchByName may not contain angle brackets or quotes.");
		
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