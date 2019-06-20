package jwd.stanica.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.stanica.model.Voznja;
//import jwd.stanica.model.Izvestaj;
import jwd.stanica.repository.VoznjaRepository;
//import jwd.stanica.repository.IzvestajRepository;
import jwd.stanica.service.VoznjaService;

@Service
@Transactional
public class JpaVoznjaService implements VoznjaService {

	@Autowired
	private VoznjaRepository voznjaRepository;
	
//	@Autowired
//	private IzvestajRepository izvetajRepository;
	
	@Override
	public Voznja findOne(Long id) {
		return voznjaRepository.findOne(id);
	}

	@Override
	public Page<Voznja> findAll(int pageNum) {
		return voznjaRepository.findAll(new PageRequest(pageNum, 5));
	}
	
//	@Override
//	public Page<Voznja> search( String destinacija, Long prevoznikId,
//								Double maksCena, int pageNum){
//		
//		if(destinacija != null) {
//			destinacija = '%' + destinacija + '%';
//		}
//		
//		return voznjaRepository.search(destinacija, prevoznikId, maksCena, new PageRequest(pageNum, 5));
//	}
	
	@Override
	public Voznja save(Voznja linija) {
		return voznjaRepository.save(linija);
	}

	@Override
	public Voznja delete(Long id) {
		Voznja linija = voznjaRepository.findOne(id);
		if(linija != null){
			voznjaRepository.delete(linija);
		}
		
		return linija;
	}

//	@Override
//	public Izvestaj reserve(Long id) {
//		Voznja l = findOne(id);
//		
//		if(l != null) {
//			Izvestaj r = null;
//			if(l.getBrojMesta() > 0) {
//				r = new Izvestaj();
//				r.setLinija(l);
//				rezervacijaRepository.save(r);
//				
//				l.setBrojMesta(l.getBrojMesta() - 1);
//				linijaRepository.save(l);
//			}
//			
//			return new Izvestaj();
//		}
//		else {
//			throw new IllegalArgumentException("Tried to reserve a ticket for non-existant line");
//		}
		
}