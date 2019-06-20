package jwd.stanica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Stanica;
import jwd.stanica.model.Voznja;
import jwd.stanica.service.StanicaService;
import jwd.stanica.service.VoznjaService;
import jwd.stanica.web.dto.StanicaDTO;

@Component
public class StanicaDTOToStanica implements Converter<StanicaDTO, Stanica> {

	@Autowired
	private StanicaService stanicaService;

	@Autowired
	private VoznjaService voznjaService;

	@Override
	public Stanica convert(StanicaDTO stanicaDTO) {

		Voznja voznja = voznjaService.findOne(stanicaDTO.getVoznjaId());
		if (voznja != null) {
			Stanica stanica = null;

			if (stanicaDTO.getId() != null) {
				stanica = stanicaService.findOne(stanicaDTO.getId());
			} else {
				stanica = new Stanica();
			}
			stanica.setAdresa(stanicaDTO.getAdresa());
			stanica.setRedniBroj(stanicaDTO.getRedniBroj());
			stanica.setTrenutna(stanicaDTO.isTrenutna());
			stanica.setVoznja(voznja);
			stanica.setVreme(stanicaDTO.getVreme());
			
			
			return stanica;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Stanica> convert(List<StanicaDTO> stanicaDTOs) {
		List<Stanica> ret = new ArrayList<>();

		for (StanicaDTO stanicaDTO : stanicaDTOs) {
			ret.add(convert(stanicaDTO));
		}

		return ret;
	}
}