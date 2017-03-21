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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEnroll(Enroll e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enroll findEnroll(int eId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enroll> findUsersEnroll(int uid, int start, int end) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
