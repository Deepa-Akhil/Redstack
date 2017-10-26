package com.enterprise.dataservices.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enterprise.common.pojo.UserSecurityBean;
import com.enterprise.dataservices.SecurityDataService;
import com.enterprise.domain.entity.User;

@Repository
public class SecurityDataServiceImpl extends AbstractSecurityDataServiceImpl implements SecurityDataService {
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserSecurityBean authenticate(UserSecurityBean input, String sessionId) {
		return input;
	}
	
	public User searchDatabase(String username)  throws Exception{
		User user = null;
		Session session = sessionFactory.openSession();
		try {
			 Query query = session.createQuery("from User where username = :username");
	            query.setParameter("username", username);
	            if(query.list().size() > 0){
	            	user = (User)query.list().get(0);	            	
	            }
	        if(null!=user)  
	    	   user = (User)session.load(User.class, user.getId());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
            session.close();
        }
		return user;
	}
}