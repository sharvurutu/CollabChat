package com.niit.collaborationbackend.DAO.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.DAO.UserDAO;
import com.niit.collaborationbackend.model.User;

@Repository("userDAO")
public class UserDAOIMPL implements UserDAO {
	
	
		@Autowired
		SessionFactory sessionFactory;
		
		public UserDAOIMPL(SessionFactory sessionFactory)
		{
			this.sessionFactory = sessionFactory;
		}
		
		@Transactional
		public boolean save(User user) {
			
			try
			{
			sessionFactory.getCurrentSession().save(user);
			return true;
			}
			
			catch(Exception e)
			{
			e.printStackTrace();	
					return false;
		}
		}
		
			
		public boolean delete(User user) {
			try
			{
			sessionFactory.getCurrentSession().delete(user);
			return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return  false;
			}
			}
		
		@Transactional
		public boolean update(User user) {
			try
			{
				sessionFactory.getCurrentSession().update(user);
				return true;
			}
			catch(Exception e)
			{
				
			e.printStackTrace();
			return false;
		}
		}
		
		@Transactional
		public User get(String username) {
				return (User) sessionFactory.getCurrentSession().get(User.class, username) ;
			
		}
		
		@Transactional
		public List<User> getall(String username) {
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery
			("select username from user_details26 where username in (select username from user_details26 where username != ? minus ("
					+ "select friendUserName from friend26 where username = ? union select username from friend26 where friendUserName= ?))");
			query.setString(0, username);
			query.setString(1, username);
			query.setString(2, username);
			List<User> users = query.list();
			return users;
		}
  
		@Transactional
		public List<User> list() {
			String hql = "FROM User";
			Query query =  sessionFactory.getCurrentSession().createQuery(hql);
			return query.list();
		}

	@Transactional
		public User IsValidUser(String username, String password) {
		try{
			String hql = "FROM User o where o.username= :username and o.password= :password";
			Query st  = sessionFactory.getCurrentSession().createQuery(hql);
			st.setString("username", username);
			st.setString("password", password);
		return (User) st.uniqueResult();
		
		}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
	
	
	
	@Transactional
	public void setOnline(String username)
	{
		String hql = "UPDATE User SET isOnline = 'Y' where username= '"+username+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	
	@Transactional
	public void setOffline(String username)
	{
		String hql = "UPDATE User SET isOnline = 'N' where username= '"+username+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	}



