package com.fdmgroup.gwbPlatformBulletin.dal;

import java.util.List;
//import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.gwbPlatformBulletin.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	List<Member> findByFirstName(String name);
	
	Optional<Member> findByEmail(String username);
	
	@Query("SELECT m FROM Member m WHERE LOWER(CONCAT(m.honorific, ' ', m.firstName, ' ', m.lastName)) LIKE :searchTerm")
	List<Member> findBySearchTerm(@Param("searchTerm") String searchTerm);
	
	@Query("SELECT COUNT(m) > 0 FROM Member m WHERE CONCAT(m.honorific, ' ', m.firstName, ' ', m.lastName) = LOWER(:fullName)")
    boolean existsByFullName(@Param("fullName") String fullName);

//	Optional<Member> findByName(String name);

//	Optional<Member> findByName(String username);
	// commenting this out makes spring happy

}
