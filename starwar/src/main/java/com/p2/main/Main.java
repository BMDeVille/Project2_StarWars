package com.p2.main;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.p2.dao.DaoService;
import com.p2.models.Allegiance;
import com.p2.models.User;

@Component
public class Main {
	//@Autowired
	//private static DaoService ds;
	public static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static void main(String[] args) {
		// Jenkins test 5
		// Jenkins test 6
		//UserDAOImpl ud = (UserDAOImpl) context.getBean("service");
		//UserDAOImpl ud = new UserDAOImpl();
//		DaoService ds = new DaoServiceImpl();
		Allegiance al = new Allegiance();
		al.setAllegiance("idk");
		Date date = new Date();
		DaoService ds = context.getBean("DaoService", DaoService.class);
		User u = new User("test1","l1","f1","test1@test1.com","test1",new Timestamp(date.getTime()),"secans",al);
		ds.insertUser(u);
		//ud.insert(u);
		System.out.println("done");
	
	}
}
