package ol.bean;

import java.sql.Timestamp;
import java.util.LinkedList;

import ol.entity.User;

public class UserBean {
	private Integer userId;
	private String userName;//登录名
	private String pwd;
	private String realName;//姓名
	private Timestamp lastLogin;//最后登录时间
	private Integer role;//角色 0学生 1老师
	private boolean isOnline = false;
	private boolean isGag = false; //是否禁言
	private boolean isNoHandUp = false; //是否不能举手
	
	private LinkedList<String> stuHandUpMsgList = new LinkedList<String>();
	
	public UserBean() {
	}
	public UserBean(User user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.pwd = user.getPwd();
		this.realName = user.getRealName();
		this.lastLogin = user.getLastLogin();
		this.role = user.getRole();
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public boolean isGag() {
		return isGag;
	}
	public void setGag(boolean isGag) {
		this.isGag = isGag;
	}
	public boolean isNoHandUp() {
		return isNoHandUp;
	}
	public void setNoHandUp(boolean isNoHandUp) {
		this.isNoHandUp = isNoHandUp;
	}
	public LinkedList<String> getStuHandUpMsgList() {
		return stuHandUpMsgList;
	}
	public void setStuHandUpMsgList(LinkedList<String> stuHandUpMsgList) {
		this.stuHandUpMsgList = stuHandUpMsgList;
	}
	
}
