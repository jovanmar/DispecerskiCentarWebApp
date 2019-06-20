package jwd.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Izvestaj;
import jwd.stanica.web.dto.IzvestajDTO;

@Component
public class IzvestajToIzvestajDTO implements Converter<Izvestaj, IzvestajDTO> {

	@Override
	public IzvestajDTO convert(Izvestaj izvestaj) {
		if(izvestaj==null){
			return null;
		}
		
		IzvestajDTO dto = new IzvestajDTO();
		
		dto.setId(izvestaj.getId());
		dto.setBrojVoznje(izvestaj.getStanica().getVoznja().getBrojVoznje());
		dto.setPoruka(izvestaj.getPoruka());
		dto.setStanicaAdresa(izvestaj.getStanica().getAdresa());
		dto.setStanicaId(izvestaj.getStanica().getId());
		dto.setStanicaVreme(izvestaj.getStanica().getVreme());
		
		
		return dto;
	}
	
	public List<IzvestajDTO> convert(List<Izvestaj> izvestaji){
		List<IzvestajDTO> dtoList = new ArrayList<>();
		
		for(Izvestaj izvestaj: izvestaji){
			dtoList.add(convert(izvestaj));
		}
		
		return dtoList;
	}

}