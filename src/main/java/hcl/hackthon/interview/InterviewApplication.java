package hcl.hackthon.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import hcl.hackthon.interview.entity.Book;
import hcl.hackthon.interview.entity.User;
import hcl.hackthon.interview.repository.BookRepository;
import hcl.hackthon.interview.repository.UserRepository;


@SpringBootApplication
public class InterviewApplication implements ApplicationContextAware{

	private static final Logger logger = LoggerFactory.getLogger(InterviewApplication.class);

	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext ;
		
	}
	
	public static void main(String[] args) {
		logger.info("this is a info message");
		logger.warn("this is a warn message");
		logger.error("this is a error message");
		logger.debug("this is debug message");
		SpringApplication.run(InterviewApplication.class, args);
		BookRepository br = (BookRepository)applicationContext.getBean(BookRepository.class);
		Book b = new Book();
		b.setIsbnNo(123L);
		b.setQty(12);
		br.save(b);
		
		UserRepository ur = (UserRepository)applicationContext.getBean(UserRepository.class);
		User u = new User();
		u.setId(23434L);
		u.setRole("MEMBER");
		ur.save(u);
		
		
	}

}
