package jwd.stanica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Voznja{

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(nullable=false, unique = true)
	private String brojVoznje;
	
	@Column(nullable=false)
	private String vozac;
	
	@Column
	private Integer kilometara;
	
	@OneToMany(mappedBy="voznja", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Stanica> stanice = new ArrayList<>();

	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBrojVoznje() {
		return brojVoznje;
	}
	public void setBrojVoznje(String brojVoznje) {
		this.brojVoznje = brojVoznje;
	}
	public String getVozac() {
		return vozac;
	}
	public void setVozac(String vozac) {
		this.vozac = vozac;
	}
	public Integer getKilometara() {
		return kilometara;
	}
	public void setKilometara(Integer kilometara) {
		this.kilometara = kilometara;
	}
	
	public List<Stanica> getStanice() {
		return stanice;
	}

	public void setStanice(List<Stanica> stanice) {
		this.stanice = stanice;
	}

	
	public void addStanica(Stanica stanica) {
		if(stanica.getVoznja() != this) {
			stanica.setVoznja(this);
		}
		stanice.add(stanica);
	}
}