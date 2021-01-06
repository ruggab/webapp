package it.com.gab.webapp.service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.com.gab.webapp.entity.Avvocato;
import it.com.gab.webapp.repository.AvvocatoRepository;
import it.com.gab.webapp.utils.AvvocatoCsv;
import it.com.gab.webapp.utils.GenericUtils;
import it.com.gab.webapp.utils.MailerUtility;
import it.com.gab.webapp.utils.Properties;

@Service
@Transactional
public class AvvocatoServiceImpl implements AvvocatoService {

	private static Logger logger = Logger.getLogger(AvvocatoServiceImpl.class);

	
	@Autowired
	AvvocatoRepository avvocatoCbillRepository;

	public void deleteAvvocatoCbillRepository(Avvocato avvocatoCbill) {
		avvocatoCbillRepository.delete(avvocatoCbill);
	}

	public Avvocato findAvvocatoCbill(Integer id) {
		return avvocatoCbillRepository.getOne(id);
	}

	public List<Avvocato> findAllAvvocatoCbills() {
		return avvocatoCbillRepository.findAll();
	}

	public void saveAvvocatoCbill(Avvocato avvocatoCbill) {
		avvocatoCbillRepository.save(avvocatoCbill);
	}

	@Override
	public long countAvvocatoCbillByPecNonInviata() throws Exception {
		return avvocatoCbillRepository.countAvvocatoCbillByPecNotSend();
	}

	@Override
	public long countAvvocatoCbillByMailNonInviata() throws Exception {
		return avvocatoCbillRepository.countAvvocatoCbillByMailNotSend();
	}

	@Override
	public void saveNewAvvocatoCbill(Avvocato avvocatoCbill) throws Exception {
		avvocatoCbillRepository.save(avvocatoCbill);

	}

	@Override
	public long countAllAvvocatoCbills() {
		return avvocatoCbillRepository.count();
	}

	@Override
	public void deleteAvvocatoCbill(Avvocato avvocatoCbill) {
		avvocatoCbillRepository.delete(avvocatoCbill);
	}

	@Override
	public Avvocato updateAvvocatoCbill(Avvocato avvocatoCbill) {
		return avvocatoCbillRepository.save(avvocatoCbill);
	}

	public List<Avvocato> findAvvocatoCbillByPecNotSend(Integer limit) throws Exception {
		List<Avvocato> list = null;
		if (limit != null) {
			list = avvocatoCbillRepository.findAvvocatoCbillByPecNotSend(limit);
		} else {
			list = avvocatoCbillRepository.findAvvocatoCbillByPecNotSend();
		}
		return list;
	}

	public List<Avvocato> findAvvocatoCbillByMailNotSend(Integer limit) throws Exception {
		List<Avvocato> list = null;
		if (limit != null) {
			list = avvocatoCbillRepository.findAvvocatoCbillByMailNotSend(limit);
		} else {
			list = avvocatoCbillRepository.findAvvocatoCbillByMailNotSend();
		}
		return list;
	}

