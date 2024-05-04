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
	
	Member findByFirstName(String name);

	Member save(Member member);

	@Query("select u from User u where upper(u.name) like concat('%', :input, '%')")
	List<Member> findByPartialMatch(@Param("input") String name);

	boolean existsByFullName(String fullName);

}
