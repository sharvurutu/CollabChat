package com.niit.collaborationbackend.DAOIMPL;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.DAO.BlogDAO;
import com.niit.collaborationbackend.model.Blog;

@Repository("blogDAO")
public class BlogDAOIMPL implements BlogDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public BlogDAOIMPL(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public boolean save(Blog blog) {

		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	public Blog get(int id) {
		return (Blog) sessionFactory.getCurrentSession().get(Blog.class, id);
	}

	@Transactional
	public boolean delete(Blog blog) {
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean update(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public List<Blog> list() {
		String hql="FROM Blog";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
		
			}
	
	@Transactional
	public Integer maxID()
	{
		Integer maxId = 100;
		try {
			String hql = "Select max(id) from Blog";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			maxId= (Integer) query.uniqueResult();
		} catch (HibernateException e) {
			maxId= 100;
			e.printStackTrace();
		}
		return maxId+1;
	}

	@Transactional
	public Blog getById(int id) {
		return (Blog) sessionFactory.getCurrentSession().get(Blog.class, id);
	}
	
}
