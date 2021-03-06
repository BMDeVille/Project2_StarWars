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
import com.p2.dao.DaoService;
import com.p2.models.User;

@Controller("UserController")
public class UserController {
	public UserController() {
	}
	
	@Autowired
	public DaoService ds;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/get-by-email.app")
	public @ResponseBody User getByEmail(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		String email = req.getParameter("email");
		res.setContentType("application/json");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		User u = ds.selectByEmail(email);
		return u;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/get-by-username.app")
	public @ResponseBody User getByUsername(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		String username = req.getParameter("username");
		res.setContentType("application/json");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		User u = ds.selectByUsername(username);
		return u;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/get-users.app")
	public @ResponseBody List<User> getAllUsers(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		res.setContentType("application/json");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		return ds.selectAllUser();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/get-by-id.app")
	public @ResponseBody User getById(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		int uid = Integer.parseInt(req.getParameter("id"));
		res.setContentType("application/json");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		User u = ds.selectById(uid);
		return u;
	}
}
