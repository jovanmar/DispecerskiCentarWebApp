package jwd.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Voznja;
import jwd.stanica.web.dto.VoznjaDTO;

@Component
public class VoznjaToVoznjaDTO 
	implements Converter<Voznja, VoznjaDTO> {

	@Override
	public VoznjaDTO convert(Voznja voznja) {
		if(voznja==null){
			return null;
		}
		
		VoznjaDTO dto = new VoznjaDTO();
		
		dto.setId(voznja.getId());
		dto.setBrojVoznje(voznja.getBrojVoznje());
		dto.setKilometara(voznja.getKilometara());
		dto.setVozac(voznja.getVozac());
		
		
		return dto;
	}
	
	public List<VoznjaDTO> convert(List<Voznja> voznje){
		List<VoznjaDTO> dtoList = new ArrayList<>();
		
		for(Voznja voznja: voznje){
			dtoList.add(convert(voznja));
		}
		
		return dtoList;
	}

}