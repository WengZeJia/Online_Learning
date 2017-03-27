package ol.dao;

import java.util.List;

import ol.entity.Enroll;
import ol.entity.LeanQueryModel;

public interface IEnrollDao {

	
	void saveEnroll(Enroll e);
	
	
	List<Enroll> findEnroll(int eId,int useId);
	/**
	 * 分页查找用户的报名记录
	 */
	List<Enroll> findEnrollByUserId(int uid,LeanQueryModel condition);
	
	
	/**
	 * 分页查找课程收到的报名记录
	 */
	List<Enroll> findCoureseEnroll(int pid, int start, int end);

	public Long findCount(LeanQueryModel condition);
}
