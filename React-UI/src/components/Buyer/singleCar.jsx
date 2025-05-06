import React from 'react';
import './CarOverview.css';
import { useNavigate } from 'react-router';

function CarOverview() {
  const navigate = useNavigate();

  const testDrive = () => {
    navigate("/testDrive");
  };

  return (
    <div className="registration-wrapper d-flex justify-content-center align-items-center vh-100 bg-light">
      <div className="card registration-card shadow-lg p-4 w-75 overflow-auto rounded-4" style={{ maxHeight: '90vh' }}>
        
        <div className="mb-4 border-bottom pb-3">
          <h3 className="text-dark fw-bold">BMW 3 Series</h3>
          <p className="text-muted mb-1">2023 • 1,200 miles</p>
          <h4 className="text-success fw-semibold">$45,900</h4>
        </div>

        <div className="row text-secondary mb-4">
          <div className="col-md-6 mb-2"><strong>Engine:</strong> 2.0L 4-cylinder</div>
          <div className="col-md-6 mb-2"><strong>Transmission:</strong> 8-speed Automatic</div>
          <div className="col-md-6 mb-2"><strong>Fuel Type:</strong> Gasoline</div>
          <div className="col-md-6 mb-2"><strong>Drive Type:</strong> RWD</div>
        </div>

        <div className="d-grid gap-3 mb-4">
          <button className="btn btn-success" onClick={testDrive}>Schedule Test Drive</button>
          <button className="btn btn-outline-secondary">Contact Seller</button>
        </div>

        <div className="mb-4">
          <h5 className="text-dark fw-semibold">Description</h5>
          <p className="text-secondary">
            This BMW 3 Series is in excellent condition with full service history. Features include leather seats, panoramic sunroof, navigation system, and premium sound.
          </p>
        </div>

        <div className="mb-4">
          <h5 className="text-dark fw-semibold mb-3">Reviews & Ratings</h5>
          <div className="review-card bg-secondary-subtle rounded-3 p-3 mb-3">
            <h6 className="text-dark mb-1">John Doe</h6>
            <div className="text-warning mb-1">★★★★★</div>
            <p className="text-dark mb-0">Great seller! Very professional and honest throughout the process.</p>
          </div>
          <button className="btn btn-outline-success w-100">Submit Review</button>
        </div>

        <div>
          <h5 className="text-dark fw-semibold mb-3">Report & Support</h5>
          <div className="row">
            <div className="col-md-6 mb-3">
              <div className="support-card bg-danger-subtle rounded-3 p-3 h-100">
                <h6 className="text-dark mb-2">Report Fraud</h6>
                <textarea className="form-control mb-2" placeholder="Describe the issue..." rows="4"></textarea>
                <button className="btn btn-danger w-100">Submit Report</button>
              </div>
            </div>
            <div className="col-md-6">
              <div className="support-card bg-info-subtle rounded-3 p-3 h-100">
                <h6 className="text-dark mb-3">Help Center</h6>
                <ul className="list-unstyled text-dark">
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
