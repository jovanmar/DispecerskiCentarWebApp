package jwd.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Stanica;
import jwd.stanica.web.dto.StanicaDTO;

@Component
public class StanicaToStanicaDTO 
	implements Converter<Stanica, StanicaDTO> {

	@Override
	public StanicaDTO convert(Stanica stanica) {
		if(stanica==null){
			return null;
		}
		
		StanicaDTO dto = new StanicaDTO();
		
		dto.setId(stanica.getId());
		dto.setVreme(stanica.getVreme());
		dto.setVreme(stanica.getVreme());
		dto.setAdresa(stanica.getAdresa());
		dto.setRedniBroj(stanica.getRedniBroj());
		dto.setTrenutna(stanica.isTrenutna());	
		
		dto.setVoznjaId(stanica.getVoznja().getId());
		dto.setBrojVoznje(stanica.getVoznja().getBrojVoznje());
		
		return dto;
	}
	
	public List<StanicaDTO> convert(List<Stanica> stanice){
		List<StanicaDTO> dtoList = new ArrayList<>();
		
		for(Stanica stanica: stanice){
			dtoList.add(convert(stanica));
		}
		
		return dtoList;
	}

}