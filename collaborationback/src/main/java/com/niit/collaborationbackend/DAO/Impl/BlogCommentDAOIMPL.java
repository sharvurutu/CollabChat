package com.niit.collaborationbackend.DAO.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.collaborationbackend.DAO.BlogCommentsDAO;
import com.niit.collaborationbackend.model.BlogComments;

@Repository("blogCommentDAOIMPL")
public class BlogCommentDAOIMPL implements BlogCommentsDAO {
	
		@Autowired
		SessionFactory sessionFactory;

	public BlogCommentDAOIMPL(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	
	@Transactional
	public boolean postComment(BlogComments blogComments) {
		try {
			sessionFactory.getCurrentSession().save(blogComments);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;

		}
		return true;

		
	}
	
	@Transactional
	public Integer maxID()
	{
		Integer maxId = 100;
		try {
			String hql = "Select max(id) from BlogComments";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			maxId= (Integer) query.uniqueResult();
		} catch (HibernateException e) {
			maxId= 100;
			e.printStackTrace();
		}
		return maxId+1;
	}

@Transactional
	public List<BlogComments> allBlogComments() {
		String hql="FROM BlogComments";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
			}

@Transactional
public List<BlogComments> blogComment(int blogId) {
	String hql="FROM BlogComments where blogId ='"+blogId+"'";
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	return query.list();
	
}


}
