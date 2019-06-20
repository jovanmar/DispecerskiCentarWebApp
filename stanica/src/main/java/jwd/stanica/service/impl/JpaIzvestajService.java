package jwd.stanica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.stanica.model.Izvestaj;
import jwd.stanica.repository.IzvestajRepository;
import jwd.stanica.service.IzvestajService;

@Service
public class JpaIzvestajService implements IzvestajService {
	
	@Autowired
	IzvestajRepository izvestajRepository;
	
	
	@Override
	public Izvestaj findOne(Long id) {
		
		return izvestajRepository.findOne(id);
	}

	@Override
	public List<Izvestaj> findAll() {
		return izvestajRepository.findAll();
	}

	@Override
	public Izvestaj save(Izvestaj izvestaj) {
		return izvestajRepository.save(izvestaj);
	}
	

}
