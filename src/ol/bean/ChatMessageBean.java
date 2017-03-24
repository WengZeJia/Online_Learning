package ol.bean;

import java.text.SimpleDateFormat;

import ol.entity.ChatLog;
import ol.entity.User;
import ol.vo.ChatSendMsgVo;

public class ChatMessageBean {
	private Integer fromUserId;
	private String fromUserName;
	private Integer formCourseId;
	private String content;
	private String dateTime;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public ChatMessageBean() {
	}
	public ChatMessageBean(ChatSendMsgVo vo) {
		this.fromUserId = vo.getFromUserId();
		this.fromUserName = vo.getFromUserName();
		this.formCourseId = vo.getCourseId();
		this.content = vo.getContent();
		this.dateTime = vo.getDateTime();
	}
	public ChatMessageBean(ChatLog cl) {
		User fromUser = cl.getFromUser();
		this.fromUserId = fromUser.getUserId();
		this.fromUserName = fromUser.getRealName();
		this.formCourseId = cl.getFromCourese().getCoureseId();
		this.content = cl.getContent();
		this.dateTime = sdf.format(cl.getSendTime());
	}
	public Integer getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public Integer getFormCourseId() {
		return formCourseId;
	}
	public void setFormCourseId(Integer formCourseId) {
		this.formCourseId = formCourseId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
