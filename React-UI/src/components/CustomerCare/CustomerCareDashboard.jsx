import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function CustomerCareDashboard() {
  const [allComplaints, setAllComplaints] = useState([]);
  const [allCustomerCareRequests, setAllCustomerCareRequests] = useState([]);
  const [selectedRequest, setSelectedRequest] = useState(null);
  const [selectedComplaint, setSelectedComplaint] = useState(null);
  const [updateData, setUpdateData] = useState({
    status: "",
    response: ""
  });

  const fetchAllComplaints = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/complaints",
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      setAllComplaints(response.data);
    } catch (error) {
      console.error("Error fetching complaints:", error);
    }
  };

  const fetchAllCustomerCareRequests = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/customer-care",
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      setAllCustomerCareRequests(response.data);
    } catch (error) {
      console.error("Error fetching customer care requests:", error);
    }
  };

  useEffect(() => {
    fetchAllComplaints();
    fetchAllCustomerCareRequests();
  }, []);

  const handleUpdateRequest = async () => {
    try {
      await axios.put(
        `http://localhost:8080/api/customer-care/${selectedRequest.supportId}`,
        {
          status: updateData.status,
          response: updateData.response
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      setSelectedRequest(null);
      setUpdateData({ status: "", response: "" });
      fetchAllCustomerCareRequests();
    } catch (error) {
      console.error("Error updating request:", error);
    }
  };

  const handleUpdateComplaint = async () => {
    try {
      await axios.put(
        `http://localhost:8080/api/complaints/${selectedComplaint.complaintId}`,
        {
          status: updateData.status,
          response: updateData.response
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      setSelectedComplaint(null);
      setUpdateData({ status: "", response: "" });
      fetchAllComplaints();
    } catch (error) {
      console.error("Error updating complaint:", error);
    }
  };

  const handleDeleteRequest = async (supportId) => {
    try {
      await axios.delete(
        `http://localhost:8080/api/customer-care/${supportId}`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      fetchAllCustomerCareRequests();
    } catch (error) {
      console.error("Error deleting request:", error);
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
      fetchAllComplaints();
    } catch (error) {
      console.error("Error deleting complaint:", error);
    }
  };

  return (
    <div className="d-flex" style={{ minHeight: "100vh" }}>
      <div className="d-flex" id="wrapper">
        <div
          className="text-dark p-2 sidebar fs-5"
          id="sidebar"
          style={{ backgroundColor: "#253345" }}
        >
          <div className="text-center my-4">
            <span className="fs-4 fw-bold d-none d-md-inline">CarRent</span>
          </div>
          <ul className="nav flex-column gap-3 text-start">
            <li className="nav-item mx-3">
              <Link
                to="/landingpage"
                className="text-dark text-decoration-none"
              >
                <i className="bi bi-house-door"></i>
                <span className="ms-2 d-none d-md-inline">Home</span>
              </Link>
            </li>
            <li className="nav-item">
              <a href="#" className="nav-link text-dark">
                <i className="bi bi-speedometer2"></i>
                <span className="ms-2 d-none d-md-inline">Dashboard</span>
              </a>
            </li>
            <li className="nav-item">
              <Link to="/customer-issues" className="nav-link text-dark">
                <i className="bi bi-headset"></i>
                <span className="ms-2 d-none d-md-inline">Customer Issues</span>
              </Link>
            </li>
          </ul>
        </div>
      </div>

      <div className="flex-grow-1 p-3">
        <div className="container mt-3">
          <h1 className="fw-bold" style={{ color: "#253343" }}>Customer Care Executive Dashboard</h1>
          
          <div className="row mt-5">
            <div className="col-md-6">
              <div className="card">
                <div className="card-header text-white" style={{ backgroundColor: "#00B86B" }}>
                  <h4>Customer Care Requests</h4>
                </div>
                <div className="card-body" style={{ maxHeight: "500px", overflowY: "auto" }}>
                  <table className="table table-striped">
                    <thead>
                      <tr>
                        <th>Issue</th>
                        <th>Status</th>
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                      {allCustomerCareRequests.map((request) => (
                        <tr key={request.supportId}>
                          <td>{request.issue}</td>
                          <td>
                            <span className={`badge ${
                              request.status === "RESOLVED" ? "bg-success" : 
                              request.status === "IN_PROGRESS" ? "bg-warning" : "bg-secondary"
                            }`}>
                              {request.status}
                            </span>
                          </td>
                          <td>
                            <button 
                              className="btn btn-sm btn-info me-2"
                              onClick={() => setSelectedRequest(request)}
                            >
                              View/Update
                            </button>
                            <button 
                              className="btn btn-sm btn-danger"
                              onClick={() => handleDeleteRequest(request.supportId)}
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

            <div className="col-md-6">
              <div className="card">
                <div className="card-header text-white" style={{ backgroundColor: "#00B86B" }}>
                  <h4>Customer Complaints</h4>
                </div>
                <div className="card-body" style={{ maxHeight: "500px", overflowY: "auto" }}>
                  <table className="table table-striped">
                    <thead>
                      <tr>
                        <th>Issue</th>
                        <th>Status</th>
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                      {allComplaints.map((complaint) => (
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
                              onClick={() => setSelectedComplaint(complaint)}

                            >
                              View/Update
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

          {selectedRequest && (
            <div className="modal show" style={{ display: "block", backgroundColor: "rgba(0,0,0,0.5)" }}>
              <div className="modal-dialog">
                <div className="modal-content">
                  <div className="modal-header">
                    <h5 className="modal-title">Update Customer Care Request</h5>
                    <button type="button" className="btn-close" onClick={() => setSelectedRequest(null)}></button>
                  </div>
                  <div className="modal-body">
                    <div className="mb-3">
                      <label className="form-label">Issue</label>
                      <input 
                        type="text" 
                        className="form-control" 
                        value={selectedRequest.issue} 
                        readOnly 
                      />
                    </div>
                    <div className="mb-3">
                      <label className="form-label">Description</label>
                      <textarea 
                        className="form-control" 
                        value={selectedRequest.description} 
                        readOnly 
                        rows="3"
                      />
                    </div>
                    <div className="mb-3">
                      <label className="form-label">Status</label>
                      <select 
                        className="form-select"
                        value={updateData.status || selectedRequest.status}
                        onChange={(e) => setUpdateData({...updateData, status: e.target.value})}
                      >
                        <option value="PENDING">Pending</option>
                        <option value="IN_PROGRESS">In Progress</option>
                        <option value="RESOLVED">Resolved</option>
                      </select>
                    </div>
                    <div className="mb-3">
                      <label className="form-label">Response</label>
                      <textarea 
                        className="form-control" 
                        value={updateData.response || selectedRequest.response || ""}
                        onChange={(e) => setUpdateData({...updateData, response: e.target.value})}
                        rows="3"
                        placeholder="Enter your response..."
                      />
                    </div>
                  </div>
                  <div className="modal-footer">
                    <button 
                      type="button" 
                      className="btn btn-secondary" 
                      onClick={() => setSelectedRequest(null)}
                    >
                      Close
                    </button>
                    <button 
                      type="button" 
                      className="btn btn-primary" 
                      onClick={handleUpdateRequest}
                    >
                      Save Changes
                    </button>
                  </div>
                </div>
              </div>
            </div>
          )}

          {selectedComplaint && (
            <div className="modal show" style={{ display: "block", backgroundColor: "rgba(0,0,0,0.5)" }}>
              <div className="modal-dialog">
                <div className="modal-content">
                  <div className="modal-header">
                    <h5 className="modal-title">Update Complaint</h5>
                    <button type="button" className="btn-close" onClick={() => setSelectedComplaint(null)}></button>
                  </div>
                  <div className="modal-body">
                    <div className="mb-3">
                      <label className="form-label">Issue</label>
                      <input 
                        type="text" 
                        className="form-control" 
                        value={selectedComplaint.issue} 
                        readOnly 
                      />
                    </div>
                    <div className="mb-3">
                      <label className="form-label">Status</label>
                      <select 
                        className="form-select"
                        value={updateData.status || selectedComplaint.status}
                        onChange={(e) => setUpdateData({...updateData, status: e.target.value})}
                      >
                        <option value="PENDING">Pending</option>
                        <option value="IN_PROGRESS">In Progress</option>
                        <option value="RESOLVED">Resolved</option>
                      </select>
                    </div>
                    <div className="mb-3">
                      <label className="form-label">Response</label>
                      <textarea 
                        className="form-control" 
                        value={updateData.response || selectedComplaint.response || ""}
                        onChange={(e) => setUpdateData({...updateData, response: e.target.value})}
                        rows="3"
                        placeholder="Enter your response..."
                      />
                    </div>
                  </div>
                  <div className="modal-footer">
                    <button 
                      type="button" 
                      className="btn btn-secondary" 
                      onClick={() => setSelectedComplaint(null)}
                    >
                      Close
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
      </div>
    </div>
  );
}

export default CustomerCareDashboard;