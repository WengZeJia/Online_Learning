package ol.dao;

import java.util.List;

import ol.entity.ChatLog;

public interface IChatLogDao {

	
	void saveChatLog(ChatLog cl);
	
	List<ChatLog> findLogByCoureseId(Integer coureseId);
	
}
