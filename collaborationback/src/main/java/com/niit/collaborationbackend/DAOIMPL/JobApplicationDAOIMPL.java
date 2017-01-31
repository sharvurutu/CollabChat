package com.niit.collaborationbackend.DAOIMPL;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.DAO.JobApplicationDAO;
import com.niit.collaborationbackend.model.Job;
import com.niit.collaborationbackend.model.JobApplication;

@Repository("jobApplicationDAO")
public class JobApplicationDAOIMPL implements JobApplicationDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	public JobApplicationDAOIMPL(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	
	@Transactional
	public JobApplication getJobApplication(String Id) {
		return (JobApplication) sessionFactory.getCurrentSession().get(JobApplication.class, Id);
	}

	
	@Transactional
	public List<JobApplication> list() {
		String hql= "From JobApplication";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	
	@Transactional
	public boolean saveJobApplication(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().save(jobApplication);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	
	@Transactional
	public boolean updateJobApplication(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().update(jobApplication);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	@Transactional
	public JobApplication getJobApplication(String username, String jobId) {
		String hql = "FROM JobApplication where username='"+username+"' and JobId = '"+jobId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (JobApplication) query.uniqueResult();
	}
	
	@Transactional
	public List<JobApplication> myAppliedJob(String username) 
	{
		String hql = "From JobApplication where username = '"+username+"'";
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list()	;
	}
	
	@Transactional
	public Integer maxID()
	{
		Integer maxId = 100;
		try {
			String hql = "Select max(id) from JobApplication";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			maxId= (Integer) query.uniqueResult();
		} catch (HibernateException e) {
			maxId= 100;
			e.printStackTrace();
		}
		return maxId+1;
	}

}
