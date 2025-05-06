import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function CustomerDashboard() {
  const [customer, setCustomer] = useState({});
  const [complaints, setComplaints] = useState([]);
  const [newComplaint, setNewComplaint] = useState({
    issue: "",
    description: "",
    status: "PENDING"
  });
  const [showComplaintModal, setShowComplaintModal] = useState(false);
  const [selectedComplaint, setSelectedComplaint] = useState(null);
  const [customerResponse, setCustomerResponse] = useState("");

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
        `http://localhost:8080/api/complaints/getByUser/${customer.userId}`,
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
        `http://localhost:8080/api/complaints/${customer.userId}`,
        newComplaint,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      fetchComplaints();
    } catch (error) {
      console.error("Error adding complaint:", error);
    }
  };

  const handleAddResponse = async () => {
    try {
      await axios.put(
        `http://localhost:8080/api/complaints/${selectedComplaint.complaintId}`,
        {
          response: customerResponse,
          isCustomerResponse: true
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
 
      fetchComplaints();
    } catch (error) {
      console.error("Error adding response:", error);
    }
  };

  const getStatusBadge = (status) => {
    switch (status) {
      case "RESOLVED": return "bg-success";
      case "IN_PROGRESS": return "bg-warning";
      case "PENDING": return "bg-secondary";
      default: return "bg-info";
    }
  };

  return (
    <div className="" style={{ minHeight: "100vh" }}>
      <nav className="navbar navbar-expand-lg mb-4 p-3" style={{ backgroundColor: "#1C2631" }}>
        <div className="container">
          <Link to="/" className="navbar-brand">
            <h3 className="text-white">CarRent</h3>
          </Link>
          <button className="navbar-toggler" style={{ boxShadow: "none", outline: "none", border: "none" }} type="button" data-bs-toggle="collapse" data-bs-target="#navmenu">
            <i className="bi bi-list fs-2" style={{ color: "#00B86B" }}></i>
          </button>
          <div className="collapse navbar-collapse" id="navmenu">
            <ul className="navbar-nav d-flex align-items-center gap-2 ms-auto">
              <li className="nav-item">
                <Link to="/customerdashboard" className="nav-link text-white text-decoration-none active">Dashboard</Link>
              </li>
              <li className="nav-item">
                <Link to="/my-bookings" className="nav-link text-white text-decoration-none">My Bookings</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <div className="container">
        <div className="d-flex justify-content-between align-items-center m-5">
          <div>
            <h1 className="fw-bold" style={{ color: "#253343" }}>Customer Dashboard</h1>
            <h3 className="text-muted">Welcome back, {customer.name}</h3>
          </div>
          <div className="d-flex gap-3">
            <div className="bg-white p-3 rounded shadow-sm text-center" style={{ minWidth: "120px" }}>
              <small className="text-muted">Active Complaints</small>
              <h5 className="mb-0">{complaints.filter(c => c.status !== "RESOLVED").length}</h5>
            </div>
          </div>
        </div>

        <div className="row g-4 mb-5">
          <div className="col-lg-8">
            <div className="card border-0 shadow-sm h-100">
              <div className="card-body">
                <div className="d-flex justify-content-between align-items-center mb-4">
                  <h4 className="card-title mb-0">New Complaint</h4>
                </div>
                <div className="mb-3">
                  <label className="form-label">Issue Title</label>
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Brief title of your issue"
                    value={newComplaint.issue}
                    onChange={(e) => setNewComplaint({...newComplaint, issue: e.target.value})}
                  />
                </div>
                <div className="mb-3">
                  <label className="form-label">Description</label>
                  <textarea
                    className="form-control"
                    rows="3"
                    placeholder="Detailed description of your issue"
                    value={newComplaint.description}
                    onChange={(e) => setNewComplaint({...newComplaint, description: e.target.value})}
                  ></textarea>
                </div>
                <button
                  className="btn text-white w-100"
                  style={{ backgroundColor: "#00B86B" }}
                  onClick={handleAddComplaint}
                  disabled={!newComplaint.issue || !newComplaint.description}
                >
                  Submit Complaint
                </button>
              </div>
            </div>
          </div>

          <div className="col-lg-4">
            <div className="card border-0 shadow-sm h-100">
              <div className="card-body">
                <div className="d-flex justify-content-between align-items-center mb-4">
                  <h4 className="card-title mb-0">Quick Actions</h4>
                </div>
                <div className="d-grid gap-3">
                  <Link to="/my-bookings" className="btn btn-lg text-white" style={{ backgroundColor: "#00B86B" }}>
                    <i className="bi bi-calendar-check me-2"></i> View Bookings
                  </Link>
                  <button className="btn btn-lg btn-outline-primary">
                    <i className="bi bi-question-circle me-2"></i> Help Center
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="card border-0 shadow-sm mb-5">
          <div className="card-body">
            <div className="d-flex justify-content-between align-items-center mb-4">
              <h4 className="card-title mb-0">My Complaints</h4>
            </div>
            <div className="table-responsive">
              <table className="table table-hover">
                <thead>
                  <tr>
                    <th>Issue</th>
                    <th>Status</th>
                    <th>Last Updated</th>
                    <th>Response</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {complaints.length > 0 ? (
                    complaints.map((complaint) => (
                      <tr key={complaint.complaintId}>
                        <td>{complaint.issue}</td>
                        <td>
                          <span className={`badge ${getStatusBadge(complaint.status)}`}>
                            {(complaint.status ?? "N/A").replace("_", " ")}
                          </span>
                        </td>
                        <td>{new Date(complaint.updatedAt).toLocaleString()}</td>
                        <td>{complaint.response}</td>
                        <td>
                          <button
                            className="btn btn-sm text-white"
                            style={{ backgroundColor: "#00B86B" }}
                            onClick={() => {
                              setSelectedComplaint(complaint);
                              setCustomerResponse("");
                            }}
                          >
                            <i className="bi bi-chat-left-text"></i> View/Reply
                          </button>
                        </td>
                      </tr>
                    ))
                  ) : (
                    <tr>
                      <td colSpan="4" className="text-center py-4">No complaints found</td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>

      {selectedComplaint && (
        <div className="modal show" style={{ display: "block", backgroundColor: "rgba(0,0,0,0.5)" }}>
          <div className="modal-dialog modal-lg">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Complaint: {selectedComplaint.issue}</h5>
                <button type="button" className="btn-close" onClick={() => setSelectedComplaint(null)}></button>
              </div>
              <div className="modal-body">
                <div className="mb-3">
                  <label className="form-label">Status</label>
                  <input type="text" className="form-control" value={selectedComplaint.status} readOnly />
                </div>
                
                <div className="mb-3">
                  <label className="form-label">Your Initial Complaint</label>
                  <textarea className="form-control" rows="3" value={selectedComplaint.description} readOnly />
                </div>
                
                {selectedComplaint.response && (
                  <div className="mb-3">
                    <label className="form-label">Customer Care Response</label>
                    <textarea className="form-control" rows="3" value={selectedComplaint.response} readOnly />
                  </div>
                )}
                
                <div className="mb-3">
                  <label className="form-label">Your Response</label>
                  <textarea
                    className="form-control"
                    rows="3"
                    placeholder="Type your response here..."
                    value={customerResponse}
                    onChange={(e) => setCustomerResponse(e.target.value)}
                  />
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" onClick={() => setSelectedComplaint(null)}>Close</button>
                <button
                  type="button"
                  className="btn text-white"
                  style={{ backgroundColor: "#00B86B" }}
                  onClick={handleAddResponse}
                  disabled={!customerResponse}
                >
                  Send Response
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