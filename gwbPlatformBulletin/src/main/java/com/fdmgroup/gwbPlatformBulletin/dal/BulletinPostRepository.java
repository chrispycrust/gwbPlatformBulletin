package com.fdmgroup.gwbPlatformBulletin.dal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;
import com.fdmgroup.gwbPlatformBulletin.model.Member;

@Repository
public interface BulletinPostRepository extends JpaRepository<BulletinPost, Integer> {
	
	Optional<BulletinPost> save(Optional<BulletinPost> bulletinPost);

}