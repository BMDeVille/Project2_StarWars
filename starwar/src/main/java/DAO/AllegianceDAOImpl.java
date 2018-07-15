package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Allegiance;
import models.Comment;
import util.HibernateUtil;

public class AllegianceDAOImpl implements AllegianceDAO{

	@Override
	public void insert(Allegiance a) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.save(a);
		tx.commit();		
	}

	@Override
	public List<Allegiance> selectAll() {
		Session ses = HibernateUtil.getSession();
		List<Allegiance> alist = ses.createQuery("from Allegiance", Allegiance.class).list();
		return alist;
	}

	@Override
	public List<Allegiance> selectByAllegiance(Allegiance a) {
		Session ses = HibernateUtil.getSession();
		List<Allegiance> alist = ses.createQuery("from Allegiance where allegiance=" + a, Allegiance.class).list();
		return alist;
	}

	@Override
	public void update(Allegiance a) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.update(a);
		tx.commit();		
	}

	@Override
	public void delete(Allegiance a) {
		Session ses = HibernateUtil.getSession();
		Transaction tx= ses.beginTransaction();
		ses.delete(a);
		tx.commit();		
	}

}
