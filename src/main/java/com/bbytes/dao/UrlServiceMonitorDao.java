package com.bbytes.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.bbytes.entity.ServiceMonitorEntity;
import com.bbytes.entity.UrlServiceMonitorEntity;

@Repository
@Transactional
public class UrlServiceMonitorDao implements ServiceMonitorDao{

	@Autowired
	 private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<ServiceMonitorEntity> save(@RequestBody ServiceMonitorEntity urlServiceMonitorEntity) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UrlServiceMonitorEntity.class);
		urlServiceMonitorEntity.setIsactive(false);
	
		sessionFactory.getCurrentSession().saveOrUpdate(urlServiceMonitorEntity);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceMonitorEntity> getAllServices(long userid) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UrlServiceMonitorEntity.class);
		criteria.add(Restrictions.like("userid", userid));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceMonitorEntity> getServiceById(long id, String services_name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UrlServiceMonitorEntity.class);
		criteria.add(Restrictions.like("services_name", services_name));
		criteria.add(Restrictions.like("id", id));
		return criteria.list();
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<ServiceMonitorEntity> isActive(long id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UrlServiceMonitorEntity.class);
		criteria.add(Restrictions.like("id", id));
		return criteria.list();
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceMonitorEntity> saveActive(long id, boolean isactive) {
		Criteria criteria  = sessionFactory.getCurrentSession().createCriteria(UrlServiceMonitorEntity.class);
		criteria.add(Restrictions.eq("id", id)).add(Restrictions.like("isactive",isactive ));
		UrlServiceMonitorEntity urlServiceMonitorEntity = (UrlServiceMonitorEntity) criteria.uniqueResult();
		urlServiceMonitorEntity.setIsactive(true);
		sessionFactory.getCurrentSession().saveOrUpdate(urlServiceMonitorEntity);
		return criteria.list();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceMonitorEntity> stopActive(long id, boolean isactive) {
		Criteria criteria  = sessionFactory.getCurrentSession().createCriteria(UrlServiceMonitorEntity.class);
		criteria.add(Restrictions.eq("id", id)).add(Restrictions.like("isactive",isactive ));
		UrlServiceMonitorEntity urlServiceMonitorEntity = (UrlServiceMonitorEntity) criteria.uniqueResult();
		urlServiceMonitorEntity.setIsactive(false);
		sessionFactory.getCurrentSession().saveOrUpdate(urlServiceMonitorEntity);
		return criteria.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceMonitorEntity> getAllServices() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UrlServiceMonitorEntity.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceMonitorEntity> deleteService(long id, String services_name) {
		Criteria criteria  = sessionFactory.getCurrentSession().createCriteria(UrlServiceMonitorEntity.class);
		criteria.add(Restrictions.eq("id", id)).add(Restrictions.like("services_name",services_name ));
		UrlServiceMonitorEntity urlServiceMonitorEntity = (UrlServiceMonitorEntity) criteria.uniqueResult();
		sessionFactory.getCurrentSession().delete(urlServiceMonitorEntity);
		return criteria.list();
	}

	
}
