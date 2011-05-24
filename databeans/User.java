/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package databeans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

public class User implements Comparable<User> {
	private String  userName = null;
	
	private String	dateOfBirth	   = null;

	private String  hashedPassword = "*";
	private int     salt           = 0;

	private String  firstName      = null;
	private String  lastName       = null;

	private String  sex			   = null;
	private String  email		   = null;
	
	
	public User(String userName) {
		this.userName = userName;
	}

	public boolean checkPassword(String password) {
		return hashedPassword.equals(hash(password));
	}
	
	public int compareTo(User other) {
		// Order first by lastName and then by firstName
		int c = lastName.compareTo(other.lastName);
		if (c != 0) return c;
		c = firstName.compareTo(other.firstName);
		if (c != 0) return c;
		return userName.compareTo(other.userName);
	}

	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User other = (User) obj;
			return userName.equals(other.userName);
		}
		return false;
	}

	public String  getHashedPassword() { return hashedPassword; }
	public String  getUserName()       { return userName;       }
	public int     getSalt()           { return salt;           }

	public String  getFirstName()      { return firstName;      }
	public String  getLastName()       { return lastName;       }
	
	public String    getDateOfBirth()    { return dateOfBirth;	}
	public String  getSex()       	   { return sex;       		}
	public String  getEmail()          { return email;       	}

	public int     hashCode()          { return userName.hashCode(); }

	public void setHashedPassword(String x) { hashedPassword = x; }
	public void setPassword(String s)       { salt = newSalt(); hashedPassword = hash(s); }
	public void setSalt(int x)              { salt = x;           }

	public void setFirstName(String s)      { firstName = s;      }
	public void setLastName(String s)       { lastName = s;       }
	
	public void setSex(String s)       { sex = s;       }
	public void setEmail(String s)       { email = s;       }
	public void setDateOfBirth(String d)       { dateOfBirth = d;       }

	public String toString() {
		return "User("+getUserName()+")";
	}

	private String hash(String clearPassword) {
		if (salt == 0) return null;

		MessageDigest md = null;
		try {
		  md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
		  throw new AssertionError("Can't find the SHA1 algorithm in the java.security package");
		}

		String saltString = String.valueOf(salt);
		
		md.update(saltString.getBytes());
		md.update(clearPassword.getBytes());
		byte[] digestBytes = md.digest();

		// Format the digest as a String
		StringBuffer digestSB = new StringBuffer();
		for (int i=0; i<digestBytes.length; i++) {
		  int lowNibble = digestBytes[i] & 0x0f;
		  int highNibble = (digestBytes[i]>>4) & 0x0f;
		  digestSB.append(Integer.toHexString(highNibble));
		  digestSB.append(Integer.toHexString(lowNibble));
		}
		String digestStr = digestSB.toString();

		return digestStr;
	}

	private int newSalt() {
		Random random = new Random();
		return random.nextInt(8192)+1;  // salt cannot be zero
	}
}
