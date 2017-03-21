package ol.dao.impl;

import java.util.List;

import ol.dao.ICoureseDao;
import ol.entity.Courese;

import org.springframework.stereotype.Repository;

@Repository
public class CoureseDaoImpl extends HibernateSupport implements ICoureseDao {

	@Override
	public void saveCourese(Courese c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCourese(Courese c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCourese(int pid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Courese findCourese(int pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Courese> findAllCoureses(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findAllCouresesCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Courese> findAllCouresesByModel(String cname, int type,
			int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findAllCouresesCountByModel(String cname, int type,
			int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Courese> findHotCoureses() {
		// TODO Auto-generated method stub
		return null;
	}


}
