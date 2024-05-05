package com.fdmgroup.gwbPlatformBulletin.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


/**
 * Represents a member/user of the platform.
 * 
 * @author Christine Nguyen
 * @version 1.0
 */

@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String honorific; // could be an enum? Queen, Princess, Lady, Mrs, Miss
	
//	@NotBlank(message = "First name cannot be blank")
	private String firstName;
	
//	@NotBlank(message = "Last name cannot be blank")
	private String lastName;
	
	@JsonIgnore // prevents circular return - Alice's issue
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	List<BulletinPost> postHistory = new ArrayList<>();
	
	public Member() {
		super();
	}
	
	public Member(String honorific, String firstName, String lastName, List<BulletinPost> postHistory) {
		
		super();
		this.honorific = honorific;
		this.firstName = firstName;
		this.lastName = lastName;
		this.postHistory = postHistory;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHonorific() {
		return honorific;
	}

	public void setHonorific(String honorific) {
		this.honorific = honorific;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getFullName() {

		StringBuilder fullName = new StringBuilder();

	    if (honorific != null && !honorific.isEmpty()) {
	        fullName.append(honorific.trim()).append(" ");
	    }

	    if (firstName != null && !firstName.isEmpty()) {
	        fullName.append(firstName.trim()).append(" ");
	    }

	    if (lastName != null && !lastName.isEmpty()) {
	        fullName.append(lastName.trim());
	    }

	    return fullName.toString().trim();
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", honorific=" + honorific + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", postHistory=" + postHistory + "]";
	}

}
