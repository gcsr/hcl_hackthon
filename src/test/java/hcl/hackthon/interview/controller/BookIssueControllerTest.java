package hcl.hackthon.interview.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import hcl.hackthon.interview.entity.Book;
import hcl.hackthon.interview.entity.User;
import hcl.hackthon.interview.repository.BookRepository;
import hcl.hackthon.interview.repository.UserRepository;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class BookIssueControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	//@Autowired
	//private static ApplicationContext applicationContext;
	/*@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext ;
		
	}
	
	@BeforeAll
	public static void setup() {
		BookRepository br = (BookRepository)applicationContext.getBean(BookRepository.class);
		br.deleteAll();
		Book b = new Book();
		b.setIsbnNo(123L);
		b.setQty(12);
		br.save(b);
		
		UserRepository ur = (UserRepository)applicationContext.getBean(UserRepository.class);
		ur.deleteAll();
		User u = new User();
		u.setId(23434L);
		u.setRole("MEMBER");
		ur.save(u);
	}*/

	//private final String BOOKISSUE="bookissue/";
	@Test
	public void testGet() throws Exception {
		String url = "http://localhost:"+port+"/bookissue/user/1/book/1";
		this.restTemplate.delete(url);
		assertThat(this.restTemplate.postForObject(url,null,
				String.class)).contains("1");
		assertThat(this.restTemplate.getForObject(url,
				String.class)).contains("1");
	}
	
	@Test
	public void Delete() throws Exception {
		String url = "http://localhost:"+port+"/bookissue/user/1/book/1";
		this.restTemplate.delete(url);
		assertThat(this.restTemplate.postForObject(url,null,
				String.class)).contains("1");
		this.restTemplate.delete(url);
		assertThat(this.restTemplate.getForObject(url,
				String.class)).doesNotContain("1");
	}
	
	@Test
	public void testPost() throws Exception {
		
		String url = "http://localhost:"+port+"/bookissue/user/1/book/1";
		this.restTemplate.delete(url);
		assertThat(this.restTemplate.postForObject(url,null,
				String.class)).contains("1");
	
	}
	
}
