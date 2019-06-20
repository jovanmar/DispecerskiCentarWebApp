package jwd.stanica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.stanica.model.Izvestaj;

@Repository
public interface IzvestajRepository extends JpaRepository<Izvestaj, Long> {
		
}