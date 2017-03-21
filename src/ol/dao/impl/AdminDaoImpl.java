package ol.dao.impl;

import java.sql.SQLException;

import ol.dao.IAdminDao;
import ol.entity.Admin;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl extends HibernateSupport implements IAdminDao {

	@Override
	public Admin findAdmin(final String username, final String pwd) {
		return (Admin) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(Admin.class).add(Restrictions.eq("username", username)).add(Restrictions.eq("pwd", pwd)).uniqueResult();
			}
			
		});
	}

}
