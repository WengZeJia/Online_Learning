package ol.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ol.dao.ICoureseDao;
import ol.dao.IEnrollDao;
import ol.entity.Courese;
import ol.entity.Enroll;

@Repository
public class EnrollDaoImpl extends HibernateSupport implements IEnrollDao {

	@Override
	public void saveEnroll(Enroll e) {
		this.getHibernateTemplate().saveOrUpdate(e);
	}

	@Override
	public void updateEnroll(Enroll e) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enroll> findEnroll(int eId,int userId) {
	    return  this.getHibernateTemplate().find("from Enroll AS e where e.courese.coureseId = ? and e.user.userId=?", eId, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enroll> findUsersEnroll(int uid, int start, int end) {
		return this.getHibernateTemplate().find("from Enroll AS e where e.user.userId = ?",uid);
	}

	@Override
	public Integer findUsersEnrollCount(int udi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enroll> findCoureseEnroll(int pid, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findCoureseEnrollCount(int pid) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enroll> userEnroll(int uid) {
		return this.getHibernateTemplate().find("from Enroll AS e where e.user.userId = ?",uid);
	}


}
