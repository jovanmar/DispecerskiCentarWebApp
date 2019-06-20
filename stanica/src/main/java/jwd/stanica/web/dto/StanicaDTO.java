package jwd.stanica.web.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class StanicaDTO{

	private Long id;

	private String vreme;
	@NotNull @NotEmpty
	private String adresa;
	@Min(0)
	private Integer redniBroj;
	private boolean trenutna;
	
	private Long voznjaId;
	private String brojVoznje;
	
	
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
	public Long getVoznjaId() {
		return voznjaId;
	}
	public void setVoznjaId(Long voznjaId) {
		this.voznjaId = voznjaId;
	}
	public String getBrojVoznje() {
		return brojVoznje;
	}
	public void setBrojVoznje(String brojVoznje) {
		this.brojVoznje = brojVoznje;
	}
	
	
	
	

}
