package jwd.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Izvestaj;
import jwd.stanica.model.Stanica;
import jwd.stanica.service.IzvestajService;
import jwd.stanica.service.StanicaService;
import jwd.stanica.web.dto.IzvestajDTO;

@Component
public class IzvestajDTOToIzvestaj implements Converter<IzvestajDTO, Izvestaj> {

	@Autowired
	IzvestajService izvestajService;
	@Autowired
	StanicaService stanicaService;

	@Override
	public Izvestaj convert(IzvestajDTO izvestajDTO) {

		Stanica stanica = stanicaService.findOne(izvestajDTO.getStanicaId());

		if (stanica != null) {
			Izvestaj izvestaj = null;

			if (izvestajDTO.getId() != null) {
				izvestaj = izvestajService.findOne(izvestajDTO.getId());
			} else {
				izvestaj = new Izvestaj();
			}
			
			izvestaj.setStanica(stanica);
			izvestaj.setPoruka(izvestajDTO.getPoruka());
			
			return izvestaj;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}
	
	
	public List<Izvestaj> convert(List<IzvestajDTO> dtos){
		List<Izvestaj> ret = new ArrayList<>();
		
		for(IzvestajDTO dto : dtos){
			ret.add(convert(dto));
		}
		
		return ret;
	}


}
