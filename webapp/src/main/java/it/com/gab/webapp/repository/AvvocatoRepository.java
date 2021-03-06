package it.com.gab.webapp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.com.gab.webapp.entity.Avvocato;


@Repository
public interface AvvocatoRepository extends JpaSpecificationExecutor<Avvocato>, JpaRepository<Avvocato, Integer> {
	
	
	List<Avvocato> findByCodiceFiscale(String codFiscale);
	
	
	@Query(value = "SELECT a.* FROM avvocato_cbill a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) <> '' and  a.pec is not null) "+
			"and a.data_invio_pec is null "+
			"order by a.id_uni_prof "+
			"limit 0, ?1 ", nativeQuery=true )
	public List<Avvocato> findAvvocatoCbillByPecNotSend(Integer p1) throws Exception;
	
	@Query(value = "SELECT a.* FROM avvocato_cbill a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) <> '' and  a.pec is not null) "+
			"and a.data_invio_pec is null "+
			"order by a.id_uni_prof ", nativeQuery=true )
	public List<Avvocato> findAvvocatoCbillByPecNotSend() throws Exception;
	
	@Query(value = "SELECT count(*) FROM avvocato_cbill a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) <> '' and  a.pec is not null) "+
			"and a.data_invio_pec is null "+
			"order by a.id_uni_prof ", nativeQuery=true )
	public long countAvvocatoCbillByPecNotSend() throws Exception;

	
	@Query(value = "SELECT a.* FROM avvocato_cbill a "+  
			"where 1 = 1 " +
			"and (trim(a.pec) = '' or  a.pec is null) "+
			"and (trim(a.mail) <> '' and  a.mail is not null) "+
			"and a.data_invio_mail is null "+
			"order by a.id_uni_prof "+
			"limit 0, ?1 ", nativeQuery=true )
	public List<Avvocato> findAvvocatoCbillByMailNotSend(Integer p1) throws Exception;
	
	@Query(value = "SELECT a.* FROM avvocato_cbill a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) = '' or  a.pec is null) "+
			"and (trim(a.mail) <> '' and  a.mail is not null) "+
			"and a.data_invio_mail is null "+
			"order by a.id_uni_prof ", nativeQuery=true )
	public List<Avvocato> findAvvocatoCbillByMailNotSend() throws Exception;
	
	@Query(value = "SELECT count(*) FROM avvocato_cbill a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) = '' or  a.pec is null) "+
			"and (trim(a.mail) <> '' and  a.mail is not null) "+
			"and a.data_invio_mail is null "+
			"order by a.id_uni_prof ", nativeQuery=true )
	public long countAvvocatoCbillByMailNotSend() throws Exception;
	
}
