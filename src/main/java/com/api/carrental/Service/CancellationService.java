package com.api.carrental.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.CancellationRepository;

@Service
public class CancellationService {
	@Autowired
	private CancellationRepository cancellationRepository;

}
