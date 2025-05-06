package com.api.carrental.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.api.carrental.Repository.DriverDocumentRepository;
import com.api.carrental.model.DriverDocument;

import java.util.List;

@Service
public class DriverDocumentService {

    @Autowired
    private DriverDocumentRepository driverDocumentRepository;

    public DriverDocument saveDocument(DriverDocument document) {
        return driverDocumentRepository.save(document);
    }

    public List<DriverDocument> getAllDocuments() {
        return driverDocumentRepository.findAll();
    }

    public DriverDocument getById(int id) {
        return driverDocumentRepository.findById(id).orElse(null);
    }

    public DriverDocument getByDriverId(int driverId) {
        return driverDocumentRepository.findByDriverDriverId(driverId);
    }

    public void deleteById(int id) {
        driverDocumentRepository.deleteById(id);
    }
}
