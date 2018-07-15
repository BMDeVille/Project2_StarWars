package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.User;
import service.UserService;
import service.UserServiceImpl;

public class SignUpController {


public static String createAccount(HttpServletRequest request) {
        
    	System.out.println("You are creating a user");
    	
    	UserService service = new UserServiceImpl();
    	
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        
        //DOB Section
        String dob = request.getParameter("dob");
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
    	
    	
    	String securityAnswer = request.getParameter("securityAnswer");
    	String allegiance = request.getParameter("allegiance");
    	int allegiance1 = Integer.parseInt(allegiance);
    	String about = request.getParameter("about");

    	///creating the user object with the no id, about, and image constructor
        User user1 = new User(username, firstName, lastName, email, password, dob1, securityAnswer, allegiance1);
       
        service.insert(user1);
        
        //confirming the account is made throught the console
        System.out.println(user1);
        

        //pulling back the object from the database.
        List<User> userList = service.selectByUsername(username);
        String userListString = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			userListString = mapper.writeValueAsString(userList);//map reimbursement array to json
		}
		catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userListString;
        
	}


//String image = request.getParameter("image");
//String image1 = convertStringToImageByteArray(image);

//private static String convertStringToImageByteArray(String imageString){
//    
//    OutputStream outputStream = null;
//    byte [] imageInByteArray = Base64.decodeBase64(
//            imageString);
//    try {
//        outputStream = new FileOutputStream("resources"
//                + "\\Spring2.png");
//        outputStream.write(imageInByteArray);
//    } catch (FileNotFoundException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }finally{
//        try {
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        	}
//    	}
//    return null;
//    
//	}
}

	
	

