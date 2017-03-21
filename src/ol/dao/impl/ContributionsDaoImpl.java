package ol.dao.impl;

import java.sql.SQLException;
import java.util.List;

import ol.dao.IContributionsDao;
import ol.entity.Contributions;
import ol.entity.LeanQueryModel;
import ol.entity.Periodical;
import ol.entity.SolicitContributions;
import ol.entity.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class ContributionsDaoImpl extends HibernateSupport implements IContributionsDao {

	@Override
	public void saveContributions(Contributions ctr) {
		this.getHibernateTemplate().save(ctr);
	}

	@Override
	public void updateContributions(Contributions ctr) {
		this.getHibernateTemplate().update(ctr);
	}

	@Override
	public Contributions findContributions(int cid) {
		return this.getHibernateTemplate().get(Contributions.class, cid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contributions> findUsersContributions(final int uid, final int start, final	int end) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				User user = (User) session.get(User.class, uid);
				List<Contributions> list = session.createCriteria(Contributions.class).add(Restrictions.eq("user", user)).addOrder(Order.desc("publishTime")).setFirstResult(start).setMaxResults(end).list();
				return list;
			}
			
		});
	}

	@Override
	public Integer findUsersContributionsCount(final int uid) {
		return (Integer) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				User user = (User) session.get(User.class, uid);
				List<Contributions> list = session.createCriteria(Contributions.class).add(Restrictions.eq("user", user)).list();
				return list.size();
			}
			
		}); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contributions> findPeriodicalsContributions(final int pid, final int start,	final int end) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Periodical p = (Periodical) session.get(Periodical.class, pid);
				List<SolicitContributions> scs = p.getSolicitContributionses();
				List<Contributions> list = session.createCriteria(Contributions.class).add(Restrictions.in("solicitContributions", scs)).addOrder(Order.desc("publishTime")).setFirstResult(start).setMaxResults(end).list();
				return list;
			}
			
		});
	}

	@Override
	public Integer findPeriodicalsContributionsCount(final int pid) {
		return (Integer) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Periodical p = (Periodical) session.get(Periodical.class, pid);
				List<SolicitContributions> scs = p.getSolicitContributionses();
				List<Contributions> list = session.createCriteria(Contributions.class).add(Restrictions.in("solicitContributions", scs)).list();
				return list.size();
			}
			
		});
	}

	@Override
	public void saveSolicitContributions(SolicitContributions sc) {
		this.getHibernateTemplate().save(sc);
	}

	@Override
	public void updateSolicitContributions(SolicitContributions sc) {
		this.getHibernateTemplate().update(sc);
	}

	@Override
	public void deleteSolicitContributions(int scid) {
		this.getHibernateTemplate().delete(this.findSolicitContributions(scid));
	}

	@Override
	public SolicitContributions findSolicitContributions(int scid) {
		return this.getHibernateTemplate().get(SolicitContributions.class, scid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitContributions> findAllSolicitContributions(final int start, final int end) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(SolicitContributions.class).addOrder(Order.desc("publishTime")).setFirstResult(start).setMaxResults(end).list();
			}
			
		});
	}

	@Override
	public Integer findAllSolicitContributionsCount() {
		return (Integer) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(SolicitContributions.class).list().size();
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitContributions> findAllMySolicitContributions(final int pid, final int start, final int end) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Periodical p = (Periodical) session.get(Periodical.class, pid);
				return session.createCriteria(SolicitContributions.class).add(Restrictions.eq("periodical", p)).addOrder(Order.desc("publishTime")).setFirstResult(start).setMaxResults(end).list();
			}
			
		});
	}

	@Override
	public Integer findAllMySolicitContributionsCount(final int pid) {
		return (Integer) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Periodical p = (Periodical) session.get(Periodical.class, pid);
				return session.createCriteria(SolicitContributions.class).add(Restrictions.eq("periodical", p)).list().size();
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitContributions> searchPosition(final LeanQueryModel condition) {
		return (List<SolicitContributions>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createCriteria(SolicitContributions.class)
						.add(Restrictions.like("title", "%"+ condition.getKeyword() + "%"))
						.setFirstResult(condition.getFirstResult())
						.setMaxResults(condition.getMaxResutl()).list();
			}
			
		});
	}
}
