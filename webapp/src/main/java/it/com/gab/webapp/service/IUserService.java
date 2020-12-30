package it.com.gab.webapp.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.com.gab.webapp.entity.User;


@Service
public interface IUserService {
	void init();

	String saveFile(MultipartFile file);

	Resource loadFile(String fileName);
	
	@Transactional
	public void salvaUtenti(MultipartFile file) throws Exception ;
	
	public List<User> getAllUsers()  throws Exception ;
	
	public List<User> getUsersFromFile(MultipartFile file) throws Exception;
}