/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package DAO;

import java.util.List;

import models.Image;
import models.Post;
import models.User;

public interface ImageDAO {
	//create
	public void insert(Image img);
	//read
	public List<Image> selectAll();
	public List<Image> selectByUser(User u);
	public List<Image> selectByPost(Post p);
	//update
	public void update(Image img);
	//delete
	public void delete(Image img);
}
