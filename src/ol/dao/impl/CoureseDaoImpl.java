package ol.dao.impl;

import java.sql.SQLException;
import java.util.List;

import ol.dao.ICoureseDao;
import ol.entity.Courese;
import ol.entity.LeanQueryModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;





@Repository
public class CoureseDaoImpl extends HibernateSupport implements ICoureseDao {
	
	@Override
	public void saveCourese(Courese c) {
		this.getHibernateTemplate().saveOrUpdate(c);
	}

	@Override
	public void updateCourese(Courese c) {
		this.getHibernateTemplate().update(c);
	}

	@Override
	public void deleteCourese(int pid) {
		this.getHibernateTemplate().delete(findCourese(pid));
	}

	@Override
	public Courese findCourese(int pid) {
		return this.getHibernateTemplate().get(Courese.class, pid);
	}

	@Override
	public Integer findAllCouresesCountByModel(String cname, int type,
			int start, int end) {
		return (Integer) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(Courese.class).list().size();
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Courese> findAllCoureses(Integer userid) {
		return this.getHibernateTemplate().find("from Courese");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Courese> searchCourese(final LeanQueryModel condition) {
		return (List<Courese>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createCriteria(Courese.class)
						.add(Restrictions.like("cName", "%"+ condition.getKeyword() + "%"))
						.setFirstResult(condition.getFirstResult())
						.setMaxResults(condition.getMaxResutl()).list();
			}
			
		});
	}

	@Override
	public Long findCount(final LeanQueryModel condition) {
		return Long.valueOf((Integer) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createCriteria(Courese.class)
						.add(Restrictions.like("cName", "%"+ condition.getKeyword() + "%")).list().size();
			}
			
		}));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Courese> findAll() {
		return (List<Courese>)this.getHibernateTemplate().find("from Courese");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Courese> queryAllCourse() {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Courese as c")
		   .append(" left join fetch c.user")
		   .append(" left join fetch c.enrolls as e")
		   .append(" left join fetch e.user")
		   .append(" order by c.releaseTime desc");
		return this.getHibernateTemplate().find(hql.toString());
	}

}
