package com.fdmgroup.gwbPlatformBulletin.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BulletinPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
//	@Autowired
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private Member author;
	
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime datePublished;

	public BulletinPost() {
		super();
	}
	
	public BulletinPost(Member author, String title, String content) {
		
		super();
		this.author = author;
		this.title = title;
		this.content = content;
		this.datePublished = LocalDateTime.now();

	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
    public String getAuthorHonorific() {
        return author != null ? author.getHonorific() : null;
    }
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
    public String getAuthorFirstName() {
        return author != null ? author.getFirstName() : null;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getAuthorLastName() {
        return author != null ? author.getLastName() : null;
    }
    
    @JsonProperty("author")
    public List<String> getAuthorDetails() {
    	
        List<String> authorDetails = new ArrayList<>();
        
        authorDetails.add(getAuthorHonorific());
        
        if ( getAuthorFirstName().length() == 0 ) {
        	authorDetails.add(getAuthorLastName());
        	
        } else if ( getAuthorLastName().length() != 0 ) {
        	authorDetails.add(getAuthorLastName());

        } else {
        	authorDetails.add(getAuthorFirstName());
        }

        return authorDetails;
    }
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Member getAuthor() {
		return author;
	}
	
	public void setAuthor(Member author) {
		this.author = author;
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

	@Override
	public String toString() {
		return "BulletinPost [id=" + id + ", author=" + author + ", title=" + title + ", content=" + content
				+ ", datePublished=" + datePublished + "]";
	}


}
