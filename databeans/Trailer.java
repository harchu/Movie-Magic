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

public class Trailer implements Comparable<Trailer> {

	private int id=0;
	private int movieId     = -1;
	private String fileName = null;
	private String caption     = null;
	private String contentType = null;
	private User   owner       = null;
	private int    position    = 0;
	
	public Trailer(String caption){
		this.caption = caption;
	}
	
	public int compareTo(Trailer other) {
		// Order first by owner, then by position
		if (owner == null && other.owner != null) return -1;
		if (owner != null && other.owner == null) return 1;
		int c = owner.compareTo(other.owner);
		if (c != 0) return c;
		return position - other.position;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Trailer) {
			Trailer other = (Trailer) obj;
			return compareTo(other) == 0;
		}
		return false;
	}
	
    public String getCaption()     { return caption;     }
    public String getContentType() { return contentType; }
    public String getFileName()     { return fileName;     }
    public User   getOwner()       { return owner;       }
    public int    getPosition()    { return position;    }
    public int    getMovieId()    { return movieId;    }
    public int    getId()    { return id;    }
    
    public void setCaption(String s)     { caption = s;     }
    public void setContentType(String s) { contentType = s; }
    public void setFileName(String s)     { fileName = s;     }
    public void setOwner(User u)         { owner = u;       }
    public void setPosition(int p)       { position = p;    }
    public void setMovieId(int m)    {  movieId = m;    }
    public void setId(int m)    {  id = m;    }
    public String toString() {
    	return "Photo("+caption+")";
    }
}