import React, { useEffect, useState } from "react";
import "./CarOverview.css";
import { Link, useNavigate, useParams } from "react-router-dom";
import axios from "axios";

function CarOverview() {
  const { id } = useParams();
  const [car, setCar] = useState({});

  const fetchCars = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/car/getById/${id}`
      );
      setCar(response.data);
    } catch (error) {
      console.error("Error fetching cars:", error);
    }
  };

  useEffect(() => {
    fetchCars();
  }, []);

  return (
    <div className="" style={{ minHeight: "100vh", backgroundColor: "#F8F9FA" }}>
      <nav
        className="navbar navbar-expand-lg mb-4 p-3"
        style={{ backgroundColor: "#1C2631" }}
      >
        <div className="container">
          
          <div className="d-flex align-items-center gap-3 ms-auto">
            <Link to="/BuyerDashboard" className="btn btn-outline-light">
              Back to Listings
            </Link>
          </div>
        </div>
      </nav>

      <div className="container mb-5">
        <div className="card border-0 shadow-sm mb-4">
          <div className="card-body p-4">
            <div className="row">
              <div className="col-md-6">
                {/* Car Image Placeholder - Replace with actual image */}
                <div
                  className="bg-light rounded"
                  style={{ height: "300px", position: "relative" }}
                >
                  <div
                    className="w-100 h-100 d-flex align-items-center justify-content-center"
                    style={{ backgroundColor: "#E9ECEF" }}
                  >
                    <i
                      className="bi bi-car-front fs-1"
                      style={{ color: "#6C757D" }}
                    ></i>
                  </div>
                </div>
              </div>
              <div className="col-md-6">
                <h2 className="fw-bold" style={{ color: "#253343" }}>
                  {car.carMake} {car.model}
                </h2>
                <div className="d-flex align-items-center gap-3 mb-3">
                  <span className="badge bg-primary">{car.year}</span>
                  <span className="text-muted">{car.mileage} miles</span>
                </div>
                <h3 className="mb-4" style={{ color: "#00B86B" }}>
                  ${car.price}
                </h3>

                <div className="row mb-4">
                  <div className="col-6 mb-3">
                    <div className="d-flex align-items-center gap-2">
                      <i
                        className="bi bi-gear-fill"
                        style={{ color: "#6C757D" }}
                      ></i>
                      <span>{car.transmission}</span>
                    </div>
                  </div>
                  <div className="col-6 mb-3">
                    <div className="d-flex align-items-center gap-2">
                      <i
                        className="bi bi-fuel-pump-fill"
                        style={{ color: "#6C757D" }}
                      ></i>
                      <span>{car.fuelType}</span>
                    </div>
                  </div>
                  <div className="col-6 mb-3">
                    <div className="d-flex align-items-center gap-2">
                      <i
                        className="bi bi-speedometer2"
                        style={{ color: "#6C757D" }}
                      ></i>
                      <span>{car.mileage} miles</span>
                    </div>
                  </div>
                  <div className="col-6 mb-3">
                    <div className="d-flex align-items-center gap-2">
                      <i
                        className="bi bi-palette-fill"
                        style={{ color: "#6C757D" }}
                      ></i>
                      <span>{car.color || "N/A"}</span>
                    </div>
                  </div>
                </div>

                <div className="d-grid gap-2 mb-4">
                  <Link
                    to={`/testDrive/${car.id}`}
                    className="btn text-white fw-bold"
                    style={{ backgroundColor: "#00B86B" }}
                  >
                    Schedule Test Drive
                  </Link>
                  <button className="btn btn-outline-secondary">
                    Contact Seller
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="row g-4">
          <div className="col-lg-8">
            <div className="card border-0 shadow-sm">
              <div className="card-body">
                <h4 className="card-title mb-4" style={{ color: "#253343" }}>
                  Description
                </h4>
                <p className="text-muted">{car.description}</p>
              </div>
            </div>

            <div className="card border-0 shadow-sm mt-4">
              <div className="card-body">
                <div className="d-flex justify-content-between align-items-center mb-4">
                  <h4 className="card-title mb-0" style={{ color: "#253343" }}>
                    Reviews & Ratings
                  </h4>
                  <button
                    className="btn btn-sm text-white"
                    style={{ backgroundColor: "#00B86B" }}
                  >
                    Submit Review
                  </button>
                </div>

                <div className="review-card p-3 mb-3 rounded" style={{ backgroundColor: "#F8F9FA" }}>
                  <div className="d-flex justify-content-between align-items-center mb-2">
                    <h6 className="mb-0 fw-bold">John Doe</h6>
                    <div className="text-warning">★★★★★</div>
                  </div>
                  <p className="text-muted mb-0">
                    Great seller! Very professional and honest throughout the
                    process.
                  </p>
                </div>

                <div className="review-card p-3 rounded" style={{ backgroundColor: "#F8F9FA" }}>
                  <div className="d-flex justify-content-between align-items-center mb-2">
                    <h6 className="mb-0 fw-bold">Jane Smith</h6>
                    <div className="text-warning">★★★★☆</div>
                  </div>
                  <p className="text-muted mb-0">
                    The car was exactly as described. Smooth transaction.
                  </p>
                </div>
              </div>
            </div>
          </div>

          <div className="col-lg-4">
            <div className="card border-0 shadow-sm">
              <div className="card-body">
                <h4 className="card-title mb-4" style={{ color: "#253343" }}>
                  Report & Support
                </h4>

                <div className="mb-4">
                  <div className="p-3 rounded mb-3" style={{ backgroundColor: "#F8F9FA" }}>
                    <h6 className="fw-bold mb-3">Report Fraud</h6>
                    <textarea
                      className="form-control mb-3"
                      placeholder="Describe the issue..."
                      rows="4"
                    ></textarea>
                    <div className="d-grid">
                      <button
                        className="btn text-white fw-bold"
                        style={{ backgroundColor: "#DC3545" }}
                      >
                        Submit Report
                      </button>
                    </div>
                  </div>
                </div>

                <div className="p-3 rounded" style={{ backgroundColor: "#F8F9FA" }}>
                  <h6 className="fw-bold mb-3">Help Center</h6>
                  <ul className="list-unstyled text-muted">
                    <li className="mb-2">
                      <i className="bi bi-question-circle-fill me-2"></i> Common
                      Questions
                    </li>
                    <li className="mb-2">
                      <i className="bi bi-headset me-2"></i> Contact Support
                    </li>
                    <li className="mb-2">
                      <i className="bi bi-info-circle-fill me-2"></i> FAQs
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CarOverview;