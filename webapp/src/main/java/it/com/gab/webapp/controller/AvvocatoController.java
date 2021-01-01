package it.com.gab.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.com.gab.webapp.entity.Avvocato;
import it.com.gab.webapp.entity.User;
import it.com.gab.webapp.exception.ResourceNotFoundException;
import it.com.gab.webapp.service.AvvocatoService;
import it.com.gab.webapp.service.UserService;

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
			avvocatoService.saveNewAvvocatoCbill(file);
		} catch (ResourceNotFoundException re) {
			throw re;
		} catch (Exception e) {
			throw e;
		}

	}

	
//	@PostMapping("/importfile")
//	public List<Avvocato> getAvvocatiFromFile(@RequestParam("file") MultipartFile file) throws Exception, ResourceNotFoundException {
//		try {
//			List<Avvocato> list =  avvocatoService.getAvvocatosFromFile(file);
//			return list;
//		} catch (ResourceNotFoundException re) {
//			throw re;
//		} catch (Exception e) {
//			throw e;
//		}
//
//	}

   
}
