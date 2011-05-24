/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBean;

import databeans.Movie;

public class UploadTrailerForm extends FormBean {
	private String button         = "";
	private String caption    = "";
	private FileProperty file     = null;
	
	public static int FILE_MAX_LENGTH = 1024 * 1024;
	
	public String       getButton()         { return button;         }
	public FileProperty getFile()           { return file;           }
	public String       getCaption()        { return caption;        }

	public void setButton(String s)         { button      = s;        }
	public void setCaption(String s)        { caption     = trimAndConvert(s,"<>\""); }
	public void setFile(FileProperty file)  { this.file   = file;     }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (caption == null || caption.length() == 0) {
			errors.add("Caption not present");
		}
		
		if (caption.matches(".*[<>\"].*"))
			errors.add("caption may not contain angle brackets or quotes.");
		
		if (file == null) {
			errors.add("File not provided");
			return errors;
		}
		
		if (file.getBytes() == null || file.getBytes().length == 0) {
			errors.add("No file data was received");
		}
		
		if (file.getContentType() == null || file.getContentType().length() == 0) {
			errors.add("No content type was sent by browser!  (Shouldn't happen)");
		}

		if (file.getFileName() == null || file.getFileName().length() == 0) {
			errors.add("No filename!");
			return errors;
		}
		
		if (file.getFileName().matches("[<>\"]")) {
			errors.add("File name may not contain angle brackets or quotes");
		}
		
		int lastDot = file.getFileName().lastIndexOf('.');
		if (lastDot == -1) {
			errors.add("No extension on file name.  Must be one of: "+Movie.EXTENSIONS);
			return errors;
		}
		
//		String extension = file.getFileName().substring(lastDot);
//		if (!Movie.EXTENSIONS.contains(extension)) {
//			errors.add("Invalid extension on file name.  Must be one of: "+Movie.EXTENSIONS);
//		}

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
