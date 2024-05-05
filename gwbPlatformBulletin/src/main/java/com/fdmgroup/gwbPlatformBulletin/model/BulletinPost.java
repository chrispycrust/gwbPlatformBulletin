package com.fdmgroup.gwbPlatformBulletin.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;

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
import jakarta.validation.constraints.NotBlank;

@Entity
public class BulletinPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 *  @JsonIgnore
	 *  Avoids serialising complete Member entity when BulletinPost objects are sent to the frontend
	 *  Avoids unnecessary data exposure and to minimise the payload
	 *  @ManyToOne
	 *  There can be many posts to an author
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private Member author;
	
	/**
	 * @NotBlank 
	 * ensures a field is not null, empty, or just whitespace
	 * Immediately returns message to the application user
	 * Server-side validation 
	 */
	@NotBlank(message = "Title cannot be blank")
	@Column(nullable = false)
	private String title;
	
	/**
	 * Column definition set tO TEXT allows for longer information exceeding VARCHAR(255) in SQL database
	 */
	@NotBlank(message = "Content cannot be blank")
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	/**
	 * sets the datePublished field to the current date and time when the object is instantiated
	 * before the constructor's body is executed
	 */
	private LocalDateTime datePublished = LocalDateTime.now();
	// TODO account for different time zones in future

	public BulletinPost() {
	}
	
	public BulletinPost(Member author, String title, String content) {
		
		this.author = author;
		this.title = title;
		this.content = content;

	}
	
	/**
	 * Helper method to specify full name of author along with other post properties 
	 * Should appear with key "authorFullName" when BulletinPost instances are serialised for front end
	 * 
	 * @return String
	 */
	@JsonProperty("authorFullName")
    public String getAuthorFullName() {
		
		if (author != null ) {
			return author.getFullName();
		} else {
			return "";
		}
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
