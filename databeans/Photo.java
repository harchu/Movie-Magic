/*
 * Copyrights © 2011 by Rohit Harchandani and Risha Chheda
 *
 * Please refer to root level license.txt file 
 * for entire license. 
 */
package databeans;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Photo implements Comparable<Photo> {
	public static final List<String> EXTENSIONS = Collections.unmodifiableList(Arrays.asList( new String[] {
			".jpg", ".gif", ".JPG"
	} ));

	private int    id          = -1;
	private int movieId     = -1;
	private byte[] bytes       = null;
	private String caption     = null;
	private String contentType = null;
	private User   owner       = null;
	private int    position    = 0;
	
	public int compareTo(Photo other) {
		// Order first by owner, then by position
		if (owner == null && other.owner != null) return -1;
		if (owner != null && other.owner == null) return 1;
		int c = owner.compareTo(other.owner);
		if (c != 0) return c;
		return position - other.position;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Photo) {
			Photo other = (Photo) obj;
			return compareTo(other) == 0;
		}
		return false;
	}
	
    public byte[] getBytes()       { return bytes;       }
    public String getCaption()     { return caption;     }
    public String getContentType() { return contentType; }
    public int    getId()          { return id;          }
    public User   getOwner()       { return owner;       }
    public int    getPosition()    { return position;    }
    public int    getMovieId()    { return movieId;    }
    
    public void setBytes(byte[] a)       { bytes = a;       }
    public void setCaption(String s)     { caption = s;     }
    public void setContentType(String s) { contentType = s; }
    public void setId(int x)             { id = x;          }
    public void setOwner(User u)         { owner = u;       }
    public void setPosition(int p)       { position = p;    }
    public void setMovieId(int m)    {  movieId = m;    }
    
    public String toString() {
    	return "Photo("+id+")";
    }
}
