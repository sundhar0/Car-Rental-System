package com.api.carrental.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.DriverDocumentRepository;
import com.api.carrental.Repository.DriverRepository;
import com.api.carrental.model.Driver;
import com.api.carrental.model.DriverDocument;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DriverDocumentService {

    @Autowired
    private DriverDocumentRepository driverDocumentRepository;
    
    @Autowired
    private DriverRepository driverRepository;
    
    @Autowired
    private DriverService driverService;
    
    private final Path rootLocation = Paths.get("D:/Documents/CarRental/driver-documents");


    public DriverDocument saveDocument(DriverDocument document, int driverId) throws InvalidIDException {
    	Driver driver = driverService.getById(driverId);
    	document.setDriver(driver);
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

	public void saveDocuments(int driverId, MultipartFile licenceDoc, MultipartFile aadhaarDoc,
			MultipartFile addressProofDoc) throws IOException {
        Files.createDirectories(rootLocation);

        Path licencePath = rootLocation.resolve(licenceDoc.getOriginalFilename());
        Path aadhaarPath = rootLocation.resolve(aadhaarDoc.getOriginalFilename());
        Path addressProofPath = rootLocation.resolve(addressProofDoc.getOriginalFilename());

        Files.copy(licenceDoc.getInputStream(), licencePath, StandardCopyOption.REPLACE_EXISTING);
        Files.copy(aadhaarDoc.getInputStream(), aadhaarPath, StandardCopyOption.REPLACE_EXISTING);
        Files.copy(addressProofDoc.getInputStream(), addressProofPath, StandardCopyOption.REPLACE_EXISTING);

        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Driver not found"));
        DriverDocument driverDocument = new DriverDocument();
        driverDocument.setDriver(driver);
        driverDocument.setDrivingLicence(licencePath.toString());
        driverDocument.setAadhaarCard(aadhaarPath.toString());
        driverDocument.setAddressProof(addressProofPath.toString());

        driverDocumentRepository.save(driverDocument);		
	}

	
    
    
}
