package view;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import databeans.Photo;


/**
 * This servlet is the "view" for images.  It looks at the "photo"
 * request attribute and sends it's image bytes to the client browser.
 * 
 * We need to use a servlet instead of a JSP for the "view" of the image
 * because we need to send back the image bits and not HTML.
 */
public class ImageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	Photo photo = (Photo) request.getAttribute("photo");

        if (photo == null) {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
        	return;
        }
        
        response.setContentType(photo.getContentType());
        System.out.println("in servlet");
        ServletOutputStream out = response.getOutputStream();
        out.write(photo.getBytes());
    }
}
