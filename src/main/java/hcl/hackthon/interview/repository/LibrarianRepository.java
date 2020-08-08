package hcl.hackthon.interview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hcl.hackthon.interview.entity.Librarian;



@Repository
public interface LibrarianRepository extends CrudRepository<Librarian, Integer> {
	@Query("select c from Librarian c where c.email = :email")
	public Librarian getLibrarianByMailId(@Param("email") String email);

	@Query("select c from Librarian c where c.userId = :userId and c.password= :password")
	public Librarian getLibrarianByUserNameAndPassword(@Param("userId") String userId,
			@Param("password") String password);
	
	@Query("select c from Librarian c where c.fName = :fName")
	public Librarian getLibrarianByName(@Param("fName")String  fName);

}