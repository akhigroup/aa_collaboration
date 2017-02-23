package com.codehub.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codehub.webapp.dao.BlogCommentDAO;
import com.codehub.webapp.entity.BlogComments;

@Repository("blogCommentDAO")
public class BlogCommentDAOImpl implements BlogCommentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<BlogComments> list() {
		String hql = "FROM BlogComments";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	@Transactional
	public BlogComments getBlogComments(int id) {
		return sessionFactory.getCurrentSession().get(BlogComments.class, id);
	}

	@Override
	@Transactional
	public boolean addBlogComments(BlogComments blogComments) {
		try {
			sessionFactory.getCurrentSession().save(blogComments);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateBlogComments(BlogComments blogComments) {
		try {
			sessionFactory.getCurrentSession().update(blogComments);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteBlogComments(BlogComments blogComments) {
		try {
			sessionFactory.getCurrentSession().delete(blogComments);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
