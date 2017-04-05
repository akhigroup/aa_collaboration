package com.codehub.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codehub.webapp.dao.JobDAO;
import com.codehub.webapp.entity.Job;
import com.codehub.webapp.entity.User;

@Repository("jobDAO")
public class JobDAOImpl implements JobDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Job> list() {
		String hql = "FROM Job";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	@Transactional
	public List<Job> getJobsByStatus(String status) {
		String hql = "FROM Job where status = '" + status +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	@Transactional
	public List<Job> getUserJobs(int id) {
		String hql = "FROM Job where USER_ID = '" + id +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	@Transactional
	public Job getJob(int id) {
		return sessionFactory.getCurrentSession().get(Job.class, id);
	}

	@Override
	@Transactional
	public boolean addJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

}
