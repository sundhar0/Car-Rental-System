import React from 'react';
import './ScheduleTestDrive.css';
import { useNavigate } from 'react-router';

function ScheduleTestDrive() {
    const navigate=useNavigate();
    const confirmBooking=()=>{
        navigate("/booking")
    }
    return (
        <div className="registration-wrapper d-flex justify-content-center align-items-center vh-100">
            <div className="card p-5 registration-card w-50">
                <h4 className="text-white mb-4">Schedule Test Drive</h4>

                <div className="row mb-3">
                    <div className="col-md-6 mb-3 mb-md-0">
                        <label className="form-label text-white">Date</label>
                        <input
                            type="date"
                            className="form-control"
                            min={new Date().toISOString().split("T")[0]} // disables past dates
                        />
                    </div>

                    <div className="col-md-6">
                        <label className="form-label text-white">Time</label>
                        <input type="time" className="form-control" />
                    </div>
                </div>

                <div className="mb-4">
                    <label className="form-label text-white">Location</label>
                    <input type="text" className="form-control" placeholder="Enter meeting location" />
                </div>

                <div className="d-grid">
                    <button className="btn btn-primary" onClick={()=>confirmBooking()}>Confirm Test Drive</button>
                </div>
            </div>
        </div>
    );
}

export default ScheduleTestDrive;
