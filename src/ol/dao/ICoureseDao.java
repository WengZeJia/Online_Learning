package ol.dao;

import java.util.List;

import ol.entity.Courese;

/**
 * 课程数据库操作
 * @author admin
 *
 */
public interface ICoureseDao {

	void saveCourese(Courese c);
	
	void updateCourese(Courese c);
	
	void deleteCourese(int pid);
	
	Courese findCourese(int pid);
	
	List<Courese> findAllCoureses(int start, int end);
	
	Integer findAllCouresesCount();
	
	List<Courese> findAllCouresesByModel(String cname, int type, int start, int end);
	
	Integer findAllCouresesCountByModel(String cname, int type, int start, int end);
	
	List<Courese> findHotCoureses();
}
