package ol.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PeriodicalType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "periodical_type", catalog = "contribute")
public class PeriodicalType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4265924108814280693L;
	private Integer periodicalTypeId;
	private String typeName;
	private String remark;
	private List<Periodical> periodicals = new ArrayList<Periodical>();

	// Constructors

	/** default constructor */
	public PeriodicalType() {
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "periodical_type_id", unique = true, nullable = false)
	public Integer getPeriodicalTypeId() {
		return this.periodicalTypeId;
	}

	public void setPeriodicalTypeId(Integer periodicalTypeId) {
		this.periodicalTypeId = periodicalTypeId;
	}

	@Column(name = "type_name")
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "periodicalType")
	public List<Periodical> getPeriodicals() {
		return this.periodicals;
	}

	public void setPeriodicals(List<Periodical> periodicals) {
		this.periodicals = periodicals;
	}

}