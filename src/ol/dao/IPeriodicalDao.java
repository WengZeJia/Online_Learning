package ol.dao;

import java.util.List;

import ol.entity.Periodical;
import ol.entity.PeriodicalType;

/**
 * 刊物数据库操作
 * @author admin
 *
 */
public interface IPeriodicalDao {

	void savePeriodical(Periodical p);
	
	void updatePeriodical(Periodical p);
	
	void deletePeriodical(int pid);
	
	Periodical findPeriodical(int pid);
	
	Periodical findPeriodical(String username, String pwd);
	
	List<Periodical> findAllPeriodicals(int start, int end);
	
	Integer findAllPeriodicalsCount();
	
	List<Periodical> findAllPeriodicalsByType(int type, int start, int end);
	
	Integer findAllPeriodicalsByTypeCount(int type);
	
	List<Periodical> findHotPeriodicals();
	
	List<PeriodicalType> findAllPeriodicalTypes();
	
	void savePeriodicalType(PeriodicalType type);
	
	void updatePeriodicalType(PeriodicalType type);
	
	PeriodicalType findPeriodicalType(int ptid);
}
