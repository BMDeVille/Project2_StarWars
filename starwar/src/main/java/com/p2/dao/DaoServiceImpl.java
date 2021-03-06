package com.p2.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.p2.models.Allegiance;
import com.p2.models.Comment;
import com.p2.models.Post;
import com.p2.models.SecurityQ;
import com.p2.models.User;



@Service("DaoService")
//@Transactional
public class DaoServiceImpl implements DaoService {
	@Autowired
	private CommentDAO cd;
	@Autowired
	private PostDAO pd;
	@Autowired
	private UserDAO ud;
	@Autowired
	private AllegianceDAO ad;
	@Autowired
	private SecQuesDao sd;
	
	
	public DaoServiceImpl() {
		System.out.println("in dao service");
	}


	//comment dao
	public void insertComment(Comment c) {
		cd.insert(c);
	}
	public List<Comment> selectAllComment() {
		System.out.println("in service get all comment");
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
	//post dao
	public void insertPost(Post p) {
		pd.insert(p);
	}

	public List<Post> selectAllPost() {
		System.out.println("in service get all post");
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
	
	public User selectByEmail(String email) {
		return ud.selectByEmail(email);
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
	public Allegiance selectByAid(int id) {
		return ad.selectByAid(id);
	}
	public List<Allegiance> selectByAllByAllegiance(Allegiance u) {
		return ad.selectByAllegiance(u);
	}

	//Security Question
	public void insertSecQues(SecurityQ sq) {
		sd.insertQues(sq);
	}
	public List<SecurityQ> selectAll(){
		return sd.selectAll();
	}
	public SecurityQ getBySqid(int id) {
		return sd.selectById(id);
	}


	public List<Post> selectByPid(int pid) {
		return pd.selectByPid(pid);
	}


	public List<Comment> selectByCid(int cid) {
		return cd.selectByCid(cid);
	}


	public List<Integer> selectLikesByPid(int pid) {
		return pd.selectLikesByPid(pid);
	}


	public User selectById(int id) {
		return ud.selectById(id);
	}
	
	
}
