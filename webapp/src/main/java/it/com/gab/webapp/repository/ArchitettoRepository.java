package it.com.gab.webapp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.com.gab.webapp.entity.Architetto;

@Repository
public interface ArchitettoRepository extends JpaSpecificationExecutor<Architetto>, JpaRepository<Architetto, Integer> {
	

	@Query(value = "SELECT count(*) FROM architetto a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) <> '' and  a.pec is not null) "+
			"and a.data_invio_pec is null "+
			"order by a.nome ", nativeQuery=true )
	public long countArchitettoByPecNotSend() throws Exception;
	
	@Query(value = "SELECT * FROM architetto a "+  
			"where 1 = 1 " + 
			"and (trim(a.pec) <> '' and  a.pec is not null) "+
			"and a.data_invio_pec is null "+
			"order by a.nome ", nativeQuery=true )
	public List<Architetto> findAllArchPecNotInvied() throws Exception;
	
	
	@Query(value = "SELECT count(*) FROM architetto a "+  
			"where 1 = 1 " + 
			"and (trim(a.mail) <> '' and  a.mail is not null) "+
			"and a.data_invio_pec is null "+
			"and a.data_invio_mail is null "+
			"order by a.nome ", nativeQuery=true )
	public long countArchitettoByMailNotSend() throws Exception;
	
	@Query(value = "SELECT * FROM architetto a "+  
			"where 1 = 1 " + 
			"and (trim(a.mail) <> '' and  a.mail is not null) "+
			"and a.data_invio_pec is null "+
			"and a.data_invio_mail is null "+
			"order by a.nome ", nativeQuery=true )
	public List<Architetto> findAllArchMailNotInvied() throws Exception;
	
	
	@Query(value = "SELECT count(*) FROM architetto a "+  
			"where 1 = 1 " + 
			"and (trim(a.telefono1) <> '' and  a.telefono1 is not null) "+
			"and a.data_invio_tel1 is null "+
			"order by a.nome ", nativeQuery=true )
	public long countArchitettoByTel1NotSend() throws Exception;
	
	@Query(value = "SELECT * FROM architetto a "+  
			"where 1 = 1 " + 
			"and (trim(a.telefono1) <> '' and  a.telefono1 is not null) "+
			"and a.data_invio_tel1 is null "+
			"order by a.nome ", nativeQuery=true )
	public List<Architetto> findAllArchTel1NotSent() throws Exception;
}
