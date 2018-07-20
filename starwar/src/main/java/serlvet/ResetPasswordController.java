package serlvet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.DaoService;
import DAO.DaoServiceImpl;
import models.User;

@Controller("ResetPasswordController")
public class ResetPasswordController {
	final static Logger logger = Logger.getLogger(ResetPasswordController.class);

	@Autowired
	private static DaoService ds;

	public ResetPasswordController() {
	}

	@GetMapping(value = "/reset.app")
	public static void reset(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println("in login cont");

		String username = req.getParameter("username");
		// current password
		String password = req.getParameter("password");
		// get the hash of new password
		String newPass = BCrypt.hashpw(req.getParameter("newPass"), BCrypt.gensalt());
		System.out.println(username);
		System.out.println(password);
		System.out.println(newPass);

		User u1 = ds.selectByUsername(username);
		if (u1 != null) {
			// check if current password match to database
			if (BCrypt.checkpw(password, u1.getPassword())) {
				u1.setPassword(newPass);
				ds.updateUser(u1);
				res.getWriter().write(new ObjectMapper().writeValueAsString("success"));
				// logger.info(u1.getUsername() + " changed password.");
				// return something
			}
		} else {
			System.out.println("no exist username");
			res.getWriter().write(new ObjectMapper().writeValueAsString("failed"));
			// return;
		}
		return;

	}
}
