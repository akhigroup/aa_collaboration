package com.codehub.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codehub.webapp.dao.FriendsDAO;
import com.codehub.webapp.entity.Blog;
import com.codehub.webapp.entity.Friends;

@Repository("friendsDAO")
@Transactional
public class FriendsDAOImpl implements FriendsDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Friends> list() {
		String hql = "FROM Friends";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<Friends> list(int id) {
		String hql = "FROM Friends where friendId = '" + id +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<Friends> list(String status) {
		String hql = "FROM Friends where status = '" + status +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Friends getFriend(int id) {
		return sessionFactory.getCurrentSession().get(Friends.class, id);
	}

	@Override
	public boolean addFriend(Friends friends) {
		try {
			sessionFactory.getCurrentSession().save(friends);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateFriend(Friends friends) {
		try {
			sessionFactory.getCurrentSession().update(friends);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteFriend(Friends friends) {
		try {
			sessionFactory.getCurrentSession().delete(friends);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	
	

}
