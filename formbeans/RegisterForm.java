/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package formbeans;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean {
	private String firstName = null;
	private String lastName = null;
	private String userName = null;
	private String password = null;
	private String confirm  = null;
	private String dateOfBirth = null;
	private String email = null;
	private String sex = null;
	
	public String getFirstName() { return firstName; }
	public String getLastName()  { return lastName;  }
	
	public String getUserName()  { return userName;  }
	public String getPassword()  { return password;  }
	public String getConfirm()   { return confirm;   }
	public String getDateOfBirth()   { return dateOfBirth;   }
	public String getSex()   { return sex;   }
	
	public void setFirstName(String s) { firstName = trimAndConvert(s,"<>\"");  }
	public void setLastName(String s)  { lastName  = trimAndConvert(s,"<>\"");  }
	public void setUserName(String s)  { userName  = trimAndConvert(s,"<>\"");  }
	public void setPassword(String s)  { password  = trimAndConvert(s,"<>\"");                  }
	public void setConfirm(String s)   { confirm   = trimAndConvert(s,"<>\"");                  }
	public void setDateOfBirth(String s)   { System.out.println("dobd: "+s);dateOfBirth   =  trimAndConvert(s,"<>\"");  }
	public void setSex(String s)   { sex   = trimAndConvert(s,"<>\"");   }
	public String getEmail() {return email; }
	public void setEmail(String email) { this.email = trimAndConvert(email,"<>\"");; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		

		if (firstName == null || firstName.length() == 0) {
			errors.add("First Name is required");
		}
		
		if (firstName.matches(".*[<>\"].*"))
			errors.add("First Name may not contain angle brackets or quotes.");

		if (lastName == null || lastName.length() == 0) {
			errors.add("Last Name is required");
		}
		
		if (lastName.matches(".*[<>\"].*"))
			errors.add("Last Name may not contain angle brackets or quotes.");

		if (userName == null || userName.length() == 0) {
			errors.add("User Name is required");
		}
		
		if (userName.matches(".*[<>\"].*"))
			errors.add("User Name may not contain angle brackets or quotes.");

		if (sex == null || sex.length() == 0) {
			errors.add("Please select appropriate gender");
		}
		if (email == null || email.length() == 0) {
			errors.add("Email Address is required");
		}
		else if(email!=null){
			if(!(email.contains("@")))
			errors.add("Please put proper email address");
			else if( email.startsWith("@"))
			errors.add("Please put proper email id ");
			else if(email.endsWith("@"))
			errors.add("Please put proper hostname in email address");
			else if (email.matches(".*[<>\"].*"))
			errors.add("Email Address may not contain angle brackets or quotes.");
		}
		if (dateOfBirth == null || dateOfBirth.length() == 0) {
			errors.add("Date of Birth is required");
		}
	
		if (dateOfBirth.matches(".*[<>\"].*"))
			errors.add("Date of Birth may not contain angle brackets or quotes.");
		
		//Validate date of birth
		String delims = "[-]";
		String tokens[] = dateOfBirth.split(delims);
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(dateOfBirth);
				
		} catch (ParseException e1) {
			errors.add("Invalid input or format of date. Proper date format is mm-dd-yyyy and specify " +
					"in range values for date, month and year");
		}
		
		/*
			try{
				if((Integer.parseInt(tokens[0]) < 1) || (Integer.parseInt(tokens[0]) > 12))
					errors.add("Invalid input for month. Month should be between 1 and 12");
				if((Integer.parseInt(tokens[1]) < 1) || (Integer.parseInt(tokens[1]) > 31))
					errors.add("Invalid input for day. Month should be between 1 and 31");
			}
			catch (NumberFormatException e){
				errors.add("Date should contain only numbers");
			}*/
		
		
		
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}
		
		if (password.matches(".*[<>\"].*"))
			errors.add("Password may not contain angle brackets or quotes.");

		if (confirm == null || confirm.length() == 0) {
			errors.add("Please re-enter the password");
		}
		
		if (confirm.matches(".*[<>\"].*"))
			errors.add("Confirm Password may not contain angle brackets or quotes.");
		
		
		if (!password.equals(confirm)) {
			errors.add("Passwords are not the same");
		}
		if (errors.size() > 0) {
			return errors;
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
