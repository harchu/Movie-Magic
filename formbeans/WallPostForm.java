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

public class WallPostForm extends FormBean {
	private String post;
	private String toName;
	private String button;

	public String getPost()  { return post; }
	

	public void setPost(String s) {	post = trimAndConvert(s,"<>\"");  }
	
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = trimAndConvert(button,"<>>\"]");
	}


	public String getToName() {
		return toName;
	}


	public void setToName(String toName) {
		this.toName = trimAndConvert(toName,"<>>\"]");
	}

	

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (post == null || post.length() == 0) {
			errors.add("Post is required");
		}
		
		if (post.matches(".*[<>\"].*")){			
			errors.add("post may not contain angle brackets or quotes.");
		}
		
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
