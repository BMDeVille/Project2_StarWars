package DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import models.Allegiance;

@Repository("AllegianceDao")
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

	public void update(Allegiance a) {
		sesFact.getCurrentSession().update(a);
		
	}

	public void delete(Allegiance a) {
		sesFact.getCurrentSession().delete(a);
	}



}
