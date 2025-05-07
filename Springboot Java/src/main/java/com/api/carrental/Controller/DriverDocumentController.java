package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.DriverDocumentService;
import com.api.carrental.model.Driver;
import com.api.carrental.model.DriverDocument;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/driver-documents")
public class DriverDocumentController {

    @Autowired
    private DriverDocumentService driverDocumentService;

   

    @GetMapping
    public List<DriverDocument> getAllDocuments() {
        return driverDocumentService.getAllDocuments();
    }

    @GetMapping("/{id}")
    public DriverDocument getById(@PathVariable int id) {
        return driverDocumentService.getById(id);
    }

    @GetMapping("/driver/{driverId}")
    public DriverDocument getByDriverId(@PathVariable int driverId) {
        return driverDocumentService.getByDriverId(driverId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        driverDocumentService.deleteById(id);
    }
    
    @PostMapping("/{driverId}")
    public ResponseEntity<String> uploadDocuments(@PathVariable int driverId,
                                                  @RequestParam MultipartFile licenceDoc,
                                                  @RequestParam MultipartFile aadhaarDoc,
                                                  @RequestParam MultipartFile addressProofDoc) throws IOException {
            driverDocumentService.saveDocuments(driverId, licenceDoc, aadhaarDoc, addressProofDoc);
            return ResponseEntity.ok("Documents uploaded successfully");
        
    }

    
}
