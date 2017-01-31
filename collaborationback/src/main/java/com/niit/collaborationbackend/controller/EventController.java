package com.niit.collaborationbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.DAO.EventDAO;
import com.niit.collaborationbackend.model.Event;

@RestController
public class EventController {

	@Autowired
	Event event;
	
	@Autowired
	EventDAO eventDAO;
	
	//Get List Of ALL Events
	@RequestMapping("/allEvents")
	public ResponseEntity<List<Event>> getAllEvent()
	{
		List<Event> events = eventDAO.list();
		
		if(events.isEmpty())
		{
			event.setErrorCode("404");
			event.setErrorMessage("No Events Were Found");
			events.add(event);
		}
		
		return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
	}


	
	//Get Event By Id
	@RequestMapping("/eventById/{id")
	public ResponseEntity<Event> getEventByID(@PathVariable("id") String eventId)
	{
		 event = eventDAO.get(eventId);
		if(event==null)
		{
			event  = new Event();
			event.setErrorCode("404");
			event.setErrorMessage("No Events Were Found");
		}
		
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	
	
	//Save a new Event
	@RequestMapping("/saveevent")
	public ResponseEntity<Event> saveEvent(@RequestBody Event event)
	{
		if(eventDAO.save(event)==false)
		{
			event.setErrorCode("404");
			event.setErrorMessage("Event was not Created.. !! ..!! .. Please Try Again After Some time..!!..!!..");
		}
		else
		{
			event.setErrorCode("200");
			event.setErrorMessage("Thank you !!..!!..Event is Created SuccessFully");
		}
		
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	
	@RequestMapping("/updateEvent")
	public ResponseEntity<Event> updateEvent(@RequestBody Event event)
	{
		if(eventDAO.update(event)==false)
		{
			event.setErrorCode("404");
			event.setErrorMessage("Event was not Updated.. !! ..!! .. Please Try Again After Some time..!!..!!..");
		}
		
		else
		{
			event.setErrorCode("404");
			event.setErrorMessage("Thank you !!..!!..Event is updated SuccessFully");
		}
		
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	

}








