package jwd.stanica.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.stanica.model.Stanica;
import jwd.stanica.repository.StanicaRepository;
import jwd.stanica.service.StanicaService;

@Service
public class JpaStanicaService implements StanicaService {

	@Autowired
	private StanicaRepository stanicaRepository;

	@Override
	public Stanica findOne(Long id) {
		return stanicaRepository.findOne(id);
	}


	@Override
	public Stanica save(Stanica stanica) {
		if (stanica.getRedniBroj() == 1) {
			stanica.setTrenutna(true);
		}
		return stanicaRepository.save(stanica);
	}

	@Override
	public Stanica delete(Long id) {
		Stanica stanica = stanicaRepository.findOne(id);
		if (stanica != null) {
			stanicaRepository.delete(stanica);
		}
		return stanica;
	}

	@Override
	public Page<Stanica> search(Long voznjaId, String adresa, int pageNum) {
		
		if(adresa!=null ) {
			adresa = '%' + adresa + '%';
		}
		
		return stanicaRepository.search(voznjaId, adresa , new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Stanica> findAll(int pageNum) {
		return stanicaRepository.findAll(new PageRequest(pageNum, 5));
	}
		
}