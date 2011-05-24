/*
 * Copyrights (c) 2011 Rohit Harchandani and Risha Chheda
 * For the complete license, please refer to the root-level license.txt document
 */

package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Model;

import databeans.*;


public class Controller extends HttpServlet {

    public void init() throws ServletException {
        Model model = new Model(getServletConfig());
        
        // add all action objects in the Action(abstract base class) hash map
        Action.add(new ChangePwdAction(model));
        Action.add(new ImageAction(model));
        Action.add(new MovieAction(model));
        Action.add(new ListAction(model));
        Action.add(new ListMovieAction(model));
        Action.add(new LoginAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new ManageAction(model));
        Action.add(new ManageMovieAction(model));
        Action.add(new AddMovieDetailsAction(model));
        Action.add(new RegisterAction(model));
        Action.add(new HomeAction(model));
        Action.add(new RemoveAction(model));
        Action.add(new UploadAction(model));
        Action.add(new UploadTrailerAction(model));
        Action.add(new ViewAction(model));
        Action.add(new SearchAction(model));
        Action.add(new DisplayMovieDetailsAction(model));
        Action.add(new ViewTrailerAction(model));
        Action.add(new ListTrailersAction(model));
        Action.add(new AddMovieCrewAction(model));
        Action.add(new AddQuizQuestionAction(model));
        Action.add(new CreateBlogAction(model));
        Action.add(new EvaluateQuizAction(model));
        Action.add(new HomeAction(model));
        Action.add(new MovieReviewAction(model));
        Action.add(new PhotoGalleryAction(model));
        Action.add(new PlayQuizAction(model));
        Action.add(new ViewBlogsAction(model));
        Action.add(new ViewBlogPostsAction(model));
        Action.add(new ReplyBlogAction(model));
        Action.add(new HomepageAction(model));
        Action.add(new SearchUserAction(model));
        Action.add(new GoAction(model));

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// get the name of jsp to display on performing the action
        String nextPage = performTheAction(request, response);
        sendToNextPage(nextPage,request,response);
    }
    
    /*
     * Extracts the requested action and (depending on whether the user is logged in)
     * perform it (or make the user login).
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session     = request.getSession(true);
        String      servletPath = request.getServletPath();
        User        user = (User) session.getAttribute("user");
        String      action = getActionName(servletPath);
        System.out.println("action" + action);

        if (action.equals("rss.do"))
        	try{
        		RSSFeedsAction.processRequest(request, response, this);
        	}catch (Exception e) {
				// TODO: handle exception
			}
        if (action.equals("register.do") || action.equals("login.do") || action.equals("homepage.do")) {
        	// Allow these actions without logging in
        	return Action.perform(action,request);
        	}

        
        if (action.equals("searchMovie.do") || action.equals("displayMovie.do") || action.equals("viewTrailer.do") || action.equals("listTrailer.do")) {
        	// Allow these actions without logging in
			return Action.perform(action,request);
        }
        
        if (action.equals("playQuiz.do") || action.equals("evaluateQuiz.do")|| action.equals("image.do") || action.equals("photoGallery.do") || action.equals("view_blogs.do")){
        	// Allow these actions without logging in
			return Action.perform(action,request);
        }
        if (action.equals("view_blog_posts.do") ){
        	// Allow these actions without logging in
			return Action.perform(action,request);
        }
        if (user == null ) {
        	// If the user hasn't logged in, direct him to the login page
			return Action.perform("home.do",request);
        }
        
        if (action.equals("start")) {
        	// If he's logged in but back at the /start page, send him to the homepage of the site
			return Action.perform("manage.do",request);
        }

      	// Let the logged in user run his chosen action
		return Action.perform(action,request);
    }
    
    /*
     * If nextPage is null, send back 404
     * If nextPage starts with a '/', redirect to this page.
     *    In this case, the page must be the whole servlet path including the webapp name
     * Otherwise dispatch to the page (the view)
     *    This is the common case
     * Note: If nextPage equals "image", we will dispatch to /image.  In the web.xml file, "/image"
     *    is mapped to the ImageServlet will which return the image bytes for display.
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	System.out.println("nextPage="+nextPage);
    	
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	
    	if (nextPage.charAt(0) == '/') {
			String host  = request.getServerName();
			String port  = ":"+String.valueOf(request.getServerPort());
			if (port.equals(":80")) port = "";
			response.sendRedirect("http://"+host+port+nextPage);
			return;
    	}
    	
   		RequestDispatcher d = request.getRequestDispatcher("/"+nextPage);
   		d.forward(request,response);
    }

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
}
