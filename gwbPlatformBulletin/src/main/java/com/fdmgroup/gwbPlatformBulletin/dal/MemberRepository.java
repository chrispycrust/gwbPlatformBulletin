package com.fdmgroup.gwbPlatformBulletin.dal;

//import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.gwbPlatformBulletin.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	// findById(Integer id);
	
	Optional<Member> findByFirstName(String name);

	Optional<Member> save(Optional<Member> member);

}


// SpringBeans example

//@Repository
//public interface UserRepository extends JpaRepository<User,Integer> {
//
//	@Query("select u from User u where upper(u.name) like concat('%', :input, '%')")
//	List<Member> findByPartialMatch(@Param("input") String name);
//	
//	boolean existsByName(String name);
//	
//}
