package com.p2.main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.p2.dao.DaoService;
import com.p2.models.Allegiance;
import com.p2.models.SecurityQ;
import com.p2.models.User;

@Component
public class Main {
	public static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	@Autowired
	DaoService ds;
	
	public static void main(String[] args) {
		Main m = new Main();
		m.initializeSecQues();
		
	}
	
	public void initializeSecQues() {
		ds.insertSecQues(new SecurityQ(1, "Name of your favorite star ship."));
		ds.insertSecQues(new SecurityQ(1, "Name of your first acquired droid."));
		ds.insertSecQues(new SecurityQ(1, "Planet your parents met on."));
		ds.insertSecQues(new SecurityQ(1, "Your favorite meal."));
		ds.insertSecQues(new SecurityQ(1, "Mother's maiden name."));
		ds.insertSecQues(new SecurityQ(1, "Academy you attended as a youngling."));
		ds.insertSecQues(new SecurityQ(1, "Name of your first mentor."));
	}
	
}
