package com.api.carrental.Controller;



import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidPaymentException;
import com.api.carrental.Service.DriverPaymentService;
import com.api.carrental.model.DriverPayment;

@RestController
@RequestMapping("/api/driverPayment")
public class DriverPaymentController {
	
	@Autowired
	private DriverPaymentService driverPaymentService;
	
	@PostMapping("/addPayment")
	public DriverPayment add(@RequestBody DriverPayment driverPayment,
			@RequestParam int driverId, int managerId) throws InvalidIDException {
		return driverPaymentService.add(driverPayment, managerId, driverId);
	}
	
	@GetMapping("/getAll")
	public List<DriverPayment> getAll(){
		return driverPaymentService.getAll();
	}
	
	@GetMapping("/getById")
	public DriverPayment getById(@PathVariable int driverPayId) throws InvalidIDException {
		return driverPaymentService.getById(driverPayId);
	}
	
	@GetMapping("/getByDriverName")
	public List<DriverPayment> getByDriverName(@RequestParam String driverName) throws InvalidPaymentException{
		return driverPaymentService.getByDriverName(driverName);
	}
	
	@GetMapping("/getByDriverId")
	public DriverPayment getByDriverId(@PathVariable int driverId) throws InvalidPaymentException {
		return driverPaymentService.getByDriverId(driverId);
	}
	
	@GetMapping("getByDate")
	public List<DriverPayment> getByDate(@RequestParam LocalDate date) throws InvalidPaymentException {
		return driverPaymentService.getByDate(date);
	}
	
	@PutMapping("/updatePayment")
	public String updatePayment(@RequestParam double payment, 
			@PathVariable int driverId, @PathVariable int managerId) throws InvalidPaymentException, InvalidIDException {
		return driverPaymentService.updatePayment(payment, driverId, managerId);
	}
	
	@DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deleteDriverPayment(@PathVariable int paymentId) throws InvalidIDException {
       
            driverPaymentService.deleteDriverPayment(paymentId);
            return ResponseEntity.ok("Driver Payment deleted successfully");
        
    }
}
