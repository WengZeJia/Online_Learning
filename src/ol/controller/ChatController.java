package ol.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ol.bean.ChatMessageBean;
import ol.bean.ChatMessageCenter;
import ol.bean.UserBean;
import ol.dao.IChatLogDao;
import ol.entity.ChatLog;
import ol.entity.Courese;
import ol.entity.User;
import ol.vo.ChatSendMsgVo;
import ol.vo.CourseUserGagReqVo;
import ol.vo.CourseUserHandUpReqVo;
import ol.vo.StudentHandUpReqVo;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private IChatLogDao chatDao;
	
	private static String USER_SESSION_KEY = "user";
	
	@RequestMapping("toChatPage.do")
	public ModelAndView toChatPage(HttpServletRequest req, Integer coureseId, HttpServletResponse resp) {
		User user = (User) req.getSession().getAttribute(USER_SESSION_KEY);
		if(user == null) {
			System.out.println("用户未登陆");
			return null;
		}
		if(coureseId == null || coureseId.intValue() == 0) {
			System.out.println("传入的课程Id数据不对");
			return null;
		}
		ServletContext application = req.getServletContext();  //获取application
		ChatMessageCenter cmCenter =  (ChatMessageCenter) application.getAttribute("chatMessageCenter"); //获取聊天信息数据center
		if(cmCenter.getCourseTeacher(coureseId)==null) {
			System.out.println("找不对对应课堂");
			return null;
		}
		return new ModelAndView("chat/chatPage").addObject("courseId", coureseId);
	}
	
	/**
	 * 发送聊天内容
	 * @throws Exception 
	 */
	@RequestMapping("stuHandUp.json")
	@ResponseBody
	public Object stuHandUp(HttpServletRequest req, StudentHandUpReqVo reqVo, HttpServletResponse resp) throws Exception{
		User user = (User) req.getSession().getAttribute(USER_SESSION_KEY);
		Map<String, Object> result = new HashMap<String, Object>();
		if(user == null) {
			result.put("result", "N");
			result.put("message", "请先登陆");
			return result;
		}
		ServletContext application = req.getServletContext();  //获取application
		ChatMessageCenter cmCenter =  (ChatMessageCenter) application.getAttribute("chatMessageCenter"); //获取聊天信息数据center
		int courseId = reqVo.getCourseId();
		Integer fromUserId = reqVo.getFromUserId();
		UserBean courseTeacher = cmCenter.getCourseTeacher(courseId);
		if(!courseTeacher.isOnline()) {
			result.put("result", "N");
			result.put("message", "目前"+courseTeacher.getRealName()+"还没上线，无法举手");
			return result;
		}
		UserBean courseUser = cmCenter.getCourseUser(courseId, fromUserId);
		if(courseUser.isNoHandUp()) {
			result.put("result", "N");
			result.put("message", "你目前处于被禁止举手状态，无法举手");
			return result;
		}
		
		courseTeacher.getStuHandUpMsgList().addLast(courseUser.getRealName()+"正在举手...");
		// 将消息存入到application的范围
		application.setAttribute("chatMessageCenter", cmCenter);
		result.put("result", "Y");
		result.put("message", "你的举手消息已经发出，等待老师响应..");
		return result;
	}
	
	/**
	 * 获取学生举手消息
	 * @throws Exception 
	 */
	@RequestMapping("getStudentHandUpMsg.json")
	@ResponseBody
	public Object getStudentHandUpMsg(HttpServletRequest req, Integer courseId, HttpServletResponse resp) throws Exception{
		User user = (User) req.getSession().getAttribute(USER_SESSION_KEY);
		Map<String, Object> result = new HashMap<String, Object>();
		if(user == null) {
			result.put("result", "N");
			result.put("message", "请先登陆");
			return result;
		}
		if(user.getRole().intValue()!=1) {
			result.put("result", "N");
			result.put("message", "非教师不能执行该操作");
			return result;
		}
		ServletContext application = req.getServletContext();  //获取application
		ChatMessageCenter cmCenter =  (ChatMessageCenter) application.getAttribute("chatMessageCenter"); //获取聊天信息数据center
		UserBean courseTeacher = cmCenter.getCourseUser(courseId, user.getUserId());
		LinkedList<String> stuHandUpMsgList = courseTeacher.getStuHandUpMsgList();
		if(stuHandUpMsgList==null || stuHandUpMsgList.isEmpty()) {
			result.put("result", "N");
			result.put("message", "目前没人举手");
			return result;
		}
		String oneHandUpMsg = stuHandUpMsgList.removeFirst();
		// 将消息存入到application的范围
		application.setAttribute("chatMessageCenter", cmCenter);
		result.put("result", "Y");
		result.put("data", oneHandUpMsg);
		return result;
	}
	
	/**
	 * 发送聊天内容
	 * @throws Exception 
	 */
	@RequestMapping("sendMessage.json")
	@ResponseBody
	public Object sendMessage(HttpServletRequest req, ChatSendMsgVo msgVo, HttpServletResponse resp) throws Exception{
		User user = (User) req.getSession().getAttribute(USER_SESSION_KEY);
		Map<String, Object> result = new HashMap<String, Object>();
		if(user == null) {
			result.put("result", "N");
			result.put("message", "请先登陆");
			return result;
		}
		ServletContext application = req.getServletContext();  //获取application
		ChatMessageCenter cmCenter =  (ChatMessageCenter) application.getAttribute("chatMessageCenter"); //获取聊天信息数据center
		int courseId = msgVo.getCourseId();
		Integer fromUserId = msgVo.getFromUserId();
		if(cmCenter.getStuGagStatus(courseId, fromUserId)) {
			result.put("result", "N");
			result.put("message", "你目前处于被禁言状态，无法发送消息");
			return result;
		}
		
		ChatMessageBean msgBean = new ChatMessageBean(msgVo); //将请求Vo转化为Bean
		ChatLog chatLog = changeChatBeanToChatLog(msgBean); //将Bean转换为Entity
		chatDao.saveChatLog(chatLog); //保存聊天信息到数据库
		
		
		cmCenter.addCourseChatMsg(courseId, msgBean); //将一条聊天信息Bean添加到数据中心
		// 将消息存入到application的范围
		application.setAttribute("chatMessageCenter", cmCenter);
		return getMessage(req, courseId, resp);
	}
	
	private ChatLog changeChatBeanToChatLog(ChatMessageBean msgBean) throws Exception {
		ChatLog chatLog = new ChatLog();
		User user = new User();
		user.setUserId(msgBean.getFromUserId());
		chatLog.setFromUser(user);
		
		chatLog.setContent(msgBean.getContent());
		
		Courese courese = new Courese();
		courese.setCoureseId(msgBean.getFormCourseId());
		chatLog.setFromCourese(courese);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		chatLog.setSendTime(sdf.parse(msgBean.getDateTime()));
		return chatLog;
	}

	/**
	 * 根据courseId获取消息的方法
	 */
	@RequestMapping("getMessage.json")
	@ResponseBody
	public Object getMessage(HttpServletRequest req, Integer courseId, HttpServletResponse resp) {
		User user = (User) req.getSession().getAttribute(USER_SESSION_KEY);
		Map<String, Object> result = new HashMap<String, Object>();
		if(user == null) {
			result.put("result", "N");
			result.put("message", "用户尚未登陆");
			return result;
		}
		ChatMessageCenter cmCenter = (ChatMessageCenter) req.getServletContext().getAttribute("chatMessageCenter");
		LinkedList<ChatMessageBean> chatMsgBeans = cmCenter.getCourseChats(courseId);
		result.put("result", "Y");
		result.put("data", chatMsgBeans);
		return result;
	}

	/**
	 * 根据courseId获取用户列表
	 */
	@RequestMapping("getCourseUsers.do")
	public ModelAndView getCouseUsers(HttpServletRequest req, Integer courseId, HttpServletResponse resp) {
		ChatMessageCenter cmCenter = (ChatMessageCenter) req.getServletContext().getAttribute("chatMessageCenter");
		LinkedList<UserBean> courseUsers = cmCenter.getCourseUsers(courseId);
		return new ModelAndView("chat/chatUserPage").addObject("courseUsers", courseUsers);
	}
	
	/**
	 * 设置指定课程中的学生发言状态，发言或禁言
	 */
	@RequestMapping("setCourseStuGagStatus.json")
	@ResponseBody
	public Object setCourseStuGagStatus(HttpServletRequest req, CourseUserGagReqVo reqVo, HttpServletResponse resp) {
		User user = (User) req.getSession().getAttribute(USER_SESSION_KEY);
		Map<String, Object> result = new HashMap<String, Object>();
		if(user == null) {
			result.put("result", "N");
			result.put("message", "用户尚未登陆");
			return result;
		}
		if(user.getRole().intValue()!=1) {
			result.put("result", "N");
			result.put("message", "非教师不能执行该操作");
			return result;
		}
		ChatMessageCenter cmCenter = (ChatMessageCenter) req.getServletContext().getAttribute("chatMessageCenter");
		try {
			cmCenter.setStuGagStatus(reqVo.getCourseId(), reqVo.getUserId(), reqVo.isGag());
		} catch(Exception e) {
			result.put("result", "N");
			result.put("message", "服务器繁忙，请稍后重试");
			return result;
		}
		result.put("result", "Y");
		result.put("message", "设置成功");
		return result;
	}
	
	/**
	 * 设置指定课程中的学生举手状态，允许或者禁止
	 */
	@RequestMapping("setCourseStuHandUpStatus.json")
	@ResponseBody
	public Object setCourseStuHandUpStatus(HttpServletRequest req, CourseUserHandUpReqVo reqVo, HttpServletResponse resp) {
		User user = (User) req.getSession().getAttribute(USER_SESSION_KEY);
		Map<String, Object> result = new HashMap<String, Object>();
		if(user == null) {
			result.put("result", "N");
			result.put("message", "用户尚未登陆");
			return result;
		}
		if(user.getRole().intValue()!=1) {
			result.put("result", "N");
			result.put("message", "非教师不能执行该操作");
			return result;
		}
		ChatMessageCenter cmCenter = (ChatMessageCenter) req.getServletContext().getAttribute("chatMessageCenter");
		try {
			cmCenter.setStuHandUpStatus(reqVo.getCourseId(), reqVo.getUserId(), reqVo.isNoHandUp());
		} catch(Exception e) {
			result.put("result", "N");
			result.put("message", "服务器繁忙，请稍后重试");
			return result;
		}
		result.put("result", "Y");
		result.put("message", "设置成功");
		return result;
	}
	
}
