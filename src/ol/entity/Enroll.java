package ol.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Contributions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "enroll", catalog = "contribute")
public class Enroll implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5772576893959249024L;
	private Integer enrollId;
	private User user;
	private Courese courese;
	private Timestamp eTime;
	private Integer status;

	/** default constructor */
	public Enroll() {
	}

	@Id
	@GeneratedValue
	@Column(name = "enroll_id", unique = true, nullable = false)
	public Integer getEnrollId() {
		return enrollId;
	}

	public void setEnrollId(Integer enrollId) {
		this.enrollId = enrollId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courese_id")
	public Courese getCourese() {
		return this.courese;
	}
	
	public void setCourese(Courese courese) {
		this.courese = courese;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "enroll_time")
	public Timestamp geteTime() {
		return eTime;
	}

	public void seteTime(Timestamp eTime) {
		this.eTime = eTime;
	}
	
	

}