package ol.dao;

import java.util.List;

import ol.entity.Enroll;

public interface IEnrollDao {

	
	void saveEnroll(Enroll e);
	
	void updateEnroll(Enroll e);
	
	Enroll findEnroll(int eId);
	
	/**
	 * 分页查找用户的报名记录
	 * @param uid
	 * @param start
	 * @param end
	 * @return
	 */
	List<Enroll> findUsersEnroll(int uid, int start, int end);
	
	/**
	 * 某个用户的所有投稿总记录数
	 * @param udi
	 * @return
	 */
	Integer findUsersEnrollCount(int udi);
	
	/**
	 * 分页查找课程收到的报名记录
	 * @param pid
	 * @param start
	 * @param end
	 * @return
	 */
	List<Enroll> findCoureseEnroll(int pid, int start, int end);
	
	Integer findCoureseEnrollCount(int pid);
	/*Enroll findSolicitContributions(int scid);*/
}
