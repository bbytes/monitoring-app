package com.bbytes.dao;


import java.util.ArrayList;
import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




import com.bbytes.entity.UserEntity;


@Repository
@Transactional
public class UserLoginDaoImpl implements UserLoginDao {

	@Autowired
	 private SessionFactory sessionFactory;
	
	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}
	@SuppressWarnings("unchecked")
	public UserEntity findByEmail(String email){
		List<UserEntity> userList = new ArrayList<UserEntity>();
		String sqlquery = "from UserEntity where EMAIL =:email";
		Query query = openSession().createQuery(sqlquery);
		query.setParameter("email", email);
		userList = query.list();
		if (userList.size() > 0)
		{
			return userList.get(0);
		}
		    
		else
		{
			return null;
		}
		
	}
	


}
