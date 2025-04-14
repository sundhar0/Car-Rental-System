package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.PaymentService;
import com.api.carrental.dto.MessageResponseDto;
import com.api.carrental.dto.PaymentRequestDto;
import com.api.carrental.dto.PaymentResponseDto;

@RestController
@RequestMapping("/api/pay")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private MessageResponseDto messageDto;
	
	@PostMapping("/initiate")
    public ResponseEntity<?> initiatePayment(@RequestBody PaymentRequestDto dto) {
        try {
            PaymentResponseDto response = paymentService.initiatePayment(dto);
            return ResponseEntity.status(201).body(response);
        } catch (InvalidIDException e) {
            messageDto.setStatusCode(400);
            messageDto.setBody(e.getMessage());
            return ResponseEntity.badRequest().body(messageDto);
        } catch (Exception e) {
            messageDto.setStatusCode(500);
            messageDto.setBody("Something went wrong");
            return ResponseEntity.status(500).body(messageDto);
        }
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable int id) {
        try {
            PaymentResponseDto response = paymentService.getPaymentById(id);
            return ResponseEntity.ok(response);
        } catch (InvalidIDException e) {
            messageDto.setStatusCode(404);
            messageDto.setBody(e.getMessage());
            return ResponseEntity.status(404).body(messageDto);
        } catch (Exception e) {
            messageDto.setStatusCode(500);
            messageDto.setBody("Something went wrong");
            return ResponseEntity.status(500).body(messageDto);
        }
    }
	
	@PutMapping("/confirm/{id}")
	public ResponseEntity<?> confirmPayment(@PathVariable int id) {
	    try {
	        PaymentResponseDto response = paymentService.confirmPayment(id);
	        return ResponseEntity.ok(response);
	    } catch (InvalidIDException e) {
	        messageDto.setStatusCode(404);
	        messageDto.setBody(e.getMessage());
	        return ResponseEntity.status(404).body(messageDto);
	    } catch (Exception e) {
	        messageDto.setStatusCode(500);
	        messageDto.setBody("Something went wrong");
	        return ResponseEntity.status(500).body(messageDto);
	    }
	}

	@GetMapping("/verify/{id}")
	public ResponseEntity<?> verifyPayment(@PathVariable int id) {
	    try {
	        String result = paymentService.verifyPayment(id);
	        messageDto.setStatusCode(200);
	        messageDto.setBody(result);
	        return ResponseEntity.ok(messageDto);
	    } catch (InvalidIDException e) {
	        messageDto.setStatusCode(404);
	        messageDto.setBody(e.getMessage());
	        return ResponseEntity.status(404).body(messageDto);
	    } catch (Exception e) {
	        messageDto.setStatusCode(500);
	        messageDto.setBody("Something went wrong");
	        return ResponseEntity.status(500).body(messageDto);
	    }
	}


}
