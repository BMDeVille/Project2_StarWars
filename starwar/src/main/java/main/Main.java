package main;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import DAO.DaoService;
import DAO.DaoServiceImpl;
import models.Allegiance;
import models.User;

public class Main {
	@Autowired
	private static DaoService ds;
	
	public static void main(String[] args) {
		// Jenkins test 5
		// Jenkins test 6
		
		Allegiance al = new Allegiance();
		al.setAllegiance("idk");
		Date date = new Date();
		User u = new User("test1","l1","f1","test1@test1.com","test1",new Timestamp(date.getTime()),"secans",al);
		ds.insertUser(u);
		System.out.println("done");
	
	}
}
