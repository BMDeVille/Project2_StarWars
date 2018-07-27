package com.p2.dao;

import java.util.List;

import com.p2.models.Allegiance;
import com.p2.models.Comment;
import com.p2.models.Post;
import com.p2.models.SecurityQ;
import com.p2.models.User;

public interface DaoService {
		//comment service
		//create
		public void insertComment(Comment c);
		//read
		public List<Comment> selectAllComment();
		public List<Comment> selectByPostCom(Post p);
		public List<Comment> selectByCid(int cid);
		//update
		public void updateComment(Comment c);
		//delete
		public void deleteComment(Comment c);
		
		//post service
		//create
		public void insertPost(Post p);
		//read
		public List<Post> selectAllPost();
		public List<Post> selectByUserPost(User u);
		public List<Post> selectByPid(int pid);
		public List<Integer> selectLikesByPid(int pid);
		//update
		public void updatePost(Post p);
		//delete
		public void deletePost(Post p);
		
		//user service
		//create
		public void insertUser(User u);
		//read
		public List<User> selectAllUser();
		public User selectByUsername(String username);
		public User selectByEmail(String email);
		public List<User> selectByFirstName(String fname);
		public List<User> selectByLastName(String lname);
		public List<User> selectById(int id);
		//update
		public void updateUser(User u);
		//delete
		public void deleteUser(User u);
		
		//Allegiance service 
		public void insertAllegiance(Allegiance u);
		//read
		public List<Allegiance> selectAllAllegiance();
		public List<Allegiance> selectByAllByAllegiance(Allegiance u);
		public Allegiance selectByAid(int id);
		
		//Security Question Service
		public void insertSecQues(SecurityQ sq);
		public List<SecurityQ> selectAll();
		public SecurityQ getBySqid(int id);
}
