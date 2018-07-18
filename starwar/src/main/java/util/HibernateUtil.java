package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


// might not need this class since we use MVC pattern 
public class HibernateUtil {
	private static Session ses ;
	public static void closeSes() {
		ses.close();
	}
	private static SessionFactory sf= new Configuration()
			.configure("Hibernate.cfg.xml")
				.buildSessionFactory();
	public static Session getSession() {
		if(ses==null) {
			ses= sf.openSession();
		}
		return ses;
	}
}
