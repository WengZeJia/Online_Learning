package ol.dao;

import java.util.List;






import ol.entity.Courese;
import ol.entity.LeanQueryModel;

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
	
	List<Courese> findAllCoureses(Integer userid);
	
	
	
	Integer findAllCouresesCountByModel(String cname, int type, int start, int end);
	
	/**
	 * 按条件返回
	 * @param page
	 * @return
	 */
	public List<Courese> searchCourese(LeanQueryModel condition);
	/**
	 * 按条件返回总条数
	 * @param page
	 * @return
	 */
	public Long findCount(LeanQueryModel condition);
	public List<Courese> findAll();
	
	List<Courese> queryAllCourse();
}
