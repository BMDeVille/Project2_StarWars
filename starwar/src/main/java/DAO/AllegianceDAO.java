package DAO;

import java.util.List;

import models.Allegiance;



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