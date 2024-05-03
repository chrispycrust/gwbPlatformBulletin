package com.fdmgroup.gwbPlatformBulletin.model;

/**
 * 
 * Represents different types of members:
 * - internal team
 * - normal access
 * - host
 * - mentor
 * 
 * <p>1 member type can be associated with 0 or more members
 * 
 * @author Christine Nguyen
 * @version 1.0
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MemberType {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String typeName;
	
	public MemberType() {
		super();
	}

	public MemberType(String typeName) {
		
		super();
		this.typeName = typeName;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "MemberType [id=" + id + ", typeName=" + typeName + "]";
	}
	
	

}