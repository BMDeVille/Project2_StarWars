package controller;

import javax.servlet.http.HttpServletRequest;


public class RequestHelper {

	
	public static String process(HttpServletRequest request) {
		System.out.println(request.getRequestURI());switch(request.getRequestURI())
		{
	    case "/galaxy/signup.do":
	    return SignUpController.createAccount(request);
	    default:
	    return "index.html";
	    
		}
	}
}
