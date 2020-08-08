package hcl.hackthon.interview.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hcl.hackthon.interview.entity.Book;
import hcl.hackthon.interview.entity.BookIssue;
import hcl.hackthon.interview.entity.User;
import hcl.hackthon.interview.error.AppException;
import hcl.hackthon.interview.model.BookModel;
import hcl.hackthon.interview.model.UserModel;
import hcl.hackthon.interview.repository.BookIssueRepository;
import hcl.hackthon.interview.repository.BookRepository;
import hcl.hackthon.interview.repository.UserRepository;

//@ConfigurationProperties()
//@Configuration("application")
@Service
public class BookIssueService {

	private static final Logger logger = LoggerFactory.getLogger(BookIssueService.class);
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	private static String MEMBER = "MEMBER" ;
	@Autowired
	private BookIssueRepository bookIssueRepository;

	@Value("${USERNOTAVAIABLE}")
	private int USERNOTAVAIABLE;
	@Value("${USERNOTAMEMBER}")
	private int USERNOTAMEMBER;
	@Value("${BOOKQUANTITYZERO}")
	private int BOOKQUANTITYZERO;
	@Value("${BOOKNOTAVAILABLE}")
	private int BOOKNOTAVAILABLE;
	
	@Value("${BOOKALREADYISSUED}")
	private int BOOKALREADYISSUED;

	@Value("${USERNOTAVAIABLE_MESSAGE}")
	private String USERNOTAVAIABLE_MESSAGE;
	@Value("${USERNOTAMEMBER_MESSAGE}")
	private String USERNOTAMEMBER_MESSAGE;
	@Value("${BOOKQUANTITYZERO_MESSAGE}")
	private String BOOKQUANTITYZERO_MESSAGE;
	@Value("${BOOKNOTAVAILABLE_MESSAGE}")
	private String BOOKNOTAVAILABLE_MESSAGE;
	
	@Value("${BOOKALREADYISSUED_MESSAGE}")
	private String BOOKALREADYISSUED_MESSAGE;

	public BookIssue issueBook(BookModel bookModel, UserModel userModel) throws Exception {
		return issueBook(bookModel.getIsbnNo(),userModel.getId());		
	}
	public BookIssue issueBook(Long bookISBN, Long userId) throws Exception {		
		logger.info("Entered BookIssueService.issueBook",bookISBN,userId);
		BookIssue exist = getBookIssue(bookISBN,userId);
		if(null!=exist)
			throw new AppException(BOOKALREADYISSUED,BOOKALREADYISSUED_MESSAGE);
		final AppException error= new AppException("message");
		final BookIssue bookIssue = new BookIssue();
		Optional<User> userOptional = userRepository.findById(userId);
		userOptional.ifPresentOrElse(
				   user -> {if(MEMBER.equals(userOptional.get().getRole())) {
						Optional<Book> bookOptional = bookRepository.findById(bookISBN);
						bookOptional.ifPresentOrElse(
								   book -> {
									   	if(book.getQty()>0) {									   		
									   		bookIssue.setBook(book);
									   		bookIssue.setUser(user);
									   		
									   	}else {
									   		error.setCode(BOOKQUANTITYZERO);
									   		error.setMessage(BOOKQUANTITYZERO_MESSAGE);
									   		//throw new AppError(BOOKQUANTITYZERO,BOOKQUANTITYZERO_MESSAGE);
									   	}
								   },
								   () -> {error.setCode(BOOKNOTAVAILABLE);
							   		error.setMessage(BOOKNOTAVAILABLE_MESSAGE);}
								);
					}else{
						error.setCode(USERNOTAMEMBER);
				   		error.setMessage(USERNOTAMEMBER_MESSAGE);
					}},
				   () ->{error.setCode(USERNOTAVAIABLE);
			   		error.setMessage(USERNOTAMEMBER_MESSAGE);}
		);
		if( null != bookIssue.getUser()) {
			bookIssue.getBook().setQty(bookIssue.getBook().getQty()-1);
	   		bookRepository.save(bookIssue.getBook());	   		
			bookIssueRepository.save(bookIssue);
			logger.debug("BookIssueService.issueBook Issueing book ",bookIssue.getBook());
			logger.debug("Exited BookIssueService.issueBook",bookISBN,userId);
			return bookIssue;
		}
		logger.debug("Exited BookIssueService.issueBook",bookISBN,userId);
		//return null ;
		throw error;
		
	}
	
	public void returnBook(Long bookISBN, Long userId) {
		logger.debug("Entered BookIssueService.returnBook",bookISBN,userId);
		deleteBookIssue(bookISBN, userId);
		logger.debug("Exited BookIssueService.returnBook",bookISBN,userId);
	}
	
	@PersistenceContext
	private EntityManager em;

	public BookIssue getBookIssue(Long bookId,Long userNo) {
		String queryString = "select bu.* from BOOK_ISSUE bu where bu.BOOK_ISBN_NO="+bookId+" and bu.USER_ID="+userNo;
		Query query = em.createNativeQuery(queryString);
		List<BookIssue>  list = query.getResultList();
		if(list.size()>0)
			return list.get(0);
		return null ;
	}
	
	@Transactional
	public void deleteBookIssue(Long bookId,Long userNo) {
		String queryString = "delete from BOOK_ISSUE bu where bu.BOOK_ISBN_NO="+bookId+" and bu.USER_ID="+userNo;
		Query query = em.createNativeQuery(queryString);
		query.executeUpdate();
	}
}
