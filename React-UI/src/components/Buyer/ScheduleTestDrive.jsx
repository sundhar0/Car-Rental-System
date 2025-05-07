import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import './ScheduleTestDrive.css';
import { Link } from 'react-router';

function ScheduleTestDrive() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [carDetails, setCarDetails] = useState(null);
    const [formData, setFormData] = useState([]);
    const [customerId, setCustomerId] = useState(localStorage.getItem('userId'));
    

    useEffect(() => {
        // Fetch car details
        const fetchCarDetails = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/car/getById/${id}`);
                setCarDetails(response.data);
            } catch (error) {
                console.error('Error fetching car details:', error);
            }
        };

        fetchCarDetails();
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const confirmBooking = async (e) => {
        e.preventDefault();
        try {
            const testDriveData = {
                bookingDate: formData.date,
                time: formData.time,
                location: formData.location,
            };

            const response = await axios.post(
                `http://localhost:8080/api/booking/add/{id}/1`,
                testDriveData,
                {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem('token')}`,
                    }
                }
            );

            navigate('/booking', { state: { bookingDetails: response.data } });
        } catch (error) {
            console.error('Error scheduling test drive:', error);
            alert('Failed to schedule test drive. Please try again.');
        }
    };

    return (
        <div className="" style={{ minHeight: "100vh", backgroundColor: "#F8F9FA" }}>
            <nav
                className="navbar navbar-expand-lg mb-4 p-3"
                style={{ backgroundColor: "#1C2631" }}
            >
                <div className="container">
                    <Link to="/" className="navbar-brand">
                        <h3 className="text-white">CarRent</h3>
                    </Link>
                    <div className="d-flex align-items-center gap-3 ms-auto">
                        <Link to="/" className="btn btn-outline-light">
                            Back to Listings
                        </Link>
                    </div>
                </div>
            </nav>

            <div className="container mb-5">
                <div className="d-flex justify-content-between align-items-center mb-4">
                    <div>
                        <h1 className="fw-bold" style={{ color: "#253343" }}>
                            Schedule Test Drive
                        </h1>
                        <p className="text-muted">Book your test drive appointment</p>
                    </div>
                </div>

                <div className="row g-4">
                    <div className="col-lg-8">
                        <div className="card border-0 shadow-sm">
                            <div className="card-body p-4">
                                <h4 className="card-title mb-4" style={{ color: "#253343" }}>
                                    Test Drive Details
                                </h4>

                                {carDetails && (
                                    <div className="mb-4 p-3 rounded" style={{ backgroundColor: "#F8F9FA" }}>
                                        <div className="d-flex align-items-center gap-3">
                                            <div style={{ width: "80px", height: "60px", backgroundColor: "#E9ECEF" }} 
                                                className="d-flex align-items-center justify-content-center rounded">
                                                <i className="bi bi-car-front fs-4" style={{ color: "#6C757D" }}></i>
                                            </div>
                                            <div>
                                                <h5 className="mb-1">{carDetails.carMake} {carDetails.carModel}</h5>
                                                <p className="text-muted mb-0">
                                                    {carDetails.year} • {carDetails.carColor} • {carDetails.mileage} miles
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                )}

                                <form onSubmit={confirmBooking}>
                                    <div className="row mb-3">
                                        <div className="col-md-6 mb-3">
                                            <label className="form-label fw-bold">Date</label>
                                            <input
                                                type="date"
                                                className="form-control"
                                                name="date"
                                                value={formData.date}
                                                onChange={handleInputChange}
                                                min={new Date().toISOString().split("T")[0]}
                                                required
                                            />
                                        </div>

                                        <div className="col-md-6 mb-3">
                                            <label className="form-label fw-bold">Time</label>
                                            <input 
                                                type="time" 
                                                className="form-control" 
                                                name="time"
                                                value={formData.time}
                                                onChange={handleInputChange}
                                                required
                                            />
                                        </div>
                                    </div>

                                    <div className="mb-4">
                                        <label className="form-label fw-bold">Location</label>
                                        <input 
                                            type="text" 
                                            className="form-control" 
                                            placeholder="Enter meeting location"
                                            name="location"
                                            value={formData.location}
                                            onChange={handleInputChange}
                                            required
                                        />
                                        <small className="text-muted">Our representative will meet you at this location</small>
                                    </div>

                                    <div className="d-grid gap-3">
                                        <button 
                                            type="submit" 
                                            className="btn text-white fw-bold"
                                            style={{ backgroundColor: "#00B86B" }}
                                        >
                                            Confirm Test Drive
                                        </button>   
                                        <button 
                                            type="button" 
                                            className="btn btn-outline-secondary"
                                            onClick={() => navigate(-1)}
                                        >
                                            Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div className="col-lg-4">
                        <div className="card border-0 shadow-sm">
                            <div className="card-body">
                                <h4 className="card-title mb-4" style={{ color: "#253343" }}>
                                    Test Drive Information
                                </h4>
                                
                                <div className="mb-4">
                                    <h6 className="fw-bold">What to bring</h6>
                                    <ul className="list-unstyled text-muted">
                                        <li className="mb-2">
                                            <i className="bi bi-check-circle-fill me-2 text-success"></i>
                                            Valid driver's license
                                        </li>
                                        <li className="mb-2">
                                            <i className="bi bi-check-circle-fill me-2 text-success"></i>
                                            Proof of insurance
                                        </li>
                                    </ul>
                                </div>

                                <div className="mb-4">
                                    <h6 className="fw-bold">Duration</h6>
                                    <p className="text-muted">
                                        <i className="bi bi-clock-fill me-2"></i>
                                        Approximately 30 minutes
                                    </p>
                                </div>

                                <div>
                                    <h6 className="fw-bold">Need help?</h6>
                                    <p className="text-muted">
                                        <i className="bi bi-telephone-fill me-2"></i>
                                        Contact us at (123) 456-7890
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ScheduleTestDrive;