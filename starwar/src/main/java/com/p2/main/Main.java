package com.p2.main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
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
	
	public static void main(String[] args) {
		DaoService ds = context.getBean("DaoService", DaoService.class);
		System.out.println(ds.selectAll());
	}
	
}
