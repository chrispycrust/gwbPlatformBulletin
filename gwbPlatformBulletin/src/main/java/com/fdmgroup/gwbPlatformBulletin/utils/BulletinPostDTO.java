package com.fdmgroup.gwbPlatformBulletin.utils;

import java.time.LocalDateTime;

import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;

/**
 * Allows us to get the author's full name from Member object without using JSON in the BulletinPost class
 */

public class BulletinPostDTO {
	
	private Integer id;
    private String title;
    private String content;
    private LocalDateTime datePublished;
    private String authorFullName;

    public BulletinPostDTO(BulletinPost post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.datePublished = post.getDatePublished();
        this.authorFullName = post.getAuthor().getFullName(); 
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(LocalDateTime datePublished) {
		this.datePublished = datePublished;
	}

	public String getAuthorFullName() {
		return authorFullName;
	}

	public void setAuthorFullName(String authorFullName) {
		this.authorFullName = authorFullName;
	}

    
}
