package com.p2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2.dao.DaoService;
import com.p2.models.Post;
import com.p2.models.User;


@Controller("FeedController")

public class FeedController {
	
	@Autowired
	private  DaoService ds;
	
	public FeedController() {
		
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value = "/allFeed.app")
	public  @ResponseBody List<Post> getAllFeed(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println("in all feed controller");
		List<Post> feed = ds.selectAllPost();
		System.out.println("from controller: " + feed);
		res.setContentType("application/json");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		return feed;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value = "/userFeed.app")
	public  @ResponseBody List<Post> getFeedByUserId(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		String username = req.getParameter("username");
		User u = ds.selectByUsername(username);
		List<Post> feed = ds.selectByUserPost(u);
		res.setContentType("application/json");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		return feed;
	}

}
