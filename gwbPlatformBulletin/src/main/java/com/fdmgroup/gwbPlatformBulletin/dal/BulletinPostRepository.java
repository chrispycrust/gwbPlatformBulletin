package com.fdmgroup.gwbPlatformBulletin.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;
import com.fdmgroup.gwbPlatformBulletin.model.Member;

@Repository
public interface BulletinPostRepository extends JpaRepository<BulletinPost, Integer> {
	
	List<BulletinPost> findAll(Sort sort);
	
	Optional<BulletinPost> save(Optional<BulletinPost> bulletinPost);

	List<BulletinPost> findByAuthorId(Integer authorId, Sort sort);
	

//	public List<BulletinPost> findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String searchTerm, String searchTerm2, String searchTerm3);
	
	

}
