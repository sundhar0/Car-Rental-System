import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function BuyerHistory() {
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
          <h1>Buyer History</h1>
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
                <th scope="col">Booked Date</th>
              </tr>
            </thead>
            <tbody>
              {drivers
                .filter((unapprov) => unapprov.driverAvailability === "UNAVAILABLE")
                .sort((a, b) => a.driverId - b.driverId)
                .map((d, index) => (
                  <tr key={index}>
                    <th scope="row">{d.driverId}</th>
                    <td>{d.car.carName}</td>
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
        <div className="modal fade show d-block" tabIndex="-1" role="dialog" style={{ backgroundColor: "rgba(0,0,0,0.5)" }}>
          <div className="modal-dialog modal-lg" role="document">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Driver Documents</h5>
                <button type="button" className="close btn" onClick={() => setSelectedDocs([])}>
                  <span>&times;</span>
                </button>
              </div>
              <div className="modal-body d-flex flex-wrap gap-3 justify-content-center">
                {selectedDocs.map((docUrl, i) => (
                  <img
                    key={i}
                    src={`${docUrl}`}
                    alt={`Document ${i + 1}`}
                    style={{ width: "200px", height: "auto", borderRadius: "10px" }}
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

export default BuyerHistory;
