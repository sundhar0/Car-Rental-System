package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Service.CancellationService;

@RestController
@RequestMapping("/api/cancel")
public class CancellationController {
	@Autowired
	private CancellationService cancellationService;
	

}
