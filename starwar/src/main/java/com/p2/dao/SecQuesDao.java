package com.p2.dao;

import java.util.List;

import com.p2.models.SecurityQ;

public interface SecQuesDao {
	public void insertQues(SecurityQ sec_ques);
	
	public List<SecurityQ> selectAll(); 
	public SecurityQ selectById(int id);
}
