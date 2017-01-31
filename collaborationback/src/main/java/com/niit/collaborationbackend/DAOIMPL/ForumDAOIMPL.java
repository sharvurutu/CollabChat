package com.niit.collaborationbackend.DAOIMPL;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.DAO.ForumDAO;
import com.niit.collaborationbackend.model.Forum;

@Repository("forumDAO")
public class ForumDAOIMPL implements ForumDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	public ForumDAOIMPL(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public boolean save(Forum forum) {
	try {
		sessionFactory.getCurrentSession().save(forum);
		return true;
	} catch (HibernateException e) {
		e.printStackTrace();
		return false;
	}
	}

	public boolean delete(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Forum get(String id) {
		return (Forum) sessionFactory.getCurrentSession().get(Forum.class, id);
	}

	public List<Forum> list() {
		String hql= "From Forum";
		Query query= sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	

}
