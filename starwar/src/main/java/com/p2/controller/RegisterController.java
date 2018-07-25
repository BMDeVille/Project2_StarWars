package com.p2.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2.dao.DaoService;
import com.p2.models.Allegiance;
import com.p2.models.SecurityQ;
import com.p2.models.User;

@Controller("RegisterController")
//@CrossOrigin
public class RegisterController {
	final static Logger logger = Logger.getLogger(RegisterController.class);
	public RegisterController() {
	}

	@Autowired
	private DaoService ds;

	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/createAccount.app")
	public  @ResponseBody User createAccount(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException, ParseException {
		System.out.println("You are creating a user");
		res.setContentType("application/json");

		String username = req.getParameter("username");
//		if(ds.selectByUsername(username) == null) {
//			res.getWriter().write(new ObjectMapper().writeValueAsString("exist username"));
//			return new User();
//		}
		String password = req.getParameter("password");
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String email = req.getParameter("email");
//		if(ds.selectByEmail(email) == null) {
//			res.getWriter().write(new ObjectMapper().writeValueAsString("exist email"));
//			return new User();
//		}
		// DOB Section
		String dob = req.getParameter("date");
		System.out.println(dob);
		String split [] = dob.split("-");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//Date d = new Date(Integer.parseInt(split[0]), Integer.parseInt(split[1]),Integer.parseInt(split[2]));
		Date d = df.parse(dob);
		Timestamp dob1 = new Timestamp(d.getTime());
		//joined date as the register date
		Date join = new Date();
		Timestamp joined = new Timestamp(join.getTime());
		// ------------------------------------
		System.out.println("Date:" + dob1);
		System.out.println("joined " + joined);

		//String securityAnswer = req.getParameter("securityAnswer");
		int allegiance = Integer.parseInt(req.getParameter("type"));
		//String about = req.getParameter("about");
		Allegiance al = ds.selectByAid(allegiance);
		int sec_ques = Integer.parseInt(req.getParameter("ques"));
		SecurityQ sq = ds.getBySqid(sec_ques);
		String securityAnswer = req.getParameter("ans");

		System.out.println(username + " " + password + " " + firstName + " " + lastName + " " +email);
		System.out.println(dob + " " + allegiance  + " " + sec_ques + " " + securityAnswer);
		
		String pw = BCrypt.hashpw(password, BCrypt.gensalt());
		User user1 = new User(username, firstName, lastName, email, pw, dob1,securityAnswer, sq, joined, al);

		logger.info("user register: " + user1.getUsername() + " " + user1.getFname());
		

		// confirming the account is made throught the console
		System.out.println(user1);

		ds.insertUser(user1);
		System.out.println("check insert");
		// pulling back the object from the database.
		User user = ds.selectByUsername(username);
		System.out.println("check get by username");

		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		res.getWriter().write(new ObjectMapper().writeValueAsString(user));
		return user;

	}
	// String image = request.getParameter("image");
	// String image1 = convertStringToImageByteArray(image);

	// private static String convertStringToImageByteArray(String imageString){
	//
	// OutputStream outputStream = null;
	// byte [] imageInByteArray = Base64.decodeBase64(
	// imageString);
	// try {
	// outputStream = new FileOutputStream("resources"
	// + "\\Spring2.png");
	// outputStream.write(imageInByteArray);
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }finally{
	// try {
	// outputStream.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// return null;
	//
	// }

}
