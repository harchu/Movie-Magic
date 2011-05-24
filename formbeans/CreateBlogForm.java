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

public class CreateBlogForm extends FormBean {
	private String blogSubject;
	private String blogPost;
	
	public String getBlogSubject()  { return blogSubject; }
	public String getBlogPost()  { return blogPost; }

	public void setBlogSubject(String s) {	blogSubject = trimAndConvert(s,"<>\""); }
	public void setBlogPost(String s) {	blogPost = trimAndConvert(s,"<>\"");                  }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (blogSubject == null || blogSubject.length() == 0) {
			errors.add("Subject is required");
		}
		
		if (blogSubject.matches(".*[<>\"].*"))
			errors.add("blogSubject may not contain angle brackets or quotes.");
		
		if (blogPost == null || blogPost.length() == 0) {
			errors.add("Post is required");
		}
		
		if (blogPost.matches(".*[<>\"].*"))
			errors.add("blogPost may not contain angle brackets or quotes.");
		
		
		
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
