package it.com.gab.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.com.gab.webapp.entity.Avvocato;
import it.com.gab.webapp.exception.ResourceNotFoundException;
import it.com.gab.webapp.model.InvioFormMail;
import it.com.gab.webapp.model.InvioFormPec;
import it.com.gab.webapp.service.AvvocatoService;
import it.com.gab.webapp.utils.Properties;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AvvocatoController {

	@Autowired
	AvvocatoService avvocatoService;

	@GetMapping("/avvocati")
	public List<Avvocato> getAvvocati() {
		List<Avvocato> avvocatos = avvocatoService.findAllAvvocatoCbills();
		return avvocatos;
	}

	@PostMapping("/importAvvocati")
	public void importAvvocati(@RequestParam("file") MultipartFile file) throws Exception, ResourceNotFoundException {
		try {
			avvocatoService.saveNewAvvocatoCbill(Properties.anno, file);
		} catch (ResourceNotFoundException re) {
			throw re;
		} catch (Exception e) {
			throw e;
		}

	}

	@GetMapping("/numPecDaInviare")
	public Long getNumPecDaInviare() throws Exception {
		Long avvocatos = avvocatoService.countAvvocatoCbillByPecNonInviata();
		return avvocatos;
	}

	@PostMapping("/inviaPec")
	public void inviaPec(@RequestBody InvioFormPec invioForm) throws Exception, ResourceNotFoundException {
		try {
			avvocatoService.invia("pec", invioForm.getNumPecSel());
		} catch (Exception e) {
			throw e;
		}

	}

	@GetMapping("/numMailDaInviare")
	public Long getNumMailDaInviare() throws Exception {
		Long avvocatos = avvocatoService.countAvvocatoCbillByMailNonInviata();
		return avvocatos;
	}

	@PostMapping("/inviaMail")
	public void inviaMail(@RequestBody InvioFormMail invioForm) throws Exception, ResourceNotFoundException {
		try {
			avvocatoService.invia("mail", invioForm.getNumMailSel());
		} catch (Exception e) {
			throw e;
		}

	}

}
