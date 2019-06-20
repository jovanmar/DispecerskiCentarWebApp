package jwd.stanica.web.dto;



public class VoznjaDTO{

	private Long id;

	
	private String brojVoznje;
	private String vozac;
	private Integer kilometara;
	
	
	
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
	
	

}