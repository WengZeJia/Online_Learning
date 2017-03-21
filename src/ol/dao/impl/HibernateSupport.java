package ol.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateSupport extends HibernateDaoSupport {
	@Autowired
	public void setSessionFactoryOverride(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
}
