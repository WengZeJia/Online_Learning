package ol.dao.impl;

import java.util.List;

import ol.dao.IChatLogDao;
import ol.entity.ChatLog;

import org.springframework.stereotype.Repository;

@Repository
public class ChatLogDaoImpl extends HibernateSupport implements IChatLogDao {

	@Override
	public void saveChatLog(ChatLog cl) {
		this.getHibernateTemplate().save(cl);
	}

	@Override
	public List<ChatLog> findLogByCoureseId(Integer coureseId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from ChatLog as cl")
		   .append(" left join fetch cl.fromUser")
		   .append(" left join fetch cl.fromCourese as fc")
		   .append(" where fc.coureseId = ?")
		   .append(" order by cl.sendTime asc");
		return this.getHibernateTemplate().find(hql.toString(), coureseId);
	}

}
