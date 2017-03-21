package ol.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * SolicitContributions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "solicit_contributions", catalog = "contribute")
public class SolicitContributions implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4510349580297853703L;
	private Integer solicitContributionsId;
	private Periodical periodical;
	private String title;
	private String content;
	private Timestamp publishTime;
	private Date deadLine;
	private Double fee;
	private String columnType;
	private List<Contributions> contributionses = new ArrayList<Contributions>(0);
	private int contributionsCount;

	// Constructors

	/** default constructor */
	public SolicitContributions() {
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "solicit_contributions_id", unique = true, nullable = false)
	public Integer getSolicitContributionsId() {
		return this.solicitContributionsId;
	}

	public void setSolicitContributionsId(Integer solicitContributionsId) {
		this.solicitContributionsId = solicitContributionsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "periodical_id")
	public Periodical getPeriodical() {
		return this.periodical;
	}

	public void setPeriodical(Periodical periodical) {
		this.periodical = periodical;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "publish_time", length = 19)
	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dead_line", length = 10)
	public Date getDeadLine() {
		return this.deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	@Column(name = "fee", precision = 22, scale = 0)
	public Double getFee() {
		return this.fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	@Column(name = "column_type")
	public String getColumnType() {
		return this.columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "solicitContributions")
	public List<Contributions> getContributionses() {
		return this.contributionses;
	}

	public void setContributionses(List<Contributions> contributionses) {
		this.contributionses = contributionses;
	}

	@Transient
	public int getContributionsCount() {
		return contributionsCount;
	}

	public void setContributionsCount(int contributionsCount) {
		this.contributionsCount = contributionsCount;
	}

}