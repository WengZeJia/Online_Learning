package ol.bean;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class ChatMessageCenter {
	private static String IMAGE_KEY_PREFIX = "COURSEIMAGE";
	private static String CHAT_KEY_PREFIX = "COURSECHAT";
	private static String USER_KEY_PREFIX = "COURSEUSER";
	
	Map<String, String> courseImageMap = Collections.synchronizedMap(new HashMap<String, String>()); //课程时画
	Map<String, LinkedList<ChatMessageBean>> chatMsgMap = Collections.synchronizedMap(new HashMap<String, LinkedList<ChatMessageBean>>()); //课程聊天Map
	Map<String, LinkedList<UserBean>> couseUserMap = Collections.synchronizedMap(new HashMap<String, LinkedList<UserBean>>()); //课程用户Map

	public Map<String, String> getCourseImageMap() {
		return courseImageMap;
	}
	public void setCourseImageMap(Map<String, String> courseImageMap) {
		this.courseImageMap = courseImageMap;
	}
	public Map<String, LinkedList<ChatMessageBean>> getChatMsgMap() {
		return chatMsgMap;
	}
	public void setChatMsgMap(Map<String, LinkedList<ChatMessageBean>> chatMsgMap) {
		this.chatMsgMap = chatMsgMap;
	}
	public Map<String, LinkedList<UserBean>> getCouseUserMap() {
		return couseUserMap;
	}
	public void setCouseUserMap(Map<String, LinkedList<UserBean>> couseUserMap) {
		this.couseUserMap = couseUserMap;
	}
	
	/**
	 * 获取课程的实时绘画
	 * @param courseId
	 * @return
	 */
	public String getCourseImage(int courseId) {
		return this.courseImageMap.get(IMAGE_KEY_PREFIX + courseId);
	}
	
	public void addCourseImage(int courseId, String imageBase64) {
		this.courseImageMap.put(IMAGE_KEY_PREFIX + courseId, imageBase64);
	}
	
	/**
	 * 获取课程的聊天记录
	 * @param courseId
	 * @return
	 */
	public LinkedList<ChatMessageBean> getCourseChats(int courseId) {
		return this.chatMsgMap.get(CHAT_KEY_PREFIX + courseId);
	}
	
	/**
	 * 在课程的聊天信息中，插入一条记录
	 * @param courseId
	 * @param msg
	 */
	public void addCourseChatMsg(int courseId, ChatMessageBean msg) {
		this.getCourseChats(courseId).addLast(msg);
	}
	
	/**
	 * 获取课程的用户集合
	 * @param courseId
	 * @return
	 */
	public LinkedList<UserBean> getCourseUsers(int courseId) {
		return this.couseUserMap.get(USER_KEY_PREFIX + courseId);
	}
	
	/**
	 * 用户报名课程时，添加用户到课程中
	 * @param courseId
	 */
	public void addCourseUser(int courseId, UserBean userBean) {
		this.getCourseUsers(courseId).addLast(userBean);
	}
	
	/**
	 * 添加课程时，需要向聊天信息Map与课程用户列表Map中添加该课程，并new对应集合设置到其值
	 * @param courseId
	 */
	public void addCourse(int courseId) {
		String image = this.getCourseImage(courseId);
		LinkedList<ChatMessageBean> courseChats = this.getCourseChats(courseId);
		LinkedList<UserBean> courseUsers = this.getCourseUsers(courseId);
		if(StringUtils.isBlank(image)) {
			String imageBase64 = "";
			this.courseImageMap.put(CHAT_KEY_PREFIX + courseId, imageBase64);
		}
		if(courseChats == null) {
			LinkedList<ChatMessageBean> chatMsgList = new LinkedList<ChatMessageBean>();
			this.chatMsgMap.put(CHAT_KEY_PREFIX + courseId, chatMsgList);
		}
		if(courseUsers == null) {
			LinkedList<UserBean> userList = new LinkedList<UserBean>();
			this.couseUserMap.put(USER_KEY_PREFIX + courseId, userList);
		}
	}
	
	/**
	 * 删除课程聊天及在线用户
	 * @param courseId
	 */
	public void removeCourse(int courseId) {
		LinkedList<ChatMessageBean> courseChats = this.getCourseChats(courseId);
		LinkedList<UserBean> courseUsers = this.getCourseUsers(courseId);
		if(courseChats != null) {
			this.chatMsgMap.remove(CHAT_KEY_PREFIX + courseId);
		}
		if(courseUsers != null) {
			this.couseUserMap.remove(USER_KEY_PREFIX + courseId);
		}
	}
	
	/**
	 * 设置用户上线
	 * @param userId
	 */
	public void setUserOnline(int userId) {
		Set<String> cuKeySet = this.couseUserMap.keySet();
		for (String cuKey : cuKeySet) {
			LinkedList<UserBean> userList = this.couseUserMap.get(cuKey);
			for (UserBean userBean : userList) {
				if(userBean.getUserId() == userId) {
					userBean.setOnline(true);
				}
			}
		}
	}
	
	/**
	 * 设置用户下线
	 * @param userId
	 */
	public void setUserOutline(int userId) {
		Set<String> cuKeySet = this.couseUserMap.keySet();
		for (String cuKey : cuKeySet) {
			LinkedList<UserBean> userList = this.couseUserMap.get(cuKey);
			for (UserBean userBean : userList) {
				if(userBean.getUserId() == userId) {
					userBean.setOnline(false);
				}
			}
		}
	}
	
	/**
	 * 设置学生禁言状态
	 * @param courseId
	 * @param userId
	 * @param status
	 */
	public void setStuGagStatus(int courseId, int userId, boolean status) {
		LinkedList<UserBean> userList = getCourseUsers(courseId);
		for (UserBean userBean : userList) {
			if(userBean.getUserId() == userId && userBean.getRole().intValue() == 0) {
				userBean.setGag(status);
			}
		}
	}
	
	/**
	 * 设置学生举手与否状态
	 * @param courseId
	 * @param userId
	 * @param status
	 */
	public void setStuHandUpStatus(int courseId, int userId, boolean status) {
		LinkedList<UserBean> userList = getCourseUsers(courseId);
		for (UserBean userBean : userList) {
			if(userBean.getUserId() == userId && userBean.getRole().intValue() == 0) {
				userBean.setNoHandUp(status);
			}
		}
	}
	
	/**
	 * 获取课程学生的禁言状态
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public boolean getStuGagStatus(int courseId, int userId) {
		LinkedList<UserBean> userList = getCourseUsers(courseId);
		for (UserBean userBean : userList) {
			if(userBean.getUserId() == userId) {
				return userBean.isGag();
			}
		}
		return true;
	}
	
	/**
	 * 获取课程学生的举手状态
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public boolean getStuHandUpStatus(int courseId, int userId) {
		LinkedList<UserBean> userList = getCourseUsers(courseId);
		for (UserBean userBean : userList) {
			if(userBean.getUserId() == userId) {
				return userBean.isNoHandUp();
			}
		}
		return true;
	}
	
	/**
	 * 获取发起课程的教师
	 * @param courseId
	 * @return
	 */
	public UserBean getCourseTeacher(int courseId) {
		LinkedList<UserBean> userList = getCourseUsers(courseId);
		for (UserBean userBean : userList) {
			if(userBean.getRole().intValue() == 1) {
				return userBean;
			}
		}
		return null;
	}
	
	/**
	 * 获取用户信息
	 * @param courseId
	 * @return
	 */
	public UserBean getCourseUser(int courseId, int userId) {
		LinkedList<UserBean> userList = this.couseUserMap.get(USER_KEY_PREFIX + courseId);
		for (UserBean userBean : userList) {
			if(userBean.getUserId().intValue() == userId) {
				return userBean;
			}
		}
		return null;
	}
}
