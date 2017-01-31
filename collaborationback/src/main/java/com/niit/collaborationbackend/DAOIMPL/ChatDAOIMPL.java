package com.niit.collaborationbackend.DAOIMPL;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.DAO.ChatDAO;
import com.niit.collaborationbackend.model.Chat;

@Repository("chatDAO")
public class ChatDAOIMPL implements ChatDAO {

	@Autowired
	SessionFactory sessionFactory;

	public ChatDAOIMPL(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean save(Chat chat) {
		try {
			sessionFactory.getCurrentSession().save(chat);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Chat get(String id) {
		return (Chat) sessionFactory.getCurrentSession().get(Chat.class, id);
	}

	public boolean Delete(Chat chat) {
		try {
			sessionFactory.getCurrentSession().delete(chat);
			return false;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Chat> list() {
		String hql ="From Chat";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}
