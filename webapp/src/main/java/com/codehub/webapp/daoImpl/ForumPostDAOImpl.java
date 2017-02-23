package com.codehub.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codehub.webapp.dao.ForumPostDAO;
import com.codehub.webapp.entity.ForumCategory;
import com.codehub.webapp.entity.ForumPosts;

@Repository("forumPostDAO")
public class ForumPostDAOImpl implements ForumPostDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<ForumPosts> list() {
		String hql = "FROM ForumPosts";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	@Transactional
	public ForumPosts getForumPosts(int id) {
		return sessionFactory.getCurrentSession().get(ForumPosts.class, id);
	}

	@Override
	@Transactional
	public boolean addForumPosts(ForumPosts forumPosts) {
		try {
			sessionFactory.getCurrentSession().save(forumPosts);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateForumPosts(ForumPosts forumPosts) {
		try {
			sessionFactory.getCurrentSession().update(forumPosts);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteForumPosts(ForumPosts forumPosts) {
		try {
			sessionFactory.getCurrentSession().delete(forumPosts);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
