package ol.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ol.bean.ChatMessageBean;
import ol.bean.ChatMessageCenter;
import ol.bean.UserBean;
import ol.dao.IChatLogDao;
import ol.dao.ICoureseDao;
import ol.entity.ChatLog;
import ol.entity.Courese;
import ol.entity.Enroll;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 监听ServletContext对象创建和销毁
 *
 */
public class InitDataListener implements ServletContextListener{
	ApplicationContext context = null;
	
	public void contextInitialized(ServletContextEvent sce) {
		context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		initChatMessageCenter(sce);
		
	}

	/**
	 * 初始化聊天信息数据中心ChatMessageCenter
	 * @param sce
	 */
	private void initChatMessageCenter(ServletContextEvent sce) {
		ICoureseDao courseDao = (ICoureseDao) context.getBean("coureseDaoImpl");
		IChatLogDao chatLogDao = (IChatLogDao) context.getBean("chatLogDaoImpl");
		//获取所有课程，并获取该课程下的所有
		List<Courese> courseList = courseDao.queryAllCourse();
		ChatMessageCenter cmCenter = new ChatMessageCenter();
		for (Courese courese : courseList) {
			cmCenter.addCourse(courese.getCoureseId()); //添加课程
			
			//填充课程-用户信息Map
			cmCenter.addCourseUser(courese.getCoureseId(), new UserBean(courese.getUser())); //添加老师
			List<Enroll> enrolls = courese.getEnrolls();
			for (Enroll enroll : enrolls) {
				cmCenter.addCourseUser(courese.getCoureseId(), new UserBean(enroll.getUser())); //添加课程报名的学生
			}
			
			//填充课程-聊天信息Map
			List<ChatLog> chatLogs = chatLogDao.findLogByCoureseId(courese.getCoureseId());
			for (ChatLog chatLog : chatLogs) {
				cmCenter.addCourseChatMsg(courese.getCoureseId(), new ChatMessageBean(chatLog)); //添加课程报名的学生
			}
		}
		sce.getServletContext().setAttribute("chatMessageCenter", cmCenter);
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		
	}



}
