package jwd.stanica.web.dto;

public class IzvestajDTO{

	private Long id;

	private String poruka;
	
	private Long stanicaId;
	private String stanicaVreme;
	private String stanicaAdresa;
	
	private String brojVoznje;

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

	public Long getStanicaId() {
		return stanicaId;
	}

	public void setStanicaId(Long stanicaId) {
		this.stanicaId = stanicaId;
	}

	public String getStanicaVreme() {
		return stanicaVreme;
	}

	public void setStanicaVreme(String stanicaVreme) {
		this.stanicaVreme = stanicaVreme;
	}

	public String getStanicaAdresa() {
		return stanicaAdresa;
	}

	public void setStanicaAdresa(String stanicaAdresa) {
		this.stanicaAdresa = stanicaAdresa;
	}

	public String getBrojVoznje() {
		return brojVoznje;
	}

	public void setBrojVoznje(String brojVoznje) {
		this.brojVoznje = brojVoznje;
	}
	
	

}