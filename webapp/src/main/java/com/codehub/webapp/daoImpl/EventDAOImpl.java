package com.codehub.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codehub.webapp.dao.EventsDAO;
import com.codehub.webapp.entity.Events;

@Repository("eventsDAO")
public class EventDAOImpl implements EventsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Events> list() {
		String hql = "FROM Events";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	@Transactional
	public Events getEvent(int id) {
		return sessionFactory.getCurrentSession().get(Events.class, id);
	}

	@Override
	@Transactional
	public boolean addEvent(Events event) {
		try {
			sessionFactory.getCurrentSession().save(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateEvent(Events event) {
		try {
			sessionFactory.getCurrentSession().update(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteEvent(Events event) {
		try {
			sessionFactory.getCurrentSession().delete(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}