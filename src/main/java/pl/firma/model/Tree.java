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
	
	private String name;
	
	private Integer numberid;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberid() {
		return numberid;
	}

	public void setNumberid(Integer numberid) {
		this.numberid = numberid;
	}
}
