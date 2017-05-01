package com.tweetquiz;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class TweetquizzzzServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		String userName = req.getParameter("playerName");
		resp.getWriter().println("UserName : " + userName);
		
	}
}
