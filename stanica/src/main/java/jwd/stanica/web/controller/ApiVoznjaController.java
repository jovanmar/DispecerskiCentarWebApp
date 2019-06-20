package jwd.stanica.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.stanica.model.Stanica;
import jwd.stanica.model.Voznja;
import jwd.stanica.service.VoznjaService;
import jwd.stanica.support.StanicaToStanicaDTO;
import jwd.stanica.support.VoznjaDTOToVoznja;
import jwd.stanica.support.VoznjaToVoznjaDTO;
import jwd.stanica.web.dto.StanicaDTO;
import jwd.stanica.web.dto.VoznjaDTO;

@RestController
@RequestMapping(value="/api/voznje")
public class ApiVoznjaController {
	@Autowired
	private VoznjaService linijaService;
	
	@Autowired
	private VoznjaToVoznjaDTO toDTO;
	
	@Autowired
	private StanicaToStanicaDTO toStanicaDto;
//	
	@Autowired
	private VoznjaDTOToVoznja toLinija;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<VoznjaDTO>> getVoznje(
			@RequestParam(required=false) String destinacija,
			@RequestParam(required=false) Long prevoznikId,
			@RequestParam(required=false) Double maksCena,
			@RequestParam(value="wpageNum", defaultValue="0") int pageNum){
		
		Page<Voznja> voznjaPage = null;
		
//		if(destinacija != null || prevoznikId != null || maksCena != null) {
//			voznjaPage = linijaService.search(destinacija, prevoznikId, maksCena, pageNum);
//		}
//		else {
			voznjaPage = linijaService.findAll(pageNum);
//		}


		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(voznjaPage.getTotalPages()) );
		
		return new ResponseEntity<>(
				toDTO.convert(voznjaPage.getContent()), 
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/stanice", method=RequestMethod.GET)
	ResponseEntity<List<StanicaDTO>> getVoznja(@PathVariable Long id){
		Voznja voznja = linijaService.findOne(id);
		if(voznja==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Stanica> retStanice = voznja.getStanice();
		
		return new ResponseEntity<>(
				toStanicaDto.convert(retStanice),
				HttpStatus.OK);
	}
	
//	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
//	ResponseEntity<VoznjaDTO> delete(@PathVariable Long id){
//		Voznja deleted = linijaService.delete(id);
//		
//		if(deleted == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		
//		return new ResponseEntity<>(
//				toDTO.convert(deleted),
//				HttpStatus.OK);
//	}
	
//	@RequestMapping(method=RequestMethod.POST,
//					consumes="application/json")
//	public ResponseEntity<VoznjaDTO> add(
//			@Validated @RequestBody VoznjaDTO newLinijaDTO){
//		
//		Voznja savedVoznja = linijaService.save(
//				toLinija.convert(newLinijaDTO));
//		
//		return new ResponseEntity<>(
//				toDTO.convert(savedVoznja), 
//				HttpStatus.CREATED);
//	}
	
	
//	@RequestMapping(method=RequestMethod.PUT,
//			value="/{id}",
//			consumes="application/json")
//	public ResponseEntity<VoznjaDTO> edit(
//			@Validated @RequestBody VoznjaDTO linijaDTO,
//			@PathVariable Long id){
//		
//		if(!id.equals(linijaDTO.getId())){
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		
//		Voznja persisted = linijaService.save(
//				toLinija.convert(linijaDTO));
//		
//		return new ResponseEntity<>(
//				toDTO.convert(persisted),
//				HttpStatus.OK);
//	}
	
//	@RequestMapping(method=RequestMethod.POST, value="/{id}")
//	public ResponseEntity<IzvestajDTO> reserve(@PathVariable Long id){
//		 
//		Izvestaj r = linijaService.reserve(id);
//		
//		if(r != null) {
//			return new ResponseEntity<>(toRezervacijaDTO.convert(r),
//					HttpStatus.CREATED);
//		}
//		else {
//			return new ResponseEntity<>(
//					HttpStatus.BAD_REQUEST);
//		}
//			
//	}
//	
//	@ExceptionHandler(value=DataIntegrityViolationException.class)
//	public ResponseEntity<Void> handle() {
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
}