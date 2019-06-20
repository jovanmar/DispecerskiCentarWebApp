package jwd.stanica.service;

import java.util.List;

import jwd.stanica.model.Izvestaj;



public interface IzvestajService {
	Izvestaj findOne(Long id);
	List<Izvestaj> findAll();
	Izvestaj save(Izvestaj izvestaj);

}
