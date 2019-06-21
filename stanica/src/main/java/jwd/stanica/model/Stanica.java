package jwd.stanica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Stanica{

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(nullable=false)
	private String vreme;
	@Column(nullable=false)
	private String adresa;
	@Column
	private Integer redniBroj;
	@Column
	private boolean trenutna;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Voznja voznja;

	@OneToMany(mappedBy="stanica", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Izvestaj> izvestaji = new ArrayList<>();
	
	@PrePersist
	private void setTrenutna() {
		if (this.getRedniBroj() == 1) {
			this.setTrenutna(true);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Integer getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(Integer redniBroj) {
		this.redniBroj = redniBroj;
	}

	public boolean isTrenutna() {
		return trenutna;
	}

	public void setTrenutna(boolean trenutna) {
		this.trenutna = trenutna;
	}

	public Voznja getVoznja() {
		return voznja;
	}

	public void setVoznja(Voznja voznja) {
		this.voznja = voznja;
		if(!voznja.getStanice().contains(this)) {
			voznja.getStanice().add(this);
		}
	}

	public List<Izvestaj> getIzvestaji() {
		return izvestaji;
	}

	public void setIzvestaji(List<Izvestaj> izvestaji) {
		this.izvestaji = izvestaji;
	}
	
	public void addIzvestaj(Izvestaj izvestaj) {
		if(izvestaj.getStanica() != this) {
			izvestaj.setStanica(this);
		}
	     izvestaji.add(izvestaj);
	}
}