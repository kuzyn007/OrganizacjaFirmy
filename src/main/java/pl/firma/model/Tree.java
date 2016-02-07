package pl.firma.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trees")
public class Tree {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String nazwa;
	
	private Integer numerid;
	
	private Integer level;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Integer getNumerid() {
		return numerid;
	}

	public void setNumerid(Integer numerid) {
		this.numerid = numerid;
	}
}
