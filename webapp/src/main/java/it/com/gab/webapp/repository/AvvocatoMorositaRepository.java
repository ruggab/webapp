package it.com.gab.webapp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.com.gab.webapp.entity.Architetto;
import it.com.gab.webapp.entity.AvvocatoMorosita;

@Repository
public interface AvvocatoMorositaRepository extends JpaSpecificationExecutor<Architetto>, JpaRepository<AvvocatoMorosita, Integer> {
	

	@Query(value = "SELECT count(*) FROM avvocato_morosita a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) <> '' and  a.pec is not null) "+
			"and a.data_invio_pec is null "+
			"order by a.denominazione ", nativeQuery=true )
	public long countAvvocatoByPecNotSend() throws Exception;
	
	@Query(value = "SELECT * FROM avvocato_morosita a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) <> '' and  a.pec is not null) "+
			"and a.data_invio_pec is null "+
			"order by a.denominazione ", nativeQuery=true )
	public List<AvvocatoMorosita> findAllAvvPecNotInvied() throws Exception;
	
	
	@Query(value = "SELECT count(*) FROM avvocato_morosita a "+  
			"where 1 = 1 " + 
			"and (trim(a.mail) <> '' and  a.mail is not null) "+
			"and a.data_invio_pec is null "+
			"and a.data_invio_mail is null "+
			"order by a.denominazione ", nativeQuery=true )
	public long countAvvocatoByMailNotSend() throws Exception;
	
	@Query(value = "SELECT * FROM avvocato_morosita a "+  
			"where 1 = 1 " + 
			"and (trim(a.mail) <> '' and  a.mail is not null) "+
			"and a.data_invio_pec is null "+
			"and a.data_invio_mail is null "+
			"order by a.denominazione ", nativeQuery=true )
	public List<AvvocatoMorosita> findAllAvvocatoMailNotInvied() throws Exception;
	
	
	
}
