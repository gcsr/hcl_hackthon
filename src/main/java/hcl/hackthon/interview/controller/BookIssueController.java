package hcl.hackthon.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcl.hackthon.interview.entity.BookIssue;
import hcl.hackthon.interview.service.BookIssueService;

@RestController
@RequestMapping("/bookissue")
public class BookIssueController {

	@Autowired
	private BookIssueService bookIssueService ;
	
	/*@PostMapping
	public BookIssue issueBook(@RequestBody UserModel userModel,
			@RequestBody BookModel bookModel) throws Exception{
		return bookIssueService.issueBook(bookModel, userModel);
	}*/
	
	@PostMapping("/user/{userId}/book/{bookISBN}")
	public BookIssue issueBook(@PathVariable long userId,
			@PathVariable long bookISBN) throws Exception{
		return bookIssueService.issueBook(bookISBN, userId);
	}
	
	@DeleteMapping("/user/{userId}/book/{bookISBN}")
	public BookIssue returnBook(@PathVariable long userId,
			@PathVariable long bookISBN) throws Exception{
		bookIssueService.deleteBookIssue(bookISBN, userId);
		return new BookIssue();
		
	}
	
}
