package serlvet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MasterServlet {
 private static final long serialVersionUID = 1L;
	 
//	 public void init(ServletConfig config) throws ServletException{
//	 }

	 protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
			req.getRequestDispatcher(RequestHelper.process(req,res)).forward(req, res);


	 }
	 
	 protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
			req.getRequestDispatcher(RequestHelper.process(req,res)).forward(req, res);
		 
			
	 
	 }
	 
}
