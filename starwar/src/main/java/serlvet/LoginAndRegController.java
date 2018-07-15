package serlvet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.DaoService;
import DAO.DaoServiceImpl;
import models.User;
//
//@Controller
//@RequestMapping("user")
//@CrossOrigin(origins= {"http://localhost:4200"});

public class LoginAndRegController {
	private static DaoService ds = new DaoServiceImpl();
	public static void login(HttpServletRequest req, HttpServletResponse res)
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
	public static String createAccount(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
System.out.println("You are creating a user");
    	
    	DaoService service = new DaoServiceImpl();
    	
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        
        //DOB Section
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
    	//------------------------------------
    	
    	
    	String securityAnswer = req.getParameter("securityAnswer");
    	String allegiance = req.getParameter("allegiance");
    	int allegiance1 = Integer.parseInt(allegiance);
    	String about = req.getParameter("about");

    	///creating the user object with the no id, about, and image constructor
        User user1 = new User(username, firstName, lastName, email, password, dob1, securityAnswer, allegiance1);
       
        ds.insertUser(user1);
        
        //confirming the account is made throught the console
        System.out.println(user1);
        

        //pulling back the object from the database.
        User user = ds.selectByUsername(username);
        String userListString = null;
		ObjectMapper mapper = new ObjectMapper();
		
		userListString = mapper.writeValueAsString(user);//map reimbursement array to json
		
		return userListString;
        
	}
	//String image = request.getParameter("image");
	//String image1 = convertStringToImageByteArray(image);

	//private static String convertStringToImageByteArray(String imageString){
	//    
//	    OutputStream outputStream = null;
//	    byte [] imageInByteArray = Base64.decodeBase64(
//	            imageString);
//	    try {
//	        outputStream = new FileOutputStream("resources"
//	                + "\\Spring2.png");
//	        outputStream.write(imageInByteArray);
//	    } catch (FileNotFoundException e) {
//	        e.printStackTrace();
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }finally{
//	        try {
//	            outputStream.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        	}
//	    	}
//	    return null;
	//    
//		}

	}
	
	
}
