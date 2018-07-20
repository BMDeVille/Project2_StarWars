package main;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import DAO.DaoService;
import DAO.DaoServiceImpl;
import models.User;

public class Main {
	public static void main(String[] args) {
		// Jenkins test 5
		// Jenkins test 6
		
		DaoService ds = new DaoServiceImpl();
		Date date = new Date();
		User u = new User("test1","l1","f1","test1@test1.com","test1",new Timestamp(date.getTime()),"secans",1);
		ds.insertUser(u);
		System.out.println("done");
	
	}
}
