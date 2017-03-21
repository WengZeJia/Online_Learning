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
@Table(name = "contributions", catalog = "contribute")
public class Contributions implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5772576893959249024L;
	private Integer contributionsId;
	private User user;
	private SolicitContributions solicitContributions;
	private Timestamp publishTime;
	private String title;
	private String content;
	private Integer status;

	// Constructors

	/** default constructor */
	public Contributions() {
	}

	/** full constructor */
	public Contributions(User user, SolicitContributions solicitContributions,
			Timestamp publishTime) {
		this.user = user;
		this.solicitContributions = solicitContributions;
		this.publishTime = publishTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "contributions_id", unique = true, nullable = false)
	public Integer getContributionsId() {
		return this.contributionsId;
	}

	public void setContributionsId(Integer contributionsId) {
		this.contributionsId = contributionsId;
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
	@JoinColumn(name = "solicit_contributions_id")
	public SolicitContributions getSolicitContributions() {
		return this.solicitContributions;
	}

	public void setSolicitContributions(
			SolicitContributions solicitContributions) {
		this.solicitContributions = solicitContributions;
	}

	@Column(name = "publish_time", length = 19)
	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}