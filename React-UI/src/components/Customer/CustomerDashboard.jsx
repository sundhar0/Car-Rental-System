<<<<<<< HEAD
import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router';

const CustomerDashboard = () => {
  const [activeTab, setActiveTab] = useState('rentals');
  const navigate=useNavigate()
  const AddCars=()=>{
    navigate("/becomeaSeller")
  }

  const renderContent = () => {
    switch (activeTab) {
      case 'rentals':
        return <p>Not done anything yet.</p>;
      case 'buying':
        return <p>Not done anything yet.</p>;
      case 'selling':
        return (
          <>
            <button className="btn btn-success mb-3" onClick={()=>{AddCars()}}>Add New Car for Sale</button>
            <p>Not done anything yet.</p>
          </>
        );
      case 'given':
        return (
          <>
            <button className="btn btn-primary mb-3">Add New Car for Rent</button>
            <p>Not done anything yet.</p>
          </>
        );
      default:
        return null;
    }
  };

  return (
    <div className="container mt-5">
      <div className="card shadow p-4">
        <h3>Customer Dashboard</h3>
        <p><strong>Name:</strong> John Doe</p>
        <p><strong>Email:</strong> john@example.com</p>
        <p><strong>Phone:</strong> 9876543210</p>

        <ul className="nav nav-tabs my-3">
          <li className="nav-item">
            <button
              className={`nav-link ${activeTab === 'rentals' ? 'active' : ''}`}
              onClick={() => setActiveTab('rentals')}
            >
              Rentals
            </button>
          </li>
          <li className="nav-item">
            <button
              className={`nav-link ${activeTab === 'buying' ? 'active' : ''}`}
              onClick={() => setActiveTab('buying')}
            >
              Buying
            </button>
          </li>
          <li className="nav-item">
            <button
              className={`nav-link ${activeTab === 'selling' ? 'active' : ''}`}
              onClick={() => setActiveTab('selling')}
            >
              Selling
            </button>
          </li>
          <li className="nav-item">
            <button
              className={`nav-link ${activeTab === 'given' ? 'active' : ''}`}
              onClick={() => setActiveTab('given')}
            >
              Given For Rent
            </button>
          </li>
        </ul>

        <div className="tab-content">
          {renderContent()}
        </div>
      </div>
    </div>
  );
};

