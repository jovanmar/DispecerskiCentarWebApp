package jwd.stanica.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.stanica.model.Stanica;

@Repository
public interface StanicaRepository extends JpaRepository<Stanica, Long> {
	
	@Query("SELECT s FROM Stanica s WHERE "
			+ "(:voznjaId IS NULL OR s.voznja.id = :voznjaId) AND "
			+ "(:adresa IS NULL or s.adresa like :adresa )  "
			)	
//	@Query("SELECT a FROM Stanica a WHERE "
//			+ "(:adresa IS NULL or a.adresa like :adresa ) AND"
//			+ "(:voznjaId IS NULL OR a.voznja.id = :voznjaId ) " 
////			+ "(:maxCena IS NULL or a.cenaKarte <= :maxCena ) "
//			
//			)
	Page<Stanica> search(
			@Param("voznjaId") Long voznjaId, 
			@Param("adresa") String adresa, 
			Pageable pageRequest);

 
}