	public void saveNewAvvocatoCbill(String anno, MultipartFile file) throws Exception {

		try {
			InputStream inputStream = new BufferedInputStream(file.getInputStream());
			List<AvvocatoCsv> listCsv = GenericUtils.readAvvocatos(inputStream);
			BigInteger intCodSia = GenericUtils.trasformaCodSia();
			for (Iterator iterator = listCsv.iterator(); iterator.hasNext();) {
				AvvocatoCsv schemaCsv = (AvvocatoCsv) iterator.next();
				schemaCsv = GenericUtils.creaCBILLs(anno, schemaCsv, intCodSia);
				Avvocato avvocatoCbill = new Avvocato();
				try {
					String progressivo = String.format("%05d", new Integer(schemaCsv.getProgressivo()));
					avvocatoCbill.setIdUniProf(progressivo);
					avvocatoCbill.setCodiceFiscale(schemaCsv.getCodiceFiscale());
					avvocatoCbill.setTipoAvvocato(schemaCsv.getTipoAlbo());
					avvocatoCbill.setNome(schemaCsv.getNome());
					avvocatoCbill.setCognome(schemaCsv.getCognome());
					avvocatoCbill.setGiorno(schemaCsv.getGiorno());
					avvocatoCbill.setMese(schemaCsv.getMese());
					avvocatoCbill.setAnno(schemaCsv.getAnno());
					avvocatoCbill.setMail(schemaCsv.getEmail());
					avvocatoCbill.setPec(schemaCsv.getPec());
					//
					avvocatoCbill.setQuotaRata1(schemaCsv.getQuotaRata1());
					avvocatoCbill.setQuotaRata2(schemaCsv.getQuotaRata2());
					avvocatoCbill.setQuotaUnica(schemaCsv.getQuotaUnica());
					//
					String idAlbo = GenericUtils.getIdAlbo(schemaCsv.getTipoAlbo(), schemaCsv.getCassazionista());
					schemaCsv.setIdAlbo(idAlbo);
					avvocatoCbill.setCodTipoAvvocato(idAlbo);
					avvocatoCbill.setCbillRata1(schemaCsv.getCbillPrimaRata());
					avvocatoCbill.setCbillRata2(schemaCsv.getCbillSecondaRata());
					avvocatoCbill.setCbillUnica(schemaCsv.getCbillRataUnica());
					//
					avvocatoCbillRepository.save(avvocatoCbill);
					//
					logger.info("Inserito" + avvocatoCbill.getId());
				} catch (Exception e) {
					logger.error("CF: " + schemaCsv.getCodiceFiscale());
					throw e;
				}
			}
		} catch (Exception e) {
			logger.error(e.toString());
			throw e;
		}

	}
	
	
	public void invia(String tipoInvio, String numMailPecSel) throws Exception {
		logger.debug("INVIA");

		String oggettoMail = Properties.oggetto;
		String ambiente =  Properties.ambiente;

		Integer limit = null;
		if (!numMailPecSel.equals("ALL")) {
			limit = new Integer(numMailPecSel);
		}
		List<Avvocato> listAvvocatoCbill = null;
		MailerUtility mailer = null;
		if (tipoInvio.equals("pec")) {
			//Parametri PEC
			listAvvocatoCbill = avvocatoCbillRepository.findAvvocatoCbillByPecNotSend(limit);
		} else {
			//Parametri MAIL
			listAvvocatoCbill = avvocatoCbillRepository.findAvvocatoCbillByMailNotSend(limit);
		}
		 
		
		for (Avvocato avvocatoCbill : listAvvocatoCbill) { 
			
			//Rieseguo la new Ogni volta per azzerare l'addRecipient
			if (tipoInvio.equals("pec")) {
				//Parametri PEC
				mailer = new MailerUtility("cfgpec.properties");
			} else {
				//Parametri MAIL
				mailer = new MailerUtility("cfgmail.properties");
			}
			// Imposto l'oggetto della mail
			mailer.setSubject(oggettoMail);
			
			
			try {
				
				boolean isPecNonVuota = tipoInvio.equals("pec") && StringUtils.isNotEmpty(avvocatoCbill.getPec());
				boolean isMailNonVuota = tipoInvio.equals("mail") && StringUtils.isNotEmpty(avvocatoCbill.getMail());
				if ( isPecNonVuota || isMailNonVuota) {

					// Aggiungo un destinatario (TO)
					if (tipoInvio.equals("pec")) {
						if (ambiente.equals("prod")) {
							mailer.addRecipient(avvocatoCbill.getPec());
						} else {
							mailer.addRecipient("ruggab@pec.it");
						}
					} else {
						if (ambiente.equals("prod")) {
							mailer.addRecipient(avvocatoCbill.getMail());
						} else {
							//String mailTo = "ruggzan@gmail.com";
							String mailTo = "roberta.rossetti@libero.it";
							mailer.addRecipient(mailTo);
						}
					}
					
					//Aggiungo un destinatario in copia (CC)
//					if (getSelectMailPec().equals("mail")) {
//						mailer.addBCCCopyRecipient("avvisi@ordineavvocatismcv.it");
//					}
					
					// Aggiungo il file
					// mailer.addAttachment(f, nomeFile);
					//Ccpr  ccpr = persona.getIndResId().getCcprId();
					String[] message = null;
					if (tipoInvio.equals("mail")) {
						message = GenericUtils.buildMessage2020Pec(avvocatoCbill);
					} else {
						message = GenericUtils.buildMessage2020Pec(avvocatoCbill);
					}
					
					
					mailer.setTextMessage(message);
					// Invio la mail
					mailer.sendMail();
					
					// Aggiungo un destinatario (TO)
					if (tipoInvio.equals("pec")) {
						avvocatoCbill.setDataInvioPec(new Date());
					} else {
						avvocatoCbill.setDataInvioMail(new Date());
					}
					//
					avvocatoCbillRepository.save(avvocatoCbill);

					logger.info("Invio pec per professionista: " + avvocatoCbill.getIdUniProf() + " riuscito correttamente");
				}
			} catch (Exception e) {
				
				logger.error("Invio pec per professionista: : " + avvocatoCbill.getIdUniProf() + " non riuscito");
			}
		}

		//
		

		
	}

}
