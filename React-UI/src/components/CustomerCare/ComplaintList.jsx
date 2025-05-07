import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function ComplaintList() {
    const [complaints, setComplaints] = useState([]);
    const [updates, setUpdates] = useState({});

    const getAllComplaints = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/complaints/getAll`);
            setComplaints(response.data);
        } catch (err) {
            console.log(err);
        }
    };

    useEffect(() => {
        getAllComplaints();
    }, []);

    const deleteComplaint = async (complaintId) => {
        try {
            await axios.delete(`http://localhost:8080/api/complaints/${complaintId}`);
            setComplaints(prev => prev.filter(c => c.complaintId !== complaintId));
        } catch (err) {
            console.log(err);
        }
    };

    const updateComplaint = async (e, complaintId) => {
        e.preventDefault();
        try {
            const { responseText, status } = updates[complaintId] || {};
            const response = await axios.put(`http://localhost:8080/api/complaints/${complaintId}`, {
                reponse: responseText,
                status: status
            });

            const updatedComplaints = complaints.map(complaint =>
                complaint.complaintId === complaintId
                    ? { ...complaint, reponse: responseText, status, updatedAt: new Date().toISOString() }
                    : complaint
            );
            setComplaints(updatedComplaints);
        } catch (err) {
            console.log(err);
        }
    };

    const handleUpdateChange = (complaintId, field, value) => {
        setUpdates(prev => ({
            ...prev,
            [complaintId]: {
                ...prev[complaintId],
                [field]: value
            }
        }));
    };

    const formatDate = (dateString) => {
        const options = { year: 'numeric', month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit' };
        return new Date(dateString).toLocaleDateString(undefined, options);
    };

    const getStatusBadge = (status) => {
        switch (status) {
            case "OPEN":
                return "bg-warning";
            case "IN_PROGRESS":
                return "bg-info";
            case "RESOLVED":
                return "bg-success";
            case "CLOSED":
                return "bg-secondary";
            default:
                return "bg-light text-dark";
        }
    };

    return (
        <div className="" style={{ minHeight: "100vh" }}>
            <nav className="navbar navbar-expand-lg mb-4 p-3" style={{ backgroundColor: "#1C2631" }}>
                <div className="container">
                    <Link to="/" className="navbar-brand">
                        <h3 className="text-white">CarRent</h3>
                    </Link>
                    <button 
                        className="navbar-toggler" 
                        style={{
                            boxShadow: "none",
                            outline: "none",
                            border: "none",
                        }}
                        type="button" 
                        data-bs-toggle="collapse" 
                        data-bs-target="#navmenu"
                    >
                        <i className="bi bi-list fs-2" style={{ color: "#00B86B" }}></i>
                    </button>
                    <div className="collapse navbar-collapse" id="navmenu">
                        <ul className="navbar-nav d-flex align-items-center gap-2 ms-auto">
                            <li className="nav-item">
                                <Link to="/admin" className="nav-link text-white text-decoration-none">
                                    Admin Dashboard
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link to="/complaints" className="nav-link text-white text-decoration-none active">
                                    Complaints
                                </Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <div className="container">
                <div className="d-flex justify-content-between align-items-center m-5">
                    <div>
                        <h1 className="fw-bold" style={{ color: "#253343" }}>Complaint Management</h1>
                        <h3 className="text-muted">View and manage customer complaints</h3>
                    </div>
                    <div className="d-flex gap-3">
                        <div
                            className="bg-white p-3 rounded shadow-sm text-center"
                            style={{ minWidth: "120px" }}
                        >
                            <small className="text-muted">Total Complaints</small>
                            <h5 className="mb-0">{complaints.length}</h5>
                        </div>
                    </div>
                </div>

                <div className="card border-0 shadow-sm mb-5">
                    <div className="card-body">
                        <div className="d-flex justify-content-between align-items-center mb-4">
                            <h4 className="card-title mb-0">All Complaints</h4>
                        </div>
                        <div className="table-responsive">
                            <table className="table table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>User</th>
                                        <th>Issue</th>
                                        <th>Description</th>
                                        <th>Status</th>
                                        <th>Response</th>
                                        <th>Last Updated</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {complaints.length > 0 ? complaints.map((c, index) => (
                                        <tr key={index}>
                                            <td>{c.complaintId}</td>
                                            <td>{c.user?.userId || "N/A"}</td>
                                            <td>{c.issue}</td>
                                            <td>{c.description}</td>
                                            <td>
                                                <span className={`badge ${getStatusBadge(c.status)}`}>
                                                    {c.status.replace("_", " ")}
                                                </span>
                                            </td>
                                            <td>{c.reponse || "No response yet"}</td>
                                            <td>{formatDate(c.updatedAt)}</td>
                                            <td>
                                                <div className="d-flex gap-2">
                                                    <button 
                                                        className="btn btn-danger btn-sm" 
                                                        onClick={() => deleteComplaint(c.complaintId)}
                                                        style={{ minWidth: "30px" }}
                                                    >
                                                        <i className="bi bi-trash"></i>
                                                    </button>
                                                    <button 
                                                        className="btn btn-primary btn-sm" 
                                                        data-bs-toggle="modal" 
                                                        data-bs-target={`#update-${c.complaintId}`}
                                                        style={{ minWidth: "30px" }}
                                                    >
                                                        <i className="bi bi-pencil"></i>
                                                    </button>
                                                </div>

                                                {/* Modal */}
                                                <div className="modal fade" id={`update-${c.complaintId}`} tabIndex="-1">
                                                    <div className="modal-dialog">
                                                        <div className="modal-content">
                                                            <div className="modal-header">
                                                                <h5 className="modal-title">Update Complaint #{c.complaintId}</h5>
                                                                <button 
                                                                    type="button" 
                                                                    className="btn-close" 
                                                                    data-bs-dismiss="modal" 
                                                                    aria-label="Close"
                                                                ></button>
                                                            </div>
                                                            <div className="modal-body">
                                                                <form onSubmit={(e) => updateComplaint(e, c.complaintId)}>
                                                                    <div className="mb-3">
                                                                        <label className="form-label">Status:</label>
                                                                        <select
                                                                            className="form-select"
                                                                            onChange={(e) => handleUpdateChange(c.complaintId, 'status', e.target.value)}
                                                                            defaultValue={c.status}
                                                                        >
                                                                            <option value="OPEN">OPEN</option>
                                                                            <option value="IN_PROGRESS">IN PROGRESS</option>
                                                                            <option value="RESOLVED">RESOLVED</option>
                                                                            <option value="CLOSED">CLOSED</option>
                                                                        </select>
                                                                    </div>
                                                                    <div className="mb-3">
                                                                        <label className="form-label">Response:</label>
                                                                        <textarea
                                                                            className="form-control"
                                                                            defaultValue={c.reponse}
                                                                            rows="4"
                                                                            onChange={(e) => handleUpdateChange(c.complaintId, 'responseText', e.target.value)}
                                                                        ></textarea>
                                                                    </div>
                                                                    <div className="d-grid">
                                                                        <button 
                                                                            type="submit" 
                                                                            className="btn text-white" 
                                                                            style={{ backgroundColor: "#00B86B" }} 
                                                                            data-bs-dismiss="modal"
                                                                        >
                                                                            Update Complaint
                                                                        </button>
                                                                    </div>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    )) : (
                                        <tr>
                                            <td colSpan="8" className="text-center py-4">No complaints found</td>
                                        </tr>
                                    )}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ComplaintList;