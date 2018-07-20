package serlvet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.DaoService;
import models.User;

@Controller("RegisterController")
public class RegisterController {

	public RegisterController() {
	}

	@Autowired
	private static DaoService ds;

	@GetMapping(value = "/createAccount.app")
	public static String createAccount(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println("You are creating a user");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");

		// DOB Section
		String dob = req.getParameter("dob");
		String pattern = "dd-MMM-yyyy";
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
		User user1 = new User(username, firstName, lastName, email, password, dob1, securityAnswer, allegiance1);
		// logger.info("user register: " + user1.getUsername() + " " + user1.getFname()
		// + " "+ user1.getLname());
		ds.insertUser(user1);

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