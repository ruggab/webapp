package it.com.gab.webapp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.com.gab.webapp.entity.AvvocatoCbill;


@Repository
public interface AvvocatoCbillRepository extends JpaSpecificationExecutor<AvvocatoCbill>, JpaRepository<AvvocatoCbill, Integer> {
	
	
	List<AvvocatoCbill> findByCodiceFiscale(String codFiscale);
	
	
	@Query(value = "SELECT a.* FROM avvocato_cbill a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) <> '' and  a.pec is not null) "+
			"and a.data_invio_pec is null "+
			"order by a.id_uni_prof "+
			"limit 0, ?1 ", nativeQuery=true )
	public List<AvvocatoCbill> findAvvocatoCbillByPecNotSend(Integer p1) throws Exception;
	
	@Query(value = "SELECT a.* FROM avvocato_cbill a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) <> '' and  a.pec is not null) "+
			"and a.data_invio_pec is null "+
			"order by a.id_uni_prof ", nativeQuery=true )
	public List<AvvocatoCbill> findAvvocatoCbillByPecNotSend() throws Exception;
	
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
	public List<AvvocatoCbill> findAvvocatoCbillByMailNotSend(Integer p1) throws Exception;
	
	@Query(value = "SELECT a.* FROM avvocato_cbill a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) = '' or  a.pec is null) "+
			"and (trim(a.mail) <> '' and  a.mail is not null) "+
			"and a.data_invio_mail is null "+
			"order by a.id_uni_prof ", nativeQuery=true )
	public List<AvvocatoCbill> findAvvocatoCbillByMailNotSend() throws Exception;
	
	@Query(value = "SELECT count(*) FROM avvocato_cbill a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) = '' or  a.pec is null) "+
			"and (trim(a.mail) <> '' and  a.mail is not null) "+
			"and a.data_invio_mail is null "+
			"order by a.id_uni_prof ", nativeQuery=true )
	public long countAvvocatoCbillByMailNotSend() throws Exception;
	
}
