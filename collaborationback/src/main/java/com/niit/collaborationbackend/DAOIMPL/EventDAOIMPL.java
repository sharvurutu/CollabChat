package com.niit.collaborationbackend.DAOIMPL;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.DAO.EventDAO;
import com.niit.collaborationbackend.model.Event;

@Repository("eventDAO")
public class EventDAOIMPL implements EventDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public EventDAOIMPL(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public boolean save(Event event) {
	try {
		sessionFactory.getCurrentSession().save(event);
		return true;
	} catch (HibernateException e) {
		e.printStackTrace();
		return false;
	}
	}

	public boolean delete(Event event) {
		try {
			sessionFactory.getCurrentSession().delete(event);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Event event) {
		try {
			sessionFactory.getCurrentSession().update(event);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Event get(String id) {
		return (Event) sessionFactory.getCurrentSession().get(Event.class, id);
	}

	public List<Event> list() {
		String hql= "From Event";
		Query query= sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	

}
