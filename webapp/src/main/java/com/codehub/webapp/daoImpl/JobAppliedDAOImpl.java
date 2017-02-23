package com.codehub.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codehub.webapp.dao.JobAppliedDAO;
import com.codehub.webapp.entity.JobApplied;
import com.codehub.webapp.entity.User;

@Repository("jobAppliedDAO")
public class JobAppliedDAOImpl implements JobAppliedDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<JobApplied> list() {
		String hql = "FROM JobApplied";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	@Transactional
	public JobApplied getJobApplied(int id) {
		return sessionFactory.getCurrentSession().get(JobApplied.class, id);
	}

	@Override
	@Transactional
	public boolean addJobApplied(JobApplied jobApplied) {
		try {
			sessionFactory.getCurrentSession().save(jobApplied);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateJobApplied(JobApplied jobApplied) {
		try {
			sessionFactory.getCurrentSession().update(jobApplied);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteJobApplied(JobApplied jobApplied) {
		try {
			sessionFactory.getCurrentSession().delete(jobApplied);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
