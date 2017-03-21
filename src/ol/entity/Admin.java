package ol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin", catalog = "contribute")
public class Admin implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2783317985996698520L;
	private Integer adminId;
	private String username;
	private String pwd;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** full constructor */
	public Admin(String username, String pwd) {
		this.username = username;
		this.pwd = pwd;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "admin_id", unique = true, nullable = false)
	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	@Column(name = "username", length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "pwd", length = 45)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}