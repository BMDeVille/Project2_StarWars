package serlvet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.DaoService;
import DAO.DaoServiceImpl;
import models.User;

public class ResetPassword {
	private static DaoService ds = new DaoServiceImpl();
	public static void reset(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println("in login cont");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		
		User u1 = ds.selectByUsername(username);
		if(u1 !=null) {
			if(BCrypt.checkpw(password, u1.getPassword())) {
				res.getWriter().write(new ObjectMapper().writeValueAsString("success"));
				//return something 
			}	
		}
		else {
			System.out.println("no exist username");
			res.getWriter().write(new ObjectMapper().writeValueAsString("failed"));
			//return;
		}
		return;

	}
}
