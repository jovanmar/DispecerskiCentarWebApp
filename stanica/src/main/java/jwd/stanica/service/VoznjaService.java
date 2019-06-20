package jwd.stanica.service;

import org.springframework.data.domain.Page;

import jwd.stanica.model.Voznja;
//import jwd.stanica.model.Izvestaj;

public interface VoznjaService {
	Voznja findOne(Long id);
	Page<Voznja> findAll(int pageNum);
//	Page<Voznja> search(String destinacija, Long prevoznikId, Double maksCena, int pageNum);
	Voznja save(Voznja linija);
	Voznja delete(Long id);
//	Izvestaj reserve(Long id);
}