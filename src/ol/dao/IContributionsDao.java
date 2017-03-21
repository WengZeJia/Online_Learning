package ol.dao;

import java.util.List;

import ol.entity.Contributions;
import ol.entity.LeanQueryModel;
import ol.entity.SolicitContributions;

public interface IContributionsDao {

	//投稿表信息DAO
	void saveContributions(Contributions ctr);
	
	void updateContributions(Contributions ctr);
	
	Contributions findContributions(int cid);
	
	/**
	 * 分页查找用户的投稿记录
	 * @param uid
	 * @param start
	 * @param end
	 * @return
	 */
	List<Contributions> findUsersContributions(int uid, int start, int end);
	
	/**
	 * 某个用户的所有投稿总记录数
	 * @param udi
	 * @return
	 */
	Integer findUsersContributionsCount(int udi);
	
	/**
	 * 分页查找刊物收到的投稿记录
	 * @param pid
	 * @param start
	 * @param end
	 * @return
	 */
	List<Contributions> findPeriodicalsContributions(int pid, int start, int end);
	
	Integer findPeriodicalsContributionsCount(int pid);
	
	//征稿表信息DAO
	void saveSolicitContributions(SolicitContributions sc);
	
	void updateSolicitContributions(SolicitContributions sc);
	
	void deleteSolicitContributions(int scid);
	
	SolicitContributions findSolicitContributions(int scid);
	
	/**
	 * 按时间倒序分页查找所有征稿启示
	 * @param start
	 * @param end
	 * @return
	 */
	List<SolicitContributions> findAllSolicitContributions(int start, int end);
	
	/**
	 * 查找所有征稿启示的总记录数
	 * @return
	 */
	Integer findAllSolicitContributionsCount();
	
	/**
	 * 分页查找刊物发布的征稿启示
	 * @param pid
	 * @return
	 */
	List<SolicitContributions> findAllMySolicitContributions(int pid, int start, int end);
	
	Integer findAllMySolicitContributionsCount(int pid);
	
	/**
	 * 按条件返回
	 * @param page
	 * @return
	 */
	public List<SolicitContributions> searchPosition(LeanQueryModel condition);
	/*public List<SolicitContributions> findPageForLean(LeanQueryModel condition);*/
}
