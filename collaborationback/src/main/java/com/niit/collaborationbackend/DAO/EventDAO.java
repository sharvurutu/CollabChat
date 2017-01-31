package com.niit.collaborationbackend.DAO;

import java.util.List;

import com.niit.collaborationbackend.model.Event;

public interface EventDAO {
	
   public boolean save(Event event);
	
	public boolean delete(Event event);
	
	public boolean update(Event event);
	
	public Event get(String id);
	
	public List<Event> list();

}
