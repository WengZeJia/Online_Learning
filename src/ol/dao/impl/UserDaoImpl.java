package ol.dao.impl;

import java.sql.SQLException;
import java.util.List;

import ol.dao.IUserDao;
import ol.entity.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends HibernateSupport implements IUserDao {

	@Override
	public void saveUser(User user) {
		this.getHibernateTemplate().saveOrUpdate(user);
	}

	@Override
	public void updateUser(User user) {
		this.getHibernateTemplate().update(user);
	}

	@Override
	public void delete(int uid) {
		this.getHibernateTemplate().delete(findUser(uid));
	}

	@Override
	public User findUserByUsernameAndPwd(final String username, final String pwd) {
		return (User) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(User.class).add(Restrictions.eq("userName", username)).add(Restrictions.eq("pwd", pwd)).uniqueResult();
			}
			
		});
	}

	@Override
	public List<User> findUserByUsername(final String username) {
		return (List<User>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(User.class).add(Restrictions.like("realName", "%"+username+"%")).list();
			}
			
		});
	}

	@Override
	public User findUser(int uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}

	@Override
	public List<User> findAllUsers() {
		return this.getHibernateTemplate().find("from User");
	}

}
