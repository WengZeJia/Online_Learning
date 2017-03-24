package ol.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Contributions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "courese", catalog = "contribute")
public class Courese implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5772576893959249024L;
	private Integer coureseId;
	private User user;//所属老师
	private String cName;//课程名称
	private String cDescribe;//课程描述
	private String startTime;//开始时间
	private Timestamp releaseTime;//发布时间
	private Integer status;//状态
	private String type;//科目 
	private List<Enroll> enrolls = new ArrayList<Enroll>();//课程报名记录

	/** default constructor */
	public Courese() {
	}

	@Id
	@GeneratedValue
	@Column(name = "courese_id", unique = true, nullable = false)
	public Integer getCoureseId() {
		return coureseId;
	}

	public void setCoureseId(Integer coureseId) {
		this.coureseId = coureseId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "c_name", length = 255)
	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	@Column(name = "c_describe", length = 255)
	public String getcDescribe() {
		return cDescribe;
	}

	public void setcDescribe(String cDescribe) {
		this.cDescribe = cDescribe;
	}

	@Column(name = "start_time", length = 255)
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "release_time")
	public Timestamp getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Timestamp releaseTime) {
		this.releaseTime = releaseTime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "courese")
	public List<Enroll> getEnrolls() {
		return enrolls;
	}

	public void setEnrolls(List<Enroll> enrolls) {
		this.enrolls = enrolls;
	}
	
	@Column(name = "c_type",length = 60)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}