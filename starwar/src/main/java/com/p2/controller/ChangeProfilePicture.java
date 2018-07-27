package com.p2.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.p2.dao.DaoService;
import com.p2.models.User;

@Controller("ChangeProfilePicture")
public class ChangeProfilePicture {

	
	public ChangeProfilePicture() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public DaoService ds;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/updateProfilePicture.app")
	public  @ResponseBody User updatePicture(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException, ParseException {
		System.out.println("You are changing a profile picture");
		
		String image = req.getParameter("image");
		 
		String username = req.getParameter("username");

		System.out.println(image);
		
		System.out.println(username);
		
		User user = ds.selectByUsername(username);
		
		
		
		user.setImage(image);
		
		ds.updateUser(user);
		
		System.out.println(user);
		
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		
		return user;
}
	
}
