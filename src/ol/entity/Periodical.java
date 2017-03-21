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
 * Periodical entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "periodical", catalog = "contribute")
public class Periodical implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2054727008756941867L;
	private Integer periodicalId;
	private String periodicalName;
	private String isbn;
	private String detail;
	private String address;
	private String tel;
	private String email;
	private String pic;
	private String admin;
	private String pwd;
	private Integer status;
	private Timestamp lastLogin;
	private List<SolicitContributions> solicitContributionses = new ArrayList<SolicitContributions>();
	private PeriodicalType periodicalType;

	// Constructors

	/** default constructor */
	public Periodical() {
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "periodical_id", unique = true, nullable = false)
	public Integer getPeriodicalId() {
		return this.periodicalId;
	}

	public void setPeriodicalId(Integer periodicalId) {
		this.periodicalId = periodicalId;
	}

	@Column(name = "periodical_name")
	public String getPeriodicalName() {
		return this.periodicalName;
	}

	public void setPeriodicalName(String periodicalName) {
		this.periodicalName = periodicalName;
	}

	@Column(name = "isbn", length = 45)
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "periodical_type")
	public PeriodicalType getPeriodicalType() {
		return this.periodicalType;
	}

	public void setPeriodicalType(PeriodicalType periodicalType) {
		this.periodicalType = periodicalType;
	}

	@Column(name = "detail", length = 65535)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "tel", length = 45)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "email", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "pic")
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "admin", length = 50)
	public String getAdmin() {
		return this.admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	@Column(name = "pwd", length = 50)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "last_login", length = 19)
	public Timestamp getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "periodical")
	public List<SolicitContributions> getSolicitContributionses() {
		return this.solicitContributionses;
	}

	public void setSolicitContributionses(
			List<SolicitContributions> solicitContributionses) {
		this.solicitContributionses = solicitContributionses;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}