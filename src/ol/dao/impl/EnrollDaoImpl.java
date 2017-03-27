package ol.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import ol.dao.IEnrollDao;
import ol.entity.Enroll;
import ol.entity.LeanQueryModel;

@Repository
public class EnrollDaoImpl extends HibernateSupport implements IEnrollDao {

	@Override
	public void saveEnroll(Enroll e) {
		this.getHibernateTemplate().saveOrUpdate(e);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Enroll> findEnroll(int eId,int userId) {
	    return  this.getHibernateTemplate().find("from Enroll AS e where e.courese.coureseId = ? and e.user.userId=?", eId, userId);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Enroll> findCoureseEnroll(final int pid, int start, int end) {
		return (List<Enroll>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(Enroll.class).add(Restrictions.eq("enrollId", pid)).list().size();
			}
		});
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Enroll> findEnrollByUserId(final int uid,final LeanQueryModel condition) {
		return (List<Enroll>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createCriteria(Enroll.class)
						.add(Restrictions.eq("user.userId", uid))
						.setFirstResult(condition.getFirstResult())
						.setMaxResults(condition.getMaxResutl()).list();
			}
			
		});
	}

	@Override
	public Long findCount(LeanQueryModel condition) {
		return Long.valueOf((Integer) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createCriteria(Enroll.class).list().size();
			}
			
		}));
	}

}
