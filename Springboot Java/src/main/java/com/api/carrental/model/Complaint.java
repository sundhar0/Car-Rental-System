package com.api.carrental.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complaintId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String issue;

    private String status;
    
    private LocalDate updatedAt = LocalDate.now();
    
    private String reponse;
    
	private String description;
	
	

	 public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public LocalDate getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDate updatedAt) {
			this.updatedAt = updatedAt;
		}
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}