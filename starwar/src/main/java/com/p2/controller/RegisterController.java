package com.p2.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.CrossOrigin;
=======
>>>>>>> e4ef6056e8bac3c7715a26da32a7f34dec6ca31a
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2.dao.DaoService;
import com.p2.models.Allegiance;
import com.p2.models.User;

@Controller("RegisterController")
@CrossOrigin(origins="http://localhost:4200")
public class RegisterController {
	final static Logger logger = Logger.getLogger(RegisterController.class);
	public RegisterController() {
	}

	@Autowired
	private DaoService ds;


	@PostMapping(value = "/createAccount.app")
	public  @ResponseBody String createAccount(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println("You are creating a user");
		Date d = new Date();
		Allegiance al1 = new Allegiance();
		al1.setAllegiance("a");
		System.out.println("check1");
		User test = new User("test11","l1","f1","test2@test2.com","test11",new Timestamp(d.getTime()),"secans",al1);
		System.out.println("check2");
		ds.insertUser(test);
		System.out.println("check3");


		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");

		// DOB Section
		String dob = req.getParameter("dob");
		String pattern = "dd-MMM-yyyy";
		System.out.println("check5");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = (Date) simpleDateFormat.parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Date:" + date);
		Timestamp dob1 = new Timestamp(date.getTime());
		// ------------------------------------

		String securityAnswer = req.getParameter("securityAnswer");
		String allegiance = req.getParameter("allegiance");
		int allegiance1 = Integer.parseInt(allegiance);
		String about = req.getParameter("about");

		/// creating the user object with the no id, about, and image constructor
		Allegiance al = new Allegiance();
		al.setAllegiance("aa");
		User user1 = new User(username, firstName, lastName, email, password, dob1, securityAnswer, al);
		logger.info("user register: " + user1.getUsername() + " " + user1.getFname());
		// + " "+ user1.getLname());
		//ds.insertUser(user1);

		// confirming the account is made throught the console
		System.out.println(user1);

		// pulling back the object from the database.
		User user = ds.selectByUsername(username);
		String userListString = null;
		ObjectMapper mapper = new ObjectMapper();

		userListString = mapper.writeValueAsString(user);// map reimbursement array to json

		return userListString;

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
