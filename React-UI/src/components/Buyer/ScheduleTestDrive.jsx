import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import './ScheduleTestDrive.css';

function ScheduleTestDrive() {
    const { carId } = useParams();
    const navigate = useNavigate();
    const [carDetails, setCarDetails] = useState(null);
    const [formData, setFormData] = useState([]);
    const [customerId, setCustomerId] = useState(localStorage.getItem('userId'));

    useEffect(() => {
        // Fetch car details
        const fetchCarDetails = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/car/getById/17`);
                setCarDetails(response.data);
            } catch (error) {
                console.error('Error fetching car details:', error);
            }
        };

        fetchCarDetails();
    }, [carId]);

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
                `http://localhost:8080/api/booking/add/17/1`,
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
        <div className="d-flex" style={{ minHeight: "100vh" }}>
            <div className="d-flex" id="wrapper">
                <div
                    className="text-white p-2 sidebar"
                    id="sidebar"
                    style={{ backgroundColor: "#1C2631" }}
                >
                    <div className="text-center my-4">
                        <span className="fs-4 fw-bold d-none d-md-inline">CarRent</span>
                    </div>
                    <ul className="nav flex-column gap-3 text-start">
                        <li className="nav-item">
                            <a href="#" className="nav-link text-white">
                                <i className="bi bi-speedometer2"></i>
                                <span className="ms-2 d-none d-md-inline">Dashboard</span>
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link text-white">
                                <i className="bi bi-car-front"></i>
                                <span className="ms-2 d-none d-md-inline">Vehicles</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div className="flex-grow-1 p-3">
                <div className="container mt-3">
                    <h1 className="fw-bold" style={{ color: "#253343" }}>Schedule Test Drive</h1>
                    
                    {carDetails && (
                        <div className="card mb-4">
                            <div className="card-body">
                                <h4>{carDetails.carMake} {carDetails.carModel}</h4>
                                <p>Year: {carDetails.year} | Color: {carDetails.carColor}</p>
                            </div>
                        </div>
                    )}

                    <div className="card p-4">
                        <form onSubmit={confirmBooking}>
                            <div className="row mb-3">
                                <div className="col-md-6 mb-3 mb-md-0">
                                    <label className="form-label">Date</label>
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

                                <div className="col-md-6">
                                    <label className="form-label">Time</label>
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
                                <label className="form-label">Location</label>
                                <input 
                                    type="text" 
                                    className="form-control" 
                                    placeholder="Enter meeting location"
                                    name="location"
                                    value={formData.location}
                                    onChange={handleInputChange}
                                    required
                                />
                            </div>

                            <div className="d-grid">
                                <button type="submit" className="btn btn-primary w-50">
                                    Confirm Test Drive
                                </button>   
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ScheduleTestDrive;