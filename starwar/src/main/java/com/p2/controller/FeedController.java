package com.p2.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p2.dao.DaoService;
import com.p2.models.Comment;
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
		for(Post p: feed) {
			List<Integer> likeids = ds.selectLikesByPid(p.getPid());
			List<User> likes = new ArrayList<User>();
			for (int i = 0; i < likeids.size(); ++i) {
				likes.add(ds.selectById(likeids.get(i)));
			}
			p.setLikes(likes);
//			p.setLikes(null);
			p.setImages(null);
		}
		System.out.println("from all feed controller: " + feed);
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
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value = "/allComments.app")
	public  @ResponseBody List<Comment> getAllComments(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		System.out.println("in all comment controller");
		List<Comment> coms = ds.selectAllComment();
		for (Comment c: coms) {
			// c.setLikes(null);
			c.getPost().setLikes(null);
			c.getPost().setImages(null);
		}
		System.out.println("from all com controller: " + coms);
		res.setContentType("application/json");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		return coms;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/newComment.app")
	public void createNewComment(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		res.setContentType("application/json");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		System.out.println("in new comment");
		String body = req.getParameter("body");
		String username = req.getParameter("username");
		int postid = Integer.parseInt(req.getParameter("postid"));
		User u = ds.selectByUsername(username);
		List<Post> pl = ds.selectByPid(postid);
		Post p = pl.get(0);
		Comment nc = new Comment(body, p, u);
		ds.insertComment(nc);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/newPost.app")
	public void createNewPost(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		res.setContentType("application/json");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		System.out.println("in new post");
		String body = req.getParameter("body");
		String username = req.getParameter("username");
		String image = req.getParameter("images");
		List<String> list1 = new ArrayList<String>();
		list1.add(image);
		Timestamp ts = new Timestamp(new Date().getTime());
		User u = ds.selectByUsername(username);
		Post np = new Post(body, ts, list1, u );
		ds.insertPost(np);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/likeComment.app")
	public void updateComment(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		res.setContentType("application/json");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		System.out.println("in like comment");
		int cid = Integer.parseInt(req.getParameter("cid"));
		String username = req.getParameter("username");
		boolean delete = Boolean.parseBoolean(req.getParameter("delete"));
		User u = ds.selectByUsername(username);
		List<Comment> coms = ds.selectByCid(cid);
		Comment com = coms.get(0);
		List<User> likes = com.getLikes();
		if (!delete) {
			likes.add(u);
		}
		else {
			int index;
			for (int i = 0; i < likes.size(); ++i) {
				if (likes.get(i).getId() == u.getId()) {
					index = i;
					likes.remove(index);
					break;
				}
			}
		}
		com.setLikes(likes);
		ds.updateComment(com);
		
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value = "/likePost.app")
	public void updatePost(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		res.setContentType("application/json");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		System.out.println("in like post");
		int pid = Integer.parseInt(req.getParameter("pid"));
		String username = req.getParameter("username");
		boolean delete = Boolean.parseBoolean(req.getParameter("delete"));
		User u = ds.selectByUsername(username);
		List<Post> posts = ds.selectByPid(pid);
		List<Integer> likeids = ds.selectLikesByPid(pid);
		Post p = posts.get(0);
		List<User> likes = new ArrayList<User>();
		for (int i = 0; i < likeids.size(); ++i) {
			likes.add(ds.selectById(likeids.get(i)));
		}
		if (!delete) {
			likes.add(u);
		}
		else {
			int index;
			for (int i = 0; i < likes.size(); ++i) {
				if (likes.get(i).getId() == u.getId()) {
					index = i;
					likes.remove(index);
					break;
				}
			}
		}
		p.setLikes(likes);
		ds.updatePost(p);
	}
	
}
