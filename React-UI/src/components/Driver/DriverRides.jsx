import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function DriverRides() {
  const [rides, setRides] = useState([]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(8);
  const [totalPages, setTotalPages] = useState(0);
  const [driver, setDriver] = useState({});

  const getDriverRides = async () => {
    try {
      // First get driver details
      const driverResponse = await axios.get(
        `http://localhost:8080/api/driver/name`,
        {
          params: {
            driverUsername: localStorage.getItem("username"),
          },
        }
      );
      setDriver(driverResponse.data);

      // Then get rides for this driver
      const ridesResponse = await axios.get(
        `http://localhost:8080/api/rentWithDriver/driver/${driverResponse.data.driverId}?page=${page}&size=${size}`,
        
      );
      setRides(ridesResponse.data.list);
      setTotalPages(ridesResponse.data.totalPages);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getDriverRides();
  }, [page]);

  const handleConfirmRide = async (rideId, status) => {
    try {
      await axios.put(
        `http://localhost:8080/api/rentWithDriver/updateStatus/${rideId}/${status}`,
        {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
          }
      );
      // Refresh the rides list
      getDriverRides();
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
                  to="/driverdashboard"
                  className="nav-link text-white text-decoration-none"
                >
                  Dashboard
                </Link>
              </li>
              <li className="nav-item">
                <Link
                  to="/driverrides"
                  className="nav-link text-white text-decoration-none"
                >
                  My Rides
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div className="container">
        <div className="d-flex justify-content-between align-items-center ms-auto m-5">
          <h1>My Rides</h1>
          <div className="d-flex align-items-center gap-3">
            <div className="input-group w-auto mb-3">
              <input
                type="text"
                className="form-control form-control-sm"
                placeholder="Search"
                aria-label="Search"
                aria-describedby="button"
              />
              <button
                className="btn fw-bold text-white"
                type="button"
                id="button"
                style={{
                  backgroundColor: "#00B86B",
                }}
              >
                <i className="bi bi-search"></i>
              </button>
            </div>
          </div>
        </div>
        <div className="">
          <table className="table text-center">
            <thead>
              <tr>
                <th scope="col">Ride ID</th>
                <th scope="col">Customer</th>
                <th scope="col">Car</th>
                <th scope="col">Start Date</th>
                <th scope="col">End Date</th>
                <th scope="col">Status</th>
                <th scope="col">Actions</th>
              </tr>
            </thead>
            <tbody>
              {rides.map((ride, index) => (
                <tr key={index}>
                  <th scope="row">{ride.id}</th>
                  <td>{ride.user.username || "N/A"}</td>
                  <td>{ride.car.model || "N/A"}</td>
                  <td>{new Date(ride.rentalStart).toLocaleDateString()}</td>
                  <td>{new Date(ride.rentalEnd).toLocaleDateString()}</td>
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
                  <td>
                    {ride.rideStatus === "WAITING_FOR_DRIVER" && (
                      <div className="d-flex justify-content-center gap-1">
                        <button
                          className="btn text-white border-0"
                          style={{ backgroundColor: "#00B86B" }}
                          onClick={() => {
                            handleConfirmRide(ride.id, "CONFIRMED");
                          }}
                        >
                          Confirm
                        </button>
                        <button
                          className="btn btn-danger"
                          onClick={() => {
                            handleConfirmRide(ride.id, "REJECTED");
                          }}
                        >
                          Reject
                        </button>
                      </div>
                    )}
                    {ride.rideStatus !== "WAITING_FOR_DRIVER" && (
                      <span className="text-muted">No actions available</span>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      <div className="d-flex justify-content-center mt-5">
        <div className="">
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
    </div>
  );
}

export default DriverRides;
