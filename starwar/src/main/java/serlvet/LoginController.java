package serlvet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.DaoService;
import DAO.DaoServiceImpl;
import models.User;
//
//@Controller
//@RequestMapping("user")
//@CrossOrigin(origins= {"http://localhost:4200"});
@Controller("LoginController")
public class LoginController {
	public LoginController() {
	}
	
	//final static Logger logger = Logger.getLogger(LoginAndRegController.class);

	@Autowired
	private static DaoService ds;
	
	@GetMapping(value="/login.app")
	public static @ResponseBody User login(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println("in login cont");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		
		User u1 = ds.selectByUsername(username);
		if(u1 !=null) {
			//check if password match to database 
			if(BCrypt.checkpw(password, u1.getPassword())) {
//				logger.info("user: " + u1.getUsername() + " login ");
				//res.getWriter().write(new ObjectMapper().writeValueAsString(u1));
				return u1;
			}	
		}
		else {
			System.out.println("no existing username");
			//logger.info("no existing username: " + username);
			res.getWriter().write(new ObjectMapper().writeValueAsString("failed"));
			//return;
		}
		return u1;

	}
	
	
	
}
