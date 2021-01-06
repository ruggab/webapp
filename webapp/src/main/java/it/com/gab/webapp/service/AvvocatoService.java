package it.com.gab.webapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.com.gab.webapp.entity.Avvocato;

@Service
public interface AvvocatoService {

	public void saveNewAvvocatoCbill(Avvocato avvocatoCbill) throws Exception;

	public void saveNewAvvocatoCbill(String anno, MultipartFile file) throws Exception;

	public long countAvvocatoCbillByPecNonInviata() throws Exception;

	public long countAvvocatoCbillByMailNonInviata() throws Exception;

	public abstract long countAllAvvocatoCbills();

	public abstract void deleteAvvocatoCbill(Avvocato avvocatoCbill);

	public abstract Avvocato findAvvocatoCbill(Integer id);

	public abstract List<Avvocato> findAllAvvocatoCbills();

	public abstract void saveAvvocatoCbill(Avvocato avvocatoCbill);

	public abstract Avvocato updateAvvocatoCbill(Avvocato avvocatoCbill);

	public List<Avvocato> findAvvocatoCbillByPecNotSend(Integer limit) throws Exception;

	public List<Avvocato> findAvvocatoCbillByMailNotSend(Integer limit) throws Exception;
	
	public void invia(String tipoInvio, String numPecSel) throws Exception;
	
	

}
