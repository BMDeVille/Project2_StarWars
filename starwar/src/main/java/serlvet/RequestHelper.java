package serlvet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;


public class RequestHelper {
	public static String process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, ServletException {
		System.out.println("in rh");
		switch(req.getRequestURI()) {
		case "/starwar/login.ms":
			return LoginAndRegController.login(req,res);	
		case "/starwar/reg.do":
			return LoginAndRegController.createAccount(req,res);
		case "/starwar/reset.do":
			//return ResetPassword.reset(req,res);
		default:
			 break;
		
		}
	}
}
