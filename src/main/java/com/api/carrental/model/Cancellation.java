package com.api.carrental.model;

import jakarta.persistence.*;

@Entity
public class Cancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cancellationId;
    
    private String reason;

    private String cancelledDate;

	public int getCancellationId() {
		return cancellationId;
	}

	public void setCancellationId(int cancellationId) {
		this.cancellationId = cancellationId;
	}


	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCancelledDate() {
		return cancelledDate;
	}

	public void setCancelledDate(String cancelledDate) {
		this.cancelledDate = cancelledDate;
	}
    
    
}
