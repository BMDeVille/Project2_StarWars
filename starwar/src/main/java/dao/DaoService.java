package dao;

import java.util.List;

import models.Allegiance;
import models.Comment;
import models.Image;
import models.Post;
import models.User;

public interface DaoService {
		//comment service
		//create
		public void insertComment(Comment c);
		//read
		public List<Comment> selectAllComment();
		public List<Comment> selectByPostCom(Post p);
		//update
		public void updateComment(Comment c);
		//delete
		public void deleteComment(Comment c);
		
		//image service
		//create
		public void insertImg(Image img);
		//read
		public List<Image> selectAllImg();
		public List<Image> selectByUserImg(User u);
		public List<Image> selectByPostImg(Post p);
		//update
		public void updateImg(Image img);
		//delete
		public void deleteImg(Image img);
		
		//post service
		//create
		public void insertPost(Post p);
		//read
		public List<Post> selectAllPost();
		public List<Post> selectByUserPost(User u);
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
		public List<User> selectByFirstName(String fname);
		public List<User> selectByLastName(String lname);
		//update
		public void updateUser(User u);
		//delete
		public void deleteUser(User u);
		
		//Allegiance service 
		public void insertAllegiance(Allegiance u);
		//read
		public List<Allegiance> selectAllAllegiance();
		public List<Allegiance> selectByAllByAllegiance(Allegiance u);
		//update
		public void updateAllegiance(Allegiance u);
		//delete
		public void deleteAllegiance(Allegiance u);
}
