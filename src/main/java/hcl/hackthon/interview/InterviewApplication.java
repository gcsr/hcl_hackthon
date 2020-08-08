package hcl.hackthon.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterviewApplication {

	private static final Logger logger = LoggerFactory.getLogger(InterviewApplication.class);

	public static void main(String[] args) {
		logger.info("this is a info message");
		logger.warn("this is a warn message");
		logger.error("this is a error message");
		logger.debug("this is debug message");
		SpringApplication.run(InterviewApplication.class, args);
	}

}
