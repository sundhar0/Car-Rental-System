import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function AddComplaint({ userId }) {
    const [issue, setIssue] = useState("");
    const [description, setDescription] = useState("");
    const [status, setStatus] = useState("OPEN");
    const [complaints, setComplaints] = useState([]);
    const [responseMap, setResponseMap] = useState({});
    const [selectedComplaint, setSelectedComplaint] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post(`http://localhost:8080/api/complaints/1`, {
                issue,
                description,
                status,
                user: { userId }
            });
            alert("Complaint submitted successfully!");
            setIssue("");
            setDescription("");
            fetchComplaints();
        } catch (err) {
            console.log(err);
            alert("Failed to submit complaint.");
        }
    };

    const fetchComplaints = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/complaints/getAll`);
            setComplaints(response.data);
        } catch (err) {
            console.log(err);
        }
    };

    const handleResponseChange = (id, value) => {
        setResponseMap((prev) => ({ ...prev, [id]: value }));
    };

    const handleResponseSubmit = async (id) => {
        const responseText = responseMap[id];
        if (!responseText) return;
        try {
            await axios.put(`http://localhost:8080/api/complaints/respond/${id}`, {
                reponse: responseText
            });
            alert("Response updated successfully!");
            fetchComplaints();
            setResponseMap((prev) => ({ ...prev, [id]: "" }));
        } catch (err) {
            console.log(err);
            alert("Failed to update response.");
        }
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

    useEffect(() => {
        fetchComplaints();
    }, []);

    return (
        <div className="" style={{ minHeight: "100vh" }}>
            <nav
                className="navbar navbar-expand-lg mb-4 p-3"
                style={{ backgroundColor: "#1C2631" }}
            >
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
                                <Link
                                    to="/userdashboard"
                                    className="nav-link text-white text-decoration-none"
                                >
                                    Dashboard
                                </Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-lg-8">
                        <div className="card border-0 shadow-sm mb-5">
                            <div className="card-body p-4">
                                <h2 className="mb-4 fw-bold" style={{ color: "#253343" }}>Submit a Complaint</h2>
                                <form onSubmit={handleSubmit}>
                                    <div className="mb-3">
                                        <label className="form-label fw-semibold">Issue</label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            value={issue}
                                            onChange={(e) => setIssue(e.target.value)}
                                            required
                                            style={{ borderRadius: "8px", padding: "12px" }}
                                        />
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label fw-semibold">Description</label>
                                        <textarea
                                            className="form-control"
                                            value={description}
                                            onChange={(e) => setDescription(e.target.value)}
                                            required
                                            rows="4"
                                            style={{ borderRadius: "8px", padding: "12px" }}
                                        />
                                    </div>
                                    <div className="mb-4">
                                        <label className="form-label fw-semibold">Status</label>
                                        <select
                                            className="form-select"
                                            value={status}
                                            onChange={(e) => setStatus(e.target.value)}
                                            style={{ borderRadius: "8px", padding: "12px" }}
                                        >
                                            <option value="OPEN">OPEN</option>
                                            <option value="IN_PROGRESS">IN PROGRESS</option>
                                            <option value="RESOLVED">RESOLVED</option>
                                            <option value="CLOSED">CLOSED</option>
                                        </select>
                                    </div>
                                    <button 
                                        type="submit" 
                                        className="btn w-100 text-white fw-bold"
                                        style={{ 
                                            backgroundColor: "#00B86B",
                                            borderRadius: "8px",
                                            padding: "12px",
                                            fontSize: "16px"
                                        }}
                                    >
                                        Submit Complaint
                                    </button>
                                </form>
                            </div>
                        </div>

                        <div className="card border-0 shadow-sm">
                            <div className="card-body p-4">
                                <div className="d-flex justify-content-between align-items-center mb-4">
                                    <h3 className="fw-bold mb-0" style={{ color: "#253343" }}>Your Complaint History</h3>
                                    <div
                                        className="bg-white p-2 rounded shadow-sm text-center"
                                        style={{ minWidth: "80px" }}
                                    >
                                        <small className="text-muted">Total</small>
                                        <h5 className="mb-0">{complaints.length}</h5>
                                    </div>
                                </div>

                                {complaints.length > 0 ? (
                                    <div className="table-responsive">
                                        <table className="table table-hover align-middle">
                                            <thead>
                                                <tr style={{ backgroundColor: "#F8F9FA" }}>
                                                    <th style={{ width: "10%" }}>ID</th>
                                                    <th style={{ width: "15%" }}>Issue</th>
                                                    <th style={{ width: "20%" }}>Description</th>
                                                    <th style={{ width: "15%" }}>Status</th>
                                                    <th style={{ width: "20%" }}>Response</th>
                                                    <th style={{ width: "20%" }}>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                {complaints.map((c) => (
                                                    <tr key={c.complaintId}>
                                                        <td>{c.complaintId}</td>
                                                        <td>{c.issue}</td>
                                                        <td>{c.description}</td>
                                                        <td>
                                                            <span className={`badge ${getStatusBadge(c.status)}`}>
                                                                {c.status.replace("_", " ")}
                                                            </span>
                                                        </td>
                                                        <td>{c.reponse || <span className="text-muted">No response yet</span>}</td>
                                                        <td>
                                                            <div className="d-flex flex-column gap-2">
                                                                <input
                                                                    type="text"
                                                                    className="form-control form-control-sm"
                                                                    placeholder="Type response..."
                                                                    value={responseMap[c.complaintId] || ""}
                                                                    onChange={(e) => handleResponseChange(c.complaintId, e.target.value)}
                                                                    style={{ borderRadius: "6px" }}
                                                                />
                                                                <button
                                                                    className="btn btn-sm text-white fw-bold"
                                                                    style={{ 
                                                                        backgroundColor: "#00B86B",
                                                                        borderRadius: "6px"
                                                                    }}
                                                                    onClick={() => handleResponseSubmit(c.complaintId)}
                                                                >
                                                                    Submit Response
                                                                </button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                ))}
                                            </tbody>
                                        </table>
                                    </div>
                                ) : (
                                    <div className="text-center py-5">
                                        <i className="bi bi-inbox" style={{ fontSize: "48px", color: "#6c757d" }}></i>
                                        <p className="mt-3 text-muted">No complaints found</p>
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AddComplaint;