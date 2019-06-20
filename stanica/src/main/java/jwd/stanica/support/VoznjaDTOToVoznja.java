package jwd.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Voznja;
import jwd.stanica.service.VoznjaService;
import jwd.stanica.web.dto.VoznjaDTO;

@Component
public class VoznjaDTOToVoznja implements Converter<VoznjaDTO, Voznja>{

	@Autowired
	private VoznjaService voznjaService;

	
	@Override
	public Voznja convert(VoznjaDTO voznjaDTO) {
		
			Voznja voznja = new Voznja();
			
		
			if(voznjaDTO.getId() != null) {
				voznja = voznjaService.findOne(voznjaDTO.getId());
			}else {
				voznja = new Voznja();
			}
			
			voznja.setBrojVoznje(voznjaDTO.getBrojVoznje());
			voznja.setKilometara(voznjaDTO.getKilometara());
			voznja.setVozac(voznjaDTO.getVozac());
			
			
			
			return voznja;
	}

	public List<Voznja> convert(List<VoznjaDTO> linijaDTOs){
		List<Voznja> ret = new ArrayList<>();
		
		for(VoznjaDTO linijaDTO : linijaDTOs){
			ret.add(convert(linijaDTO));
		}
		
		return ret;
	}
}