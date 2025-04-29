import React, { useState } from 'react';
import './CarDetails.css';
import { useNavigate } from 'react-router';
import axios from 'axios';

function SellCarVerification() {
  const [brand, setBrand] = useState(null);
  const [model, setModel] = useState(null);
  const [year, setYear] = useState(null);
  const [price, setPrice] = useState(null);
  const [description, setDescription] = useState(null);
  const navigate=useNavigate();
  // const addcars = async ($e) => {
  //   $e.preventDefault();

    // try {
    //     const response = await axios.post('http://localhost:8081/api/customer/add',
    //         {
    //             "brand": brand,
    //             "model": model,
    //             "year":year,
    //             "price":price,
    //             "description":description
    //         }
    //     )

    //     console.log('Car added Successfully....')
    // }
    // catch (err) {
    //     console.log(err)
    // }
    
//}
const carAdd=()=>{
  navigate("/CarAdded")
}
  return (
    <div className="registration-wrapper d-flex justify-content-center align-items-center vh-100">
      <div className="card p-5 registration-card w-75 overflow-auto" style={{ maxHeight: '90vh' }}>
        <h3 className="mb-4 text-white">Sell Your Car - Document Verification</h3>
        <form className="row g-3" onSubmit={($e) => addcars($e)}>
        <div className="row mb-3">
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Brand" 
            onChange={($event) => { setBrand($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Model" 
            onChange={($event) => { setModel($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Year" 
            onChange={($event) => { setYear($event.target.value) }}/>
          </div>
          <div className="col-md-6 mb-3">
            <input type="text" className="form-control" placeholder="Price" 
            onChange={($event) => { setPrice($event.target.value) }}/>
          </div>
          <div className="col-12 mb-4">
            <textarea className="form-control" placeholder="Description" rows="4"
            onChange={($event) => { setDescription($event.target.value) }}></textarea>
          </div>
        </div>

        <h5 className="text-white mb-3">Upload Images</h5>
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

        <h5 className="text-white mb-3">Required Documents</h5>

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
                <small className="text-muted">{doc.desc}</small>
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
