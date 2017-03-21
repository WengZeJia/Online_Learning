package ol.dao.impl;

import java.sql.SQLException;
import java.util.List;

import ol.dao.IPeriodicalDao;
import ol.entity.Periodical;
import ol.entity.PeriodicalType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class PeriodicalDaoImpl extends HibernateSupport implements IPeriodicalDao {

	@Override
	public void savePeriodical(Periodical p) {
		this.getHibernateTemplate().save(p);
	}

	@Override
	public void updatePeriodical(Periodical p) {
		this.getHibernateTemplate().update(p);
	}

	@Override
	public void deletePeriodical(int pid) {
		this.getHibernateTemplate().delete(this.findPeriodical(pid));
	}

	@Override
	public Periodical findPeriodical(int pid) {
		return this.getHibernateTemplate().get(Periodical.class, pid);
	}

	@Override
	public Periodical findPeriodical(final String username, final String pwd) {
		return (Periodical) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(Periodical.class).add(Restrictions.eq("admin", username)).add(Restrictions.eq("pwd", pwd)).uniqueResult();
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Periodical> findAllPeriodicals(final int start, final int end) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(Periodical.class).add(Restrictions.eq("status", 0)).setFirstResult(start).setMaxResults(end).list();
			}
			
		});
	}

	@Override
	public Integer findAllPeriodicalsCount() {
		return (Integer) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(Periodical.class).add(Restrictions.eq("status", 0)).list().size();
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Periodical> findHotPeriodicals() {
		return this.getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(Periodical.class).add(Restrictions.eq("status", 0)).addOrder(Order.desc("periodicalId")).setFirstResult(0).setMaxResults(8).list();
			}
			
		});
	}

	@Override
	public List<PeriodicalType> findAllPeriodicalTypes() {
		return this.getHibernateTemplate().find("from PeriodicalType");
	}

	@Override
	public void savePeriodicalType(PeriodicalType type) {
		this.getHibernateTemplate().save(type);
	}

	@Override
	public void updatePeriodicalType(PeriodicalType type) {
		this.getHibernateTemplate().update(type);
	}

	@Override
	public PeriodicalType findPeriodicalType(int ptid) {
		return this.getHibernateTemplate().get(PeriodicalType.class, ptid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Periodical> findAllPeriodicalsByType(final int type, final int start,
			final int end) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				PeriodicalType pt = (PeriodicalType) session.get(PeriodicalType.class, type);
				return session.createCriteria(Periodical.class).add(Restrictions.eq("periodicalType", pt)).setFirstResult(start).setMaxResults(end).list();
			}
			
		});
	}

	@Override
	public Integer findAllPeriodicalsByTypeCount(final int type) {
		return (Integer) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				PeriodicalType pt = (PeriodicalType) session.get(PeriodicalType.class, type);
				return session.createCriteria(Periodical.class).add(Restrictions.eq("periodicalType", pt)).list().size();
			}
			
		});
	}

}
