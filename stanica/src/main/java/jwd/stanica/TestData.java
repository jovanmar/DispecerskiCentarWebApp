package jwd.stanica;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Izvestaj;
import jwd.stanica.model.Stanica;
import jwd.stanica.model.Voznja;
import jwd.stanica.service.IzvestajService;
import jwd.stanica.service.StanicaService;
import jwd.stanica.service.VoznjaService;


@Component
public class TestData {
	
	@Autowired
	StanicaService stanicaService;
	@Autowired
	VoznjaService voznjaService;
	@Autowired
	IzvestajService izvestajService;
	
	
	
	@PostConstruct
	public void init() {
	
		Stanica stanica1 = new Stanica();
		stanica1.setAdresa("adresa1");
		stanica1.setVreme("10:55");
		stanica1.setRedniBroj(1);
		stanicaService.save(stanica1);



		Stanica stanica2 = new Stanica();
		stanica2.setAdresa("adresa2");
		stanica2.setVreme("10:22" );
		stanica2.setRedniBroj(2);
		stanicaService.save(stanica2);
		
		Stanica stanica3 = new Stanica();
		stanica3.setAdresa("adresa3");
		stanica3.setVreme("10:33" );
		stanica3.setRedniBroj(3);
		stanicaService.save(stanica3);
		
		Voznja voznja = new Voznja();
		voznja.setBrojVoznje("#005");
		voznja.setKilometara(100);
		voznja.setVozac("vozac1");
		voznjaService.save(voznja);

		Voznja voznja2 = new Voznja();
		voznja2.setBrojVoznje("#003");
		voznja2.setKilometara(101);
		voznja2.setVozac("vozac1");
		voznjaService.save(voznja2);


		Izvestaj izvestaj = new Izvestaj();
		izvestaj.setPoruka("neka poruka1");
		izvestaj.setStanica(stanica1);
		izvestajService.save(izvestaj);

		Izvestaj izvestaj2 = new Izvestaj();
		izvestaj2.setPoruka("neka poruka2");
		izvestaj2.setStanica(stanica2);
		izvestajService.save(izvestaj2);

		stanica1.setVoznja(voznja2);
		stanicaService.save(stanica1);
		stanica2.setVoznja(voznja);
		stanicaService.save(stanica2);
		stanica3.setVoznja(voznja);
		stanicaService.save(stanica3);
		
		
	}
	
}
