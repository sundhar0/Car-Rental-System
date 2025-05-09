import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { setDriverAll } from "../Store/DriverStore/driverAllSlice";


function DriverApprovels() {
  const [selectedDocs, setSelectedDocs] = useState([]);

  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  const driverAll = useSelector((state) => state.driverAll.driverAll);

  const handleApprove = async (driverId, availablility) => {
    try {
      await axios.put(
        `http://localhost:8080/api/driver/updateAvailablility/${driverId}/${availablility}`
      );
      dispatch(setDriverAll((prev) => prev.filter((d) => d.driverId !== driverId)));
      
    } catch (err) {
      console.log(err);
    }
  };

  const deleteDriver = async (driverId) => {
    try {
      await axios.delete(`http://localhost:8080/api/driver/delete/${driverId}`);
      dispatch(setDriverAll((prev) => prev.filter((d) => d.driverId !== driverId)));
    } catch (err) {
      console.log(err);
    }
  };


  return (
    <div className="" style={{ minHeight: "100vh" }}>
      <nav
        className="navbar navbar-expand-lg mb-4 p-3"
        style={{ backgroundColor: "#1C2631" }}
      >
        <div className="container">
          <a href="#" className="navbar-brand">
            <h3 className="text-white">CarRent</h3>
          </a>
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
                  to="/driverlistformanager"
                  className="nav-link text-white text-decoration-none"
                >
                  Driver List
                </Link>
              </li>
              <li className="nav-item">
                <Link
                  to="/complaintlist"
                  className="nav-link text-white text-decoration-none"
                >
                  Complaints
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <div className="container">
        <div className="d-flex justify-content-between align-items-center ms-auto m-5">
          <h1>Approvals</h1>
        </div>

        <div className="">
          <table className="table text-center">
            <thead>
              <tr>
                <th scope="col">DriverId</th>
                <th scope="col">UserId</th>
                <th scope="col">Name</th>
                <th scope="col">Experience</th>
                <th scope="col">Licence NO</th>
                <th scope="col">Per Day Charge</th>
                <th scope="col">Approve / Reject</th>
              </tr>
            </thead>
            <tbody>
              {driverAll
                .filter(
                  (unapprov) => unapprov.driverAvailability === "NOT_APPROVED"
                )
                .sort((a, b) => a.driverId - b.driverId)
                .map((d, index) => (
                  <tr key={index}>
                    <th scope="row">{d.driverId}</th>
                    <td>{d.user.userId}</td>
                    <td>{d.name}</td>
                    <td>{d.experienceYears}</td>
                    <td>{d.licenseNo}</td>
                    <td>{d.perDayCharge}</td>
                    <td>
                      <div className="d-flex justify-content-center gap-1">
                        <button
                          className="btn text-white border-0"
                          style={{ backgroundColor: "#00B86B" }}
                          onClick={() =>
                            handleApprove(d.driverId, "AVAILABLE")
                          }
                        >
                          Approve
                        </button>
                        <button
                          className="btn btn-danger"
                          onClick={() => deleteDriver(d.driverId)}
                        >
                          Reject
                        </button>
                      </div>
                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>

      <div className="d-flex justify-content-center mt-5">
        <nav aria-label="Page navigation example">
          <ul className="pagination">
            <li className="page-item">
              <a
                className="page-link"
                href="#"
                onClick={() => {
                  page === 0 ? setPage(0) : setPage(page - 1);
                }}
                style={{ color: "#00B86B" }}
              >
                Previous
              </a>
            </li>
            <li className="page-item">
              <a
                className="page-link"
                href="#"
                onClick={() => {
                  page === totalPages - 1 ? setPage(page) : setPage(page + 1);
                }}
                style={{ color: "#00B86B" }}
              >
                Next
              </a>
            </li>
          </ul>
        </nav>
      </div>

      {/* Modal to show documents as an album */}
      {selectedDocs.length > 0 && (
        <div
          className="modal fade show d-block"
          tabIndex="-1"
          role="dialog"
          style={{ backgroundColor: "rgba(0,0,0,0.5)" }}
        >
          <div className="modal-dialog modal-lg" role="document">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Driver Documents</h5>
                <button
                  type="button"
                  className="close btn"
                  onClick={() => setSelectedDocs([])}
                >
                  <span>&times;</span>
                </button>
              </div>
              <div className="modal-body d-flex flex-wrap gap-3 justify-content-center">
                {selectedDocs.map((docUrl, i) => (
                  <img
                    key={i}
                    src={`${docUrl}`}
                    alt={`Document ${i + 1}`}
                    style={{
                      width: "200px",
                      height: "auto",
                      borderRadius: "10px",
                    }}
                  />
                ))}
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default DriverApprovels;
