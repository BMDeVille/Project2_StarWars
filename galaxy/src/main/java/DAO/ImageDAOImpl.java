/*
 * ---------------Star Wars Facebook-----------------
 * Primary Author: Jeffrey Martinez
 * Team:JiaLing Chen, Brandon DeVille, Jeffrey Martinez, Quinn Conlon
 * Last Modified: 14 Jul 2018
 * -------------------------------------------------
 */

package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Image;
import models.Post;
import models.User;
import util.HibernateUtil;

public class ImageDAOImpl implements ImageDAO{

	public void insert(Image img) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.save(img);
		tx.commit();
	}

	public List<Image> selectAll() {
		Session ses = HibernateUtil.getSession();
		List<Image> imglist = ses.createQuery("from Image", Image.class).list();
		return imglist;
	}

	public List<Image> selectByUser(User u) {
		Session ses = HibernateUtil.getSession();
		List<Image> imglist = ses.createQuery("from Image where myUser=" + u, Image.class).list();
		return imglist;
	}

	public List<Image> selectByPost(Post p) {
		Session ses = HibernateUtil.getSession();
		List<Image> imglist = ses.createQuery("from Image where myPost=" + p, Image.class).list();
		return imglist;
	}

	public void update(Image img) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.update(img);
		tx.commit();
	}

	public void delete(Image img) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.delete(img);
		tx.commit();
	}

}
