package com.api.carrental.model;

import jakarta.persistence.*;

@Entity
public class CustomerCare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supportId;

    private String issueType;

    private String message;

	public int getSupportId() {
		return supportId;
	}

	public void setSupportId(int supportId) {
		this.supportId = supportId;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
