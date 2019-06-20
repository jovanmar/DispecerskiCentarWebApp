package jwd.stanica.service;


import org.springframework.data.domain.Page;

import jwd.stanica.model.Stanica;

public interface StanicaService {
	Stanica findOne(Long id);
	Page<Stanica> findAll(int pageNum);
	Stanica save(Stanica prevoznik);
	Stanica delete(Long id);
	Page<Stanica> search(Long voznjaId, String adresa, int pageNum);
}