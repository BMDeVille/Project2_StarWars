package DAO;

import java.util.List;

import models.Comment;
import models.Image;
import models.Post;
import models.User;


public class DaoServiceImpl implements DaoService {
	private CommentDAO cd = new CommentDAOImpl();
	private ImageDAO id = new ImageDAOImpl();
	private PostDAO pd = new PostDAOImpl();
	private UserDAO ud = new UserDAOImpl();
	
	//comment dao
	public void insertComment(Comment c) {
		cd.insert(c);
	}
	public List<Comment> selectAllComment() {
		return cd.selectAll();
	}

	public List<Comment> selectByPostCom(Post p) {
		return cd.selectByPost(p);
	}

	public void updateComment(Comment c) {
		cd.update(c);
	}

	public void deleteComment(Comment c) {
		cd.delete(c);
	}
	
	//img dao
	public void insertImg(Image img) {
		id.insert(img);
	}

	public List<Image> selectAllImg() {
		return id.selectAll();
	}

	public List<Image> selectByUserImg(User u) {
		return id.selectByUser(u);
	}

	public List<Image> selectByPostImg(Post p) {
		return id.selectByPost(p);
	}

	public void updateImg(Image img) {
		id.update(img);
	}

	public void deleteImg(Image img) {
		id.delete(img);
	}
	//post dao
	public void insertPost(Post p) {
		pd.insert(p);
	}

	public List<Post> selectAllPost() {
		return pd.selectAll();
	}

	public List<Post> selectByUserPost(User u) {
		return pd.selectByUser(u);
	}

	public void updatePost(Post p) {
		pd.update(p);
	}

	public void deletePost(Post p) {
		pd.delete(p);
	}

	//user dao
	public void insertUser(User u) {
		ud.insert(u);
	}

	public List<User> selectAllUser() {
		return ud.selectAll();
	}

	public User selectByUsername(String username) {
		return ud.selectByUsername(username);
	}

	public List<User> selectByFirstName(String fname) {
		return ud.selectByFirstName(fname);
	}

	public List<User> selectByLastName(String lname) {
		return ud.selectByLastName(lname);
	}

	public void updateUser(User u) {
		ud.update(u);
	}

	public void deleteUser(User u) {
		ud.delete(u);
	}
	
}
