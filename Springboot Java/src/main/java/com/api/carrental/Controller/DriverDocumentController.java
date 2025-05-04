package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.api.carrental.Service.DriverDocumentService;
import com.api.carrental.model.DriverDocument;

import java.util.List;

@RestController
@RequestMapping("/api/driver-documents")
public class DriverDocumentController {

    @Autowired
    private DriverDocumentService driverDocumentService;

    @PostMapping
    public DriverDocument saveDocument(@RequestBody DriverDocument document) {
        return driverDocumentService.saveDocument(document);
    }

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
}