export default CustomerDashboard;
=======
import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function CustomerDashboard() {
  const [customer, setCustomer] = useState({});
  const [complaints, setComplaints] = useState([]);
  const [newComplaint, setNewComplaint] = useState({
    issue: "",
    status: "PENDING" // Default status
  });
  const [editingComplaint, setEditingComplaint] = useState(null);
  const [showComplaintModal, setShowComplaintModal] = useState(false);

  const fetchCustomer = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/userLogin/userDetails`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      setCustomer(response.data);
    } catch (error) {
      console.error("Error fetching customer:", error);
    }
  };

  const fetchComplaints = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/complaints/user/2`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      setComplaints(response.data);
    } catch (error) {
      console.error("Error fetching complaints:", error);
    }
  };

  useEffect(() => {
    fetchCustomer();
  }, []);

  useEffect(() => {
    if (customer.userId) {
      fetchComplaints();
    }
  }, [customer.userId]);

  const handleAddComplaint = async () => {
    try {
      await axios.post(
        `http://localhost:8080/api/complaints/1`,
        newComplaint,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      setNewComplaint({ issue: "", status: "PENDING" });
      fetchComplaints();
    } catch (error) {
      console.error("Error adding complaint:", error);
    }
  };

  const handleUpdateComplaint = async () => {
    try {
      await axios.put(
        `http://localhost:8080/api/complaints/${editingComplaint.complaintId}`,
        editingComplaint,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      setShowComplaintModal(false);
      setEditingComplaint(null);
      fetchComplaints();
    } catch (error) {
      console.error("Error updating complaint:", error);
    }
  };

  const handleDeleteComplaint = async (complaintId) => {
    try {
      await axios.delete(
        `http://localhost:8080/api/complaints/${complaintId}`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      fetchComplaints();
    } catch (error) {
      console.error("Error deleting complaint:", error);
    }
  };

  const openEditModal = (complaint) => {
    setEditingComplaint({ ...complaint });
    setShowComplaintModal(true);
  };

  return (
    <div className="d-flex" style={{ minHeight: "100vh" }}>
      <div className="d-flex" id="wrapper">
        <div
          className="text-white p-2 sidebar"
          id="sidebar"
          style={{ backgroundColor: "#253343" }}
        >
          <div className="text-center my-4">
            <span className="fs-4 fw-bold d-none d-md-inline">CarRent</span>
          </div>
          <ul className="nav flex-column gap-3 text-center">
            <li className="nav-item">
              <Link
                to="/landingpage"
                className="text-white text-decoration-none"
              >
                <i className="bi bi-house-door"></i>
                <span className="ms-2 d-none d-md-inline">Home</span>
              </Link>
            </li>
            <li className="nav-item">
              <a href="#" className="nav-link text-white">
                <i className="bi bi-speedometer2"></i>
                <span className="ms-2 d-none d-md-inline">Dashboard</span>
              </a>
            </li>
            <li className="nav-item">
              <Link to="/my-bookings" className="nav-link text-white">
                <i className="bi bi-table"></i>
                <span className="ms-2 d-none d-md-inline">My Bookings</span>
              </Link>
            </li>
          </ul>
        </div>
      </div>

      <div className="flex-grow-1 p-3">
        <div className="container mt-3">
          <h1 className="fw-bold" style={{ color: "#253343" }}>Customer Dashboard</h1>
          <div className="m-5">
            <h3>Welcome, {customer.name}</h3>
          </div>

          <div className="row gap-5 px-5">
            <div className="col-12 border p-5">
              <h4>My Complaints</h4>
              <div className="mb-4">
                <div className="input-group mb-3">
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Enter complaint issue..."
                    value={newComplaint.issue}
                    onChange={(e) => 
                      setNewComplaint({ ...newComplaint, issue: e.target.value })
                    }
                  />
                  <button
                    className="btn btn-primary"
                    onClick={handleAddComplaint}
                    disabled={!newComplaint.issue}
                    style={{ backgroundColor: "#00B86B" }}
                  >
                    Add Complaint
                  </button>
                </div>
              </div>
              <div className="table-responsive">
                <table className="table table-striped">
                  <thead>
                    <tr>
                      <th>Issue</th>
                      <th>Status</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    {complaints.map((complaint) => (
                      <tr key={complaint.complaintId}>
                        <td>{complaint.issue}</td>
                        <td>
                          <span className={`badge ${
                            complaint.status === "RESOLVED" ? "bg-success" :
                            complaint.status === "IN_PROGRESS" ? "bg-warning" : "bg-secondary"
                          }`}>
                            {complaint.status}
                          </span>
                        </td>
                        <td>
                          <button
                            className="btn btn-sm btn-info me-2"
                            onClick={() => openEditModal(complaint)}
                          >
                            Edit
                          </button>
                          <button
                            className="btn btn-sm btn-danger"
                            onClick={() => handleDeleteComplaint(complaint.complaintId)}
                          >
                            Delete
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Complaint Edit Modal */}
      {showComplaintModal && (
        <div className="modal show" style={{ display: "block", backgroundColor: "rgba(0,0,0,0.5)" }}>
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Edit Complaint</h5>
                <button 
                  type="button" 
                  className="btn-close" 
                  onClick={() => setShowComplaintModal(false)}
                ></button>
              </div>
              <div className="modal-body">
                <div className="mb-3">
                  <label className="form-label">Issue</label>
                  <input
                    type="text"
                    className="form-control"
                    value={editingComplaint.issue}
                    onChange={(e) =>
                      setEditingComplaint({
                        ...editingComplaint,
                        issue: e.target.value,
                      })
                    }
                  />
                </div>
                <div className="mb-3">
                  <label className="form-label">Status</label>
                  <select
                    className="form-select"
                    value={editingComplaint.status}
                    onChange={(e) =>
                      setEditingComplaint({
                        ...editingComplaint,
                        status: e.target.value,
                      })
                    }
                  >
                    <option value="PENDING">Pending</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="RESOLVED">Resolved</option>
                  </select>
                </div>
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-secondary"
                  onClick={() => setShowComplaintModal(false)}
                >
                  Cancel
                </button>
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={handleUpdateComplaint}
                >
                  Save Changes
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default CustomerDashboard;
>>>>>>> de9b27e6df39d061f00a7cdffe9e0cc7e12fc2c7
