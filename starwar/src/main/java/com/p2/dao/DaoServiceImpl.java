package com.p2.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.p2.models.Allegiance;
import com.p2.models.Comment;
import com.p2.models.Image;
import com.p2.models.Post;
import com.p2.models.User;



@Service("DaoService")
//@Transactional
public class DaoServiceImpl implements DaoService {
	@Autowired
	private CommentDAO cd;
	@Autowired
	private ImageDAO id;
	@Autowired
	private PostDAO pd;
	@Autowired
	private UserDAO ud;
	@Autowired
	private AllegianceDAO ad;
	
	
	public DaoServiceImpl() {
		System.out.println("in dao service");
	}


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
	
	
	//Allegiance dao
	public void insertAllegiance(Allegiance u) {
		ad.insert(u);
	}
	public List<Allegiance> selectAllAllegiance() {
		return ad.selectAll();
	}
	public List<Allegiance> selectByAllByAllegiance(Allegiance u) {
		return ad.selectByAllegiance(u);
	}
	public void updateAllegiance(Allegiance u) {
		ad.update(u);
	}
	public void deleteAllegiance(Allegiance u) {
		ad.delete(u);
	}
	
	
}
