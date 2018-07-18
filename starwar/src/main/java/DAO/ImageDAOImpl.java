/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import models.Image;
import models.Post;
import models.User;

@Repository("ImageDao")
@Transactional
public class ImageDAOImpl implements ImageDAO{
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Autowired
	private SessionFactory sesFact;
	
	public ImageDAOImpl() {

	}
	
	public void insert(Image img) {
		sesFact.getCurrentSession().save(img);
	}

	public List<Image> selectAll() {
		return sesFact.getCurrentSession().createQuery("from Image", Image.class).list();
	}

	public List<Image> selectByUser(User u) {
		return sesFact.getCurrentSession().createQuery("from Image where myUser=" + u, Image.class).list();
	}

	public List<Image> selectByPost(Post p) {
		return sesFact.getCurrentSession().createQuery("from Image where myPost=" + p, Image.class).list();
	}

	public void update(Image img) {
		sesFact.getCurrentSession().update(img);
	}

	public void delete(Image img) {
		sesFact.getCurrentSession().delete(img);
	}

}
