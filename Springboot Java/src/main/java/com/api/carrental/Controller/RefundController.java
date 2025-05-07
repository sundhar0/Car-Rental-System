package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.RefundService;
import com.api.carrental.dto.MessageResponseDto;
import com.api.carrental.dto.RefundRequsetDto;
import com.api.carrental.dto.RefundResponseDto;

@RestController
@RequestMapping("/api/refund")
public class RefundController {
	
	 @Autowired
	    private RefundService refundService;

	    @Autowired
	    private MessageResponseDto messageDto;

	    // Initiate a new refund
	    @PostMapping("/add/{paymentId}")
	    public ResponseEntity<?> initiateRefund(@PathVariable int paymentId, @RequestBody RefundRequsetDto request) {
	        try {
	            RefundResponseDto response = refundService.initiateRefund(paymentId, request);
	            return ResponseEntity.status(400).body(response);
	        } catch (InvalidIDException e) {
	            messageDto.setStatusCode(400);
	            messageDto.setBody(e.getMessage());
	            return ResponseEntity.badRequest().body(messageDto);
	        } catch (Exception e) {
	            messageDto.setStatusCode(400);
	            messageDto.setBody("Something went wrong");
	            return ResponseEntity.status(400).body(messageDto);
	        }
	    }
	

}
