package hcl.hackthon.interview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hcl.hackthon.interview.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findById(Long id);
}
