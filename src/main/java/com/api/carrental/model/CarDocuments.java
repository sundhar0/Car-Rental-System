package com.api.carrental.model;

import jakarta.persistence.*;

@Entity
public class CarDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentId;

<<<<<<< HEAD
    @ManyToOne
    private Car car;

=======
>>>>>>> 477a9c7d1f831c0507906241b73e6083a73153db
    private String documentType;

    private String documentUrl;

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentUrl() {
		return documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}
    
}
