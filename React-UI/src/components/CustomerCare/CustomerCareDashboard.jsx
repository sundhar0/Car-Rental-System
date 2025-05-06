import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function CustomerCareDashboard() {
  const [complaints, setComplaints] = useState([]);
  const [selectedComplaint, setSelectedComplaint] = useState(null);
  const [careResponse, setCareResponse] = useState("");
  const [status, setStatus] = useState("PENDING");

  const fetchComplaints = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/complaints/getAll",
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
    fetchComplaints();
  }, []);

  const handleUpdateComplaint = async () => {
    try {
      await axios.put(
        `http://localhost:8080/api/complaints/${selectedComplaint.complaintId}`,
        {
          status: status,
          description: careResponse
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      setSelectedComplaint(null);
      setCareResponse("");
      fetchComplaints();
    } catch (error) {
      console.error("Error updating complaint:", error);
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
                <Link to="/customercaredashboard" className="nav-link text-white text-decoration-none active">Dashboard</Link>
              </li>
              <li className="nav-item">
                <Link to="/customer-issues" className="nav-link text-white text-decoration-none">Customer Issues</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <div className="container">
        <div className="d-flex justify-content-between align-items-center m-5">
          <div>
            <h1 className="fw-bold" style={{ color: "#253343" }}>Customer Care Dashboard</h1>
            <h3 className="text-muted">Welcome back, {localStorage.getItem("username")}</h3>
          </div>
          <div className="d-flex gap-3">
            <div className="bg-white p-3 rounded shadow-sm text-center" style={{ minWidth: "120px" }}>
              <small className="text-muted">Pending Complaints</small>
              <h5 className="mb-0">{complaints.filter(c => c.status === "PENDING").length}</h5>
            </div>
            <div className="bg-white p-3 rounded shadow-sm text-center" style={{ minWidth: "120px" }}>
              <small className="text-muted">In Progress</small>
              <h5 className="mb-0">{complaints.filter(c => c.status === "IN_PROGRESS").length}</h5>
            </div>
          </div>
        </div>

        <div className="row g-4 mb-5">
          <div className="col-lg-8">
            <div className="card border-0 shadow-sm h-100">
              <div className="card-body">
                <div className="d-flex justify-content-between align-items-center mb-4">
                  <h4 className="card-title mb-0">Customer Complaints</h4>
                </div>
                <div className="table-responsive">
                  <table className="table table-hover">
                    <thead>
                      <tr>
                        <th>Customer</th>
                        <th>Issue</th>
                        <th>Status</th>
                        <th>Last Updated</th>
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                      {complaints.length > 0 ? (
                        complaints.map((complaint) => (
                          <tr key={complaint.complaintId}>
                            <td>{complaint.user?.name || "Unknown"}</td>
                            <td>{complaint.issue}</td>
                            <td>
                              <span className={`badge ${getStatusBadge(complaint.status)}`}>
                                {(complaint.status ?? "N/A").replace("_", " ")}
                              </span>
                            </td>
                            <td>{new Date(complaint.updatedAt).toLocaleString()}</td>
                            <td>
                              <button
                                className="btn btn-sm text-white"
                                style={{ backgroundColor: "#00B86B" }}
                                onClick={() => {
                                  setSelectedComplaint(complaint);
                                  setStatus(complaint.status);
                                  setCareResponse(complaint.response || "");
                                }}
                              >
                                <i className="bi bi-chat-left-text"></i> Respond
                              </button>
                            </td>
                          </tr>
                        ))
                      ) : (
                        <tr>
                          <td colSpan="5" className="text-center py-4">No complaints found</td>
                        </tr>
                      )}
                    </tbody>
                  </table>
                </div>
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
                  <button className="btn btn-lg text-white" style={{ backgroundColor: "#00B86B" }}>
                    <i className="bi bi-search me-2"></i> Search Complaints
                  </button>
                  <button className="btn btn-lg btn-outline-primary">
                    <i className="bi bi-filter me-2"></i> Filter by Status
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {selectedComplaint && (
        <div className="modal show" style={{ display: "block", backgroundColor: "rgba(0,0,0,0.5)" }}>
          <div className="modal-dialog modal-lg">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Complaint from {selectedComplaint.user?.name || "Customer"}</h5>
                <button type="button" className="btn-close" onClick={() => setSelectedComplaint(null)}></button>
              </div>
              <div className="modal-body">
                <div className="row">
                  <div className="col-md-6 mb-3">
                    <label className="form-label">Status</label>
                    <select
                      className="form-select"
                      value={status}
                      onChange={(e) => setStatus(e.target.value)}
                    >
                      <option value="PENDING">Pending</option>
                      <option value="IN_PROGRESS">In Progress</option>
                      <option value="RESOLVED">Resolved</option>
                    </select>
                  </div>
                  <div className="col-md-6 mb-3">
                    <label className="form-label">Issue</label>
                    <input type="text" className="form-control" value={selectedComplaint.response} readOnly />
                  </div>
                </div>
                
                <div className="mb-3">
                  <label className="form-label">Customer's Description</label>
                  <textarea className="form-control" rows="3" value={selectedComplaint.description} readOnly />
                </div>
                
                <div className="mb-3">
                  <label className="form-label">Your Response</label>
                  <textarea
                    className="form-control"
                    rows="3"
                    placeholder="Type your response to the customer..."
                    value={careResponse}
                    onChange={(e) => setCareResponse(e.target.value)}
                  />
                </div>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" onClick={() => setSelectedComplaint(null)}>Close</button>
                <button
                  type="button"
                  className="btn text-white"
                  style={{ backgroundColor: "#00B86B" }}
                  onClick={handleUpdateComplaint}
                  disabled={!careResponse}
                >
                  Update Complaint
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default CustomerCareDashboard;