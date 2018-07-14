/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package DAO;

import java.util.List;

import models.Comment;
import models.Post;

public interface CommentDAO {
	//create
	public void insert(Comment c);
	//read
	public List<Comment> selectAll();
	public List<Comment> selectByPost(Post p);
	//update
	public void update(Comment c);
	//delete
	public void delete(Comment c);
}
