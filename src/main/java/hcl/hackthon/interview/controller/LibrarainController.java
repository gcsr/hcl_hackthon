package hcl.hackthon.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hcl.hackthon.interview.entity.Librarian;
import hcl.hackthon.interview.exception.ExceptionCtrl;
import hcl.hackthon.interview.model.Login;
import hcl.hackthon.interview.model.ResultStatus;
import hcl.hackthon.interview.service.LibrarianService;

@RestController
public class LibrarainController {
	@Autowired
	private LibrarianService service;

	@RequestMapping(value = "api/librarian", method = RequestMethod.POST, consumes = "application/JSON", produces = "application/JSON")
	public Librarian registerLibrarian(@RequestBody Librarian librarian) throws ExceptionCtrl {
		return service.addLibrarian(librarian);
	}

	@RequestMapping(value = "api/librarian/{id}", method = RequestMethod.GET, produces = "application/JSON")
	public Librarian getLibrarianById(@PathVariable int id) {
		return service.getLibrarianById(id);
	}
	
	@RequestMapping(value = "api/librarian", method = RequestMethod.GET, produces = "application/JSON")
	public List<Librarian> getAllLibrarian() {
		return service.getAllLibrarian();
	}

	@RequestMapping(value = "api/librarian/mail", method = RequestMethod.GET, produces = "application/JSON")
	public Librarian getLibrarianByEmailId(@RequestParam(name="mailId") String mailId) {
		return service.getLibrarianUsingmailId(mailId);
	}

	@RequestMapping(value = "api/librarian/login", method = RequestMethod.POST, produces = { "application/JSON" })
	public Librarian getLibrarianByUserNameAndPassword(@RequestBody Login login) {
		return service.getLibrarianUsingCredential(login.getUserName(), login.getPassword());
	}

	@RequestMapping(value = "api/librarian/{id}", method = RequestMethod.PUT, consumes = "application/JSON")
	public ResultStatus updateLibrarianById(@PathVariable int id, @RequestBody Librarian LibrarianInfo) {
		String result = "";
		ResultStatus resultStatus = new ResultStatus();
		if (service.updateLibrarian(id, LibrarianInfo)) {
			result = "Librarian Updated successfull";
		} else {
			result = "Librarian not updated successfull";
		}
		resultStatus.setStatus(result);
		return resultStatus;
	}

	@RequestMapping(value = "api/librarian/{id}", method = RequestMethod.DELETE, produces = "application/JSON")
	public ResultStatus deleteLibrarian(@PathVariable int id) {
		String result = "";
		ResultStatus resultStatus = new ResultStatus();
		if (service.removeLibrarian(id)) {
			result = "Librarian deleted successfully";
		}else{
			result = "LIbrarin not able to delete due to not found in system ";
		}
		
		resultStatus.setStatus(result);
		return resultStatus;
	}

}
