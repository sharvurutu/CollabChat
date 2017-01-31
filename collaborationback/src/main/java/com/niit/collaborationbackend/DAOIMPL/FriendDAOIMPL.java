package com.niit.collaborationbackend.DAOIMPL;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.DAO.FriendDAO;
import com.niit.collaborationbackend.model.Friend;

@Repository("friendDAO")
public class FriendDAOIMPL implements FriendDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public FriendDAOIMPL(SessionFactory sessionFactory)
	{
		this.sessionFactory =sessionFactory;
	}
	

	@Transactional
	public boolean save(Friend friend) {
		try {
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(Friend friend) {
		try {
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public List<Friend> getMyFriendRequests(String username) {
/*		String hql = "select username From Friend where friendUserName= '"+username+"' and status ='N'";
*/
		String hql = "From Friend where friendUserName= '"+username+"' and status ='N'";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	
	@Transactional
	public List<Friend> getMySentFriendRequest(String emailId) {
		String hql = "select friendUserName From Friend where username= '"+emailId+"' and status ='N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	
	@Transactional
	public void setOnline(String username)
	{
		String hql = "UPDATE Friend SET isOnline = 'Y' where username= '"+username+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	
	@Transactional
	public void setOffline(String username)
	{
		String hql = "UPDATE Friend SET isOnline = 'N' where username= '"+username+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	
	
	@Transactional
	public Friend get(String username, String friendUserName) {
		String hql = "From Friend where username = '" +username+ "' and friendUserName = '" +friendUserName+ "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (Friend) query.uniqueResult();
	}


	@Transactional
	public boolean update(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Transactional
	public List<Friend> getMyFriends(String username) {
		String hql1 = "Select friendUserName as FN from Friend where username = '"+ username + "' and status ='A'";
			//	+ "UNION +"
		
/*		String hql1 = "From Friend where username = '"+ username + "' and status ='A'";
*/
				String hql2 = "Select username as FN From Friend Where friendUserName = '" +username+ "' and status = 'A'";
						//+ "MINUS"
						//+ "From Friend where emaild = '"+emailId+"'";
		
/*		String hql2 = "From Friend Where friendUserName = '" +username+ "' and status = 'A'";
*/
		
		Query query1 = sessionFactory.getCurrentSession().createQuery(hql1);
		Query query2 = sessionFactory.getCurrentSession().createQuery(hql2);

		List<Friend> myFriends1 = query1.list();
		List<Friend> myFriends2 = query2.list();
		

		myFriends1.addAll(myFriends2);
		return myFriends1;
	}
	
	@Transactional
	public Integer maxID()
	{
		Integer maxId = 100;
		String hql = "Select max(id) from Friend";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		try {
			maxId= (Integer) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			return maxId;
		}
		return maxId+1;
	}

}
