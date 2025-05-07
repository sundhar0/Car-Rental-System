import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function SellerHistory() {
  const [drivers, setDrivers] = useState([]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(8);
  const [totalPages, setTotalPages] = useState(0);
  const [selectedDocs, setSelectedDocs] = useState([]);

  const getAllDrivers = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/history/getall/2?page=${page}&size=${size}`
      );
      setDrivers(response.data.list);
      setTotalPages(response.data.totalPages);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getAllDrivers();
  }, [page]);

  const handleApproveReject = async (driverId, availablility) => {
    try {
      await axios.put(
        `http://localhost:8080/api/driver/updateAvailablility/${driverId}/${availablility}`
      );
      setDrivers((prev) => prev.filter((d) => d.driverId !== driverId));
    } catch (err) {
      console.log(err);
    }
  };

  const deleteStudents = async (driverId) => {
    try {
      await axios.delete(`http://localhost:8080/api/driver/delete/${driverId}`);
      setDrivers((prev) => prev.filter((s) => s.driverId !== driverId));
    } catch (err) {
      console.log(err);
    }
  };

  const getDriverDocument = async (driverId) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/driver-documents/driver/${driverId}`
      );
      console.log(response.data);
      const documents = [
        response.data.drivingLicence,
        response.data.aadhaarCard,
        response.data.addressProof,
      ];
      setSelectedDocs(documents); // expects array of image URLs
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="" style={{ minHeight: "100vh" }}>

      <div className="container">   
        <div className="d-flex justify-content-between align-items-center ms-auto m-5">
          <h1>Seller History</h1>
          <div className="input-group w-auto mb-3">
            <input
              type="text"
              className="form-control form-control-sm"
              placeholder="Search"
              aria-label="Search"
              aria-describedby="button"
            />
            <button className="btn fw-bold text-white" type="button" id="button" style={{ backgroundColor: "#00B86B" }}>
              <i className="bi bi-search"></i>
            </button>
          </div>
        </div>

        <div className="">
          <table className="table text-center">
            <thead>
              <tr>
                <th scope="col">Car ID</th>
                <th scope="col">Car Name</th>
                <th scope="col">Status</th>
                <th scope="col">Actions</th>
              </tr>
            </thead>
            <tbody>
              {drivers
                .filter((unapprov) => unapprov.driverAvailability === "UNAVAILABLE")
                .sort((a, b) => a.driverId - b.driverId)
                .map((d, index) => (
                  <tr key={index}>
                    <th scope="row">{d.driverId}</th>
                    <td>{d.user.userId}</td>
                    <td>{d.name}</td>
                    <td>
                    <span
                      className={`badge ${
                        ride.rideStatus === "CONFIRMED"
                          ? "bg-success"
                          : ride.rideStatus === "WAITING_FOR_DRIVER"
                          ? "bg-warning"
                          : "bg-danger"
                      }`}
                    >
                      {ride.rideStatus}
                    </span>
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
    </div>
  );
}

export default SellerHistory;
