package hcl.hackthon.interview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hcl.hackthon.interview.entity.BookIssue;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssue, Long>{
	Optional<BookIssue> findById(Long id);
	
}
