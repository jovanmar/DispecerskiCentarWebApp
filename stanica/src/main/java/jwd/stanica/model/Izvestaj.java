package jwd.stanica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table
public class Izvestaj{

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String poruka;

	
	@ManyToOne(fetch=FetchType.EAGER)
	private Stanica stanica;

	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPoruka() {
		return poruka;
	}


	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}


	public Stanica getStanica() {
		return stanica;
	}


	public void setStanica(Stanica stanica) {
		this.stanica = stanica;
		if(!stanica.getIzvestaji().contains(this)) {
			stanica.getIzvestaji().add(this);
		}
	
	}

	
}