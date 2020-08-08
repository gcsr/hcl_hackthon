package hcl.hackthon.interview.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcl.hackthon.interview.entity.Librarian;
import hcl.hackthon.interview.exception.ExceptionCtrl;
import hcl.hackthon.interview.repository.LibrarianRepository;

@Service
public class LibrarianService {
	private static final Logger logger = LoggerFactory.getLogger(LibrarianService.class);
	@Autowired
	private LibrarianRepository librarianRepository;

	public Librarian addLibrarian(Librarian librarian) throws ExceptionCtrl {
		logger.info("Entered LibrarianService.addLibrarian",librarian.getEmail());
		Librarian librarianInfo = librarianRepository.getLibrarianByMailId(librarian.getEmail());
		if (librarianInfo == null) {
			librarianInfo = librarianRepository.save(librarian);
		} else {
			throw new ExceptionCtrl("Librarian already exists with  same EmailId");
		}
		return librarianInfo;
	}

	public Librarian getLibrarianUsingmailId(String mailId) {
		logger.info("Entered LibrarianService.getLibrarianUsingmailId",mailId);
		Librarian librarian = librarianRepository.getLibrarianByMailId(mailId);
		return librarian;
	}

	public Librarian getLibrarianById(int librarianId) {
		logger.info("Entered LibrarianService.getLibrarianById",librarianId);
		Optional<Librarian> librarian = librarianRepository.findById(librarianId);
		return librarian.get();
	}

	public Librarian getLibrarianUsingCredential(String userName, String password) {
		Librarian librarian = librarianRepository.getLibrarianByUserNameAndPassword(userName, password);
		return librarian;
	}

	public boolean removeLibrarian(int librarianId) {
		logger.info("Entered LibrarianService.removeLibrarian",librarianId);
		librarianRepository.deleteById(librarianId);
		return true;
	}

	public List<Librarian> getAllLibrarian() {
		logger.info("Entered LibrarianService.getAllLibrarian");
		return (List<Librarian>) librarianRepository.findAll();
	}

	public List<Librarian> getLibrarianByName(String name) {
		logger.info("Entered LibrarianService.getLibrarianByName",name);
		List<Librarian> librarianByName = (List<Librarian>) librarianRepository.getLibrarianByName(name);
		return librarianByName;
	}

	public boolean updateLibrarian(int librarianId, Librarian librarian) {
		logger.info("Entered LibrarianService.updateLibrarian",librarianId);
		Optional<Librarian> cuInfo = librarianRepository.findById(librarianId);
		Librarian librarianDB = cuInfo.get();
		librarianDB.setfName(librarian.getfName());
		librarianDB.setlName(librarian.getlName());
		librarianDB.setAddress(librarian.getAddress());
		librarianDB.setPhone(librarian.getPhone());
		librarianDB.setEmail(librarian.getEmail());
		librarianRepository.save(librarianDB);
		return true;

	}
}