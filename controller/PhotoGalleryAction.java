/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.dao.DAOException;

import databeans.Photo;

import model.Model;
import model.PhotoDAO;

public class PhotoGalleryAction extends Action{

	public String getName() { return "photoGallery.do"; }
	private PhotoDAO  photoDAO;
	
    public PhotoGalleryAction(Model model) {
    	photoDAO  = model.getPhotoDAO();
	}
    

    public String perform(HttpServletRequest request) {
    List<String> errors = new ArrayList<String>();
    request.setAttribute("errors",errors);
    try { 
    	// get all the photos stored in the database and pass the list to the view
    	Photo[] photo_list;
    	photo_list = photoDAO.getPhotos();
    	System.out.println("length = " +photo_list.length);
    	
        request.setAttribute("photoList",photo_list);
		
		return "photo-gallery.jsp";
    }
    catch (DAOException e) {
    	errors.add(e.getMessage());
    	return "error.jsp";
    } catch (Exception e) {
    	errors.add(e.getMessage());
    	return "error.jsp";
    }
    }
}
