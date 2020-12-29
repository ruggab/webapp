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

import it.com.gab.webapp.entity.User;
import it.com.gab.webapp.exception.ResourceNotFoundException;
import it.com.gab.webapp.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;
    // standard constructors
    @GetMapping("/users")
    public List<User> getUsers() {
    	
    	List<User> users = userService.getAllUsers();
        return users;
    }
    
    

	@PostMapping("/uploaddoc")
	public void uploadDoc(@RequestParam("file") MultipartFile file) throws Exception, ResourceNotFoundException {
		try {
			userService.salvaUtenti(file);
		} catch (ResourceNotFoundException re) {
			throw re;
		} catch (Exception e) {
			throw e;
		}

	}

   
}
