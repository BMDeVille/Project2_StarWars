package com.p2.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.p2.models.Allegiance;

@Repository("AllegianceDAO")
@Transactional
public class AllegianceDAOImpl implements AllegianceDAO{
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Autowired
	private SessionFactory sesFact;
	
	public AllegianceDAOImpl() {
	}

	public void insert(Allegiance a) {
		sesFact.getCurrentSession().save(a);
	}
	
	public List<Allegiance> selectAll() {
		return sesFact.getCurrentSession().createQuery("from Allegiance", Allegiance.class).list();
	}

	public List<Allegiance> selectByAllegiance(Allegiance a) {
		return sesFact.getCurrentSession().createQuery("from Allegiance where aid=" + a.getAid(), Allegiance.class).list();
	}
	public Allegiance selectByAid(int id) {
		return sesFact.getCurrentSession().createQuery("from Allegiance where aid=" + id, Allegiance.class).list().get(0);
	}

}
