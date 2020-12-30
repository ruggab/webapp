package it.com.gab.webapp.service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.com.gab.webapp.entity.User;
import it.com.gab.webapp.repository.UserRepository;
import it.com.gab.webapp.utils.FileUploadProperties;
import it.com.gab.webapp.utils.GenericUtils;
import it.com.gab.webapp.utils.SchemaCsv;
import it.present.daspoCore.utilities.HashBuilder;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	private final Path dirLocation;

	@Autowired
	public UserService(FileUploadProperties fileUploadProperties) {
		this.dirLocation = Paths.get(fileUploadProperties.getLocation()).toAbsolutePath().normalize();
	}

	@Override
	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		try {
			Files.createDirectories(this.dirLocation);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String saveFile(MultipartFile file) {
		// TODO Auto-generated method stub
		String fileName = "";
		try {
			fileName = file.getOriginalFilename();
			Path dfile = this.dirLocation.resolve(fileName);
			Files.copy(file.getInputStream(), dfile, StandardCopyOption.REPLACE_EXISTING);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	@Override
	public Resource loadFile(String fileName) {
		// TODO Auto-generated method stub
		Resource resource = null;
		try {
			Path file = this.dirLocation.resolve(fileName).normalize();
			resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new MalformedURLException("Could not find file");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return resource;
	}

	@Transactional
	public void salvaUtenti(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub

		try {
			InputStream inputStream = new BufferedInputStream(file.getInputStream());
			List<SchemaCsv> listCsv = GenericUtils.read(inputStream);
			for (Iterator iterator = listCsv.iterator(); iterator.hasNext();) {
				SchemaCsv schemaCsv = (SchemaCsv) iterator.next();
				User user = new User();
				user.setTessera(schemaCsv.getTessera());
				user.setCognome(schemaCsv.getCognome());
				user.setComune(schemaCsv.getComune());
				user.setData(schemaCsv.getData());
				user.setNazione(schemaCsv.getNazione());
				user.setNome(schemaCsv.getNome());
				user.setProvincia(schemaCsv.getProvincia());
				user.setSesso(schemaCsv.getSesso());
				String hash = "";
				if (StringUtils.isEmpty(schemaCsv.getComune()) && StringUtils.isEmpty(schemaCsv.getProvincia())) {
					hash = hash + HashBuilder.costruisciHash(schemaCsv.getCognome().toUpperCase(), schemaCsv.getNome().toUpperCase(), schemaCsv.getData().toUpperCase(), schemaCsv.getSesso().toUpperCase(), schemaCsv.getNazione().toUpperCase(), "", "");
				} else {
					hash = hash + HashBuilder.costruisciHash(schemaCsv.getCognome().toUpperCase(), schemaCsv.getNome().toUpperCase(), schemaCsv.getData().toUpperCase(), schemaCsv.getSesso().toUpperCase(), schemaCsv.getNazione().toUpperCase(), schemaCsv.getComune().toUpperCase(), schemaCsv.getProvincia().toUpperCase());
				}

				user.setHash(hash);
				userRepository.save(user);
			}
		} catch (Exception e) {
			throw e;
		}

	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public List<User> getUsersFromFile(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		try {
			InputStream inputStream = new BufferedInputStream(file.getInputStream());
			List<SchemaCsv> listCsv = GenericUtils.read(inputStream);
			for (Iterator iterator = listCsv.iterator(); iterator.hasNext();) {
				SchemaCsv schemaCsv = (SchemaCsv) iterator.next();
				User user = new User();
				user.setTessera(schemaCsv.getTessera());
				user.setCognome(schemaCsv.getCognome());
				user.setComune(schemaCsv.getComune());
				user.setData(schemaCsv.getData());
				user.setNazione(schemaCsv.getNazione());
				user.setNome(schemaCsv.getNome());
				user.setProvincia(schemaCsv.getProvincia());
				user.setSesso(schemaCsv.getSesso());
				String hash = "";
				if (StringUtils.isEmpty(schemaCsv.getComune()) && StringUtils.isEmpty(schemaCsv.getProvincia())) {
					hash = hash + HashBuilder.costruisciHash(schemaCsv.getCognome().toUpperCase(), schemaCsv.getNome().toUpperCase(), schemaCsv.getData().toUpperCase(), schemaCsv.getSesso().toUpperCase(), schemaCsv.getNazione().toUpperCase(), "", "");
				} else {
					hash = hash + HashBuilder.costruisciHash(schemaCsv.getCognome().toUpperCase(), schemaCsv.getNome().toUpperCase(), schemaCsv.getData().toUpperCase(), schemaCsv.getSesso().toUpperCase(), schemaCsv.getNazione().toUpperCase(), schemaCsv.getComune().toUpperCase(), schemaCsv.getProvincia().toUpperCase());
				}

				user.setHash(hash);
				users.add(user);
			}
			return users;

		} catch (Exception e) {
			throw e;
		}

	}

}