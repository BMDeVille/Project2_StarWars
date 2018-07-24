package com.p2.dao;

import com.p2.models.SecurityQ;

public interface SecQuesDao {
	public void insertQues(SecurityQ sec_ques);
	
	public SecurityQ selectById(int id);
}
