import React, { useState } from 'react';
import './CarDetails.css';
import { useNavigate } from 'react-router';
import axios from 'axios';

function SellCarVerification() {
  const [carMake, setCarMake] = useState('');
  const [carModel, setCarModel] = useState('');
  const [year, setYear] = useState('');
  const [licensePlateNumber, setLicensePlateNumber] = useState('');
  const [vehicleRegistrationNumber, setVehicleRegistrationNumber] = useState('');
  const [carColor, setCarColor] = useState('');
  const [price, setPrice] = useState('');
  const [brand, setBrand] = useState('');
  const [fuelType, setFuelType] = useState('');
  const [transmission, setTransmission] = useState('');
  const [mileage, setMileage] = useState('');
  const [showAlert, setShowAlert] = useState(false);

  const navigate = useNavigate(); 
  const addcars = async ($e) => {
    e.preventDefault();

    // Assuming the user's token is stored in localStorage, retrieve it.
    const token = localStorage.getItem('token');
    if (!token) {
      alert('No token found!');
      return;
    }

    // You can use the token directly for authorization and let the server handle user identification
    const carData = {
      carMake,
      carModel,
      year,
      licensePlateNumber,
      vehicleRegistrationNumber,
      carColor,
      price,                   // Price field
      brand,                   // Brand field
      fuelType,                // Fuel type field
      transmission,            // Transmission field
      mileage,                 // Mileage field
    };

    try {
      const response = await axios.post('http://localhost:8081/api/cars/add', carData, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      console.log('Car added successfully:', response.data);
      setShowAlert(true); 
    } catch (error) {
      console.error('Error adding car:', error);
      alert('Failed to submit car. Please try again.');
    }
  };
  

const carAdd = () => {
    setShowAlert(false);
    navigate("/CarAdded") 
  };
  return (
    <div className="registration-wrapper d-flex justify-content-center align-items-center vh-100">
      <div className="card p-5 registration-card w-75 overflow-auto" style={{ maxHeight: '90vh' }}>
        <h3 className="mb-4 text-black">Sell Your Car - Document Verification</h3>
        <form className="row g-3" onSubmit={($e) => addcars($e)}>
        <div className="row mb-3">
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Car Make" 
            onChange={($event) => { setCarMake($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Car Model" 
            onChange={($event) => { setCarModel($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Year" 
            onChange={($event) => { setYear($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Licence Plate Number" 
            onChange={($event) => { setLicensePlateNumber($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Vehicle Registration Number" 
            onChange={($event) => { setVehicleRegistrationNumber($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Car Color" 
            onChange={($event) => { setCarColor($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Price" 
            onChange={($event) => { setPrice($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Brand" 
            onChange={($event) => { setBrand($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Fuel Type" 
            onChange={($event) => { setFuelType($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Transmission" 
            onChange={($event) => { setTransmission($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Mileage" 
            onChange={($event) => { setMileage($event.target.value) }}/>
          </div>
        </div>

        <h5 className="text-black mb-3">Upload Images</h5>
        <div className="mb-4">
          <div className="upload-box text-center">
            <i className="bi bi-upload fs-2"></i>
            <p className="mb-0">Upload files</p>
            <small>PNG, JPG, GIF up to 10MB</small>
          </div>
        </div>

        <div className="d-grid mb-5">
          <button className="btn btn-primary" onClick={()=>carAdd()}>Submit for verification</button>
        </div>

        <h5 className="text-black mb-3">Required Documents</h5>

        <div className="required-docs">
          {[
            { label: 'RC Book', desc: 'Upload valid registration certificate' },
            { label: 'Insurance Certificate', desc: 'Upload valid insurance document' },
            { label: 'Pollution Certificate (PUC)', desc: 'Upload valid PUC certificate' },
            { label: 'Service History', desc: 'Upload service records' },
            { label: 'NOC (if applicable)', desc: 'Upload No Objection Certificate' },
          ].map((doc, idx) => (
            <div className="doc-item d-flex justify-content-between align-items-center p-3 mb-3" key={idx}>
              <div>
                <h6 className="mb-1 text-white">{doc.label}</h6>
                <small className="text-white">{doc.desc}</small>
              </div>
              <button className="btn btn-dark">Upload</button>
            </div>
          ))}
        </div>
        </form>
      </div>
    </div>
  );
}

export default SellCarVerification;
