package it.com.gab.webapp.service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.com.gab.webapp.entity.Avvocato;
import it.com.gab.webapp.repository.AvvocatoRepository;
import it.com.gab.webapp.utils.AvvocatoCsv;
import it.com.gab.webapp.utils.GenericUtils;
import it.com.gab.webapp.utils.UserCsv;

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
	public long countAvvocatoCbillByPecInviata() throws Exception {
		return avvocatoCbillRepository.countAvvocatoCbillByPecNotSend();
	}

	@Override
	public long countAvvocatoCbillByMailInviata() throws Exception {
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

	public void saveNewAvvocatoCbill(MultipartFile file) throws Exception {

		try {
			InputStream inputStream = new BufferedInputStream(file.getInputStream());
			List<AvvocatoCsv> listCsv = GenericUtils.readAvvocatos(inputStream);
			for (Iterator iterator = listCsv.iterator(); iterator.hasNext();) {
				AvvocatoCsv schemaCsv = (AvvocatoCsv) iterator.next();
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

}
