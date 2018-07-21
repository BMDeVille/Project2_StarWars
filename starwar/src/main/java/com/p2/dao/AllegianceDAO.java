package com.p2.dao;

import java.util.List;

import com.p2.models.Allegiance;



public interface AllegianceDAO {
	//create
	public void insert(Allegiance a);
	//read
	public List<Allegiance> selectAll();
	public List<Allegiance> selectByAllegiance(Allegiance a);
	//update
	public void update(Allegiance a);
	//delete
	public void delete(Allegiance a);
}