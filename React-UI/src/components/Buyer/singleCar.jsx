import React from 'react';
import './CarOverview.css';
import { useNavigate } from 'react-router';

function CarOverview() {
  const navigate=useNavigate();
    const testDrive=()=>{
        navigate("/testDrive")
    }
  return (
    <div className="registration-wrapper d-flex justify-content-center align-items-center vh-100">
      <div className="card p-5 registration-card w-75 overflow-auto" style={{ maxHeight: '90vh' }}>
        
        <h3 className="mb-3 text-white">BMW 3 Series</h3>
        <p className="text-white mb-1">2023 • 1,200 miles</p>
        <h4 className="text-primary mb-4">$45,900</h4>

        <div className="row text-white mb-5">
          <div className="col-md-6 mb-3">
            <strong>Engine:</strong> 2.0L 4-cylinder
          </div>
          <div className="col-md-6 mb-3">
            <strong>Transmission:</strong> 8-speed Automatic
          </div>
          <div className="col-md-6 mb-3">
            <strong>Fuel Type:</strong> Gasoline
          </div>
          <div className="col-md-6 mb-3">
            <strong>Drive Type:</strong> RWD
          </div>
        </div>

        <div className="d-grid gap-2 mb-5">
          <button className="btn btn-primary" onClick={()=>testDrive()}>Schedule Test Drive</button>
          <button className="btn btn-outline-light">Contact Seller</button>
        </div>

        <div className="mb-5">
          <h5 className="text-white">Description</h5>
          <p className="text-white">
            This BMW 3 Series is in excellent condition with full service history. Features include leather seats, panoramic sunroof, navigation system, and premium sound.
          </p>
        </div>

        <div className="mb-5">
          <h5 className="text-white mb-4">Reviews & Ratings</h5>
          <div className="review-card p-3 mb-3">
            <h6 className="text-white mb-1">John Doe</h6>
            <div className="text-warning mb-2">
              ★★★★★
            </div>
            <p className="text-white mb-0">
              Great seller! Very professional and honest throughout the process.
            </p>
          </div>
          <div className="d-grid">
            <button className="btn btn-primary">Submit Review</button>
          </div>
        </div>

        <div className="mb-3">
          <h5 className="text-white mb-4">Report & Support</h5>
          <div className="row">
            <div className="col-md-6 mb-4">
              <div className="support-card p-3 h-100">
                <h6 className="text-white mb-2">Report Fraud</h6>
                <textarea className="form-control mb-3" placeholder="Describe the issue..." rows="4"></textarea>
                <div className="d-grid">
                  <button className="btn btn-primary">Submit Report</button>
                </div>
              </div>
            </div>
            <div className="col-md-6">
              <div className="support-card p-3 h-100">
                <h6 className="text-white mb-3">Help Center</h6>
                <ul className="list-unstyled text-white">
                  <li>• Common Questions</li>
                  <li>• Contact Support</li>
                  <li>• FAQs</li>
                </ul>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  );
}

export default CarOverview;
