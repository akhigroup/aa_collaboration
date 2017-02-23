package com.codehub.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codehub.webapp.dao.ForumDAO;
import com.codehub.webapp.entity.Forum;

@Repository("forumDAO")
public class ForumDAOImpl implements ForumDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Forum> list() {
		String hql = "FROM Forum";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	@Transactional
	public Forum getForum(int id) {
		return sessionFactory.getCurrentSession().get(Forum.class, id);
	}

	@Override
	@Transactional
	public boolean addForum(Forum forum) {
			try {
				sessionFactory.getCurrentSession().save(forum);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}

	@Override
	@Transactional
	public boolean updateForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
