/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

public class RSSFeedsAction {
	
	protected static void processRequest(HttpServletRequest request, HttpServletResponse response, HttpServlet s)
	    throws ServletException, IOException {
	        PrintWriter out = response.getWriter();
	        
	        String urlAsString=null;
	        try{
	        	// get the xsl stored in this project
	            ServletContext context = s.getServletContext();
	            InputStream xsl = (InputStream)
	                        (context.getResourceAsStream("/a.xsl"));

	            /**
	             * Check the category and news source selected by the user and based
	             * on his choice use the appropriate xml url to be converted into
	             * HTML
	             */
	            //urlAsString = "http://feeds.nytimes.com/nyt/rss/" + category;
	            urlAsString = "http://www.fandango.com/rss/top10boxoffice.rss";

	            // We need two source objects and one result
	            // get an external xml document using a url in a
	            // string format
	            Source xmlDoc =  new StreamSource(urlAsString);
	            Source xslDoc =  new StreamSource(xsl);
	            Result result =  new StreamResult(out);

	            // Prepare to transform
	            TransformerFactory factory = TransformerFactory.newInstance();
	            Transformer trans = factory.newTransformer(xslDoc);
	            trans.transform(xmlDoc,result);
	        }
	        catch(Exception e){

	        }
	    }
}
