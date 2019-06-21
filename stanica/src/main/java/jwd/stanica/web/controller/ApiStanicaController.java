package jwd.stanica.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.stanica.model.Izvestaj;
import jwd.stanica.model.Stanica;
import jwd.stanica.service.IzvestajService;
import jwd.stanica.service.StanicaService;
import jwd.stanica.support.IzvestajDTOToIzvestaj;
import jwd.stanica.support.IzvestajToIzvestajDTO;
import jwd.stanica.support.StanicaDTOToStanica;
import jwd.stanica.support.StanicaToStanicaDTO;
import jwd.stanica.web.dto.IzvestajDTO;
import jwd.stanica.web.dto.StanicaDTO;

@RestController
@RequestMapping(value = "/api/stanice")
public class ApiStanicaController {
	@Autowired
	private StanicaService stanicaService;

	@Autowired
	private StanicaToStanicaDTO toStanicaDto;

	@Autowired
	private StanicaDTOToStanica toStanica;

	@Autowired
	private IzvestajService izvestajService;

	@Autowired
	private IzvestajDTOToIzvestaj toIzvestaj;

	@Autowired
	private IzvestajToIzvestajDTO izvestajToDto;

	// dobavljanje stanica sa i bez parametara za pretragu, paginirano
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<StanicaDTO>> getStanice(
			@RequestParam(required = false) String adresa,
			@RequestParam(required = false) Long voznjaId,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {

		Page<Stanica> stanicaPage = null;

		if (voznjaId != null || adresa != null) {
			stanicaPage = stanicaService.search(voznjaId, adresa, pageNum);
		} else {
			stanicaPage = stanicaService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(stanicaPage.getTotalPages()));

		return new ResponseEntity<>(toStanicaDto.convert(stanicaPage.getContent()), headers, HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	//// dobavljanje stanice po kljucu
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<StanicaDTO> getStanica(@PathVariable Long id) {
		
		Stanica stanica = stanicaService.findOne(id);
		if (stanica == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toStanicaDto.convert(stanica), HttpStatus.OK);
	}

	//// brisanje stanice iz baze
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<StanicaDTO> delete(@PathVariable Long id) {

		Stanica deleted = stanicaService.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toStanicaDto.convert(deleted), HttpStatus.OK);
	}

	// dodavanje nove stanice u bazu
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	ResponseEntity<StanicaDTO> add(@Validated @RequestBody StanicaDTO newStanicaDto) {

		Stanica savedStanica = stanicaService.save(toStanica.convert(newStanicaDto));

		return new ResponseEntity<>(toStanicaDto.convert(savedStanica), HttpStatus.CREATED);
	}

	/// izmena postojece stanice
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	ResponseEntity<StanicaDTO> edit(@Validated @RequestBody StanicaDTO stanicaDto, @PathVariable Long id) {

		if (!id.equals(stanicaDto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Stanica persisted = stanicaService.save(toStanica.convert(stanicaDto));

		return new ResponseEntity<>(toStanicaDto.convert(persisted), HttpStatus.OK);
	}

	/// dodavanje novog izvestaja u bazu

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json")
	ResponseEntity<IzvestajDTO> addIzvestaj(@Validated @RequestBody IzvestajDTO newIzvestajDto) {

		Izvestaj savedIzvestaj = izvestajService.save(toIzvestaj.convert(newIzvestajDto));

		return new ResponseEntity<>(izvestajToDto.convert(savedIzvestaj), HttpStatus.CREATED);
	}

}
