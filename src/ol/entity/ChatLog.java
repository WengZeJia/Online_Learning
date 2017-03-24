package ol.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Contributions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "chat_log", catalog = "contribute")
public class ChatLog implements java.io.Serializable {

	// Fields

	/**
	 * 课程聊天记录表
	 */
	private static final long serialVersionUID = -1200042523946192544L;
	private Integer chatLogId;
	private String content; //发送内容
	private Date sendTime;//报名时间
	private User fromUser;//所属用户
	private Courese fromCourese;//所属课程

	/** default constructor */
	public ChatLog() {
	}

	@Id
	@GeneratedValue
	@Column(name = "chat_log_id", unique = true, nullable = false)
	public Integer getChatLogId() {
		return chatLogId;
	}

	public void setChatLogId(Integer chatLogId) {
		this.chatLogId = chatLogId;
	}
	
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "send_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_user_id")
	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_courese_id")
	public Courese getFromCourese() {
		return fromCourese;
	}

	public void setFromCourese(Courese fromCourese) {
		this.fromCourese = fromCourese;
	}
}