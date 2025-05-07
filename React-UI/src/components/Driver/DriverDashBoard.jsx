import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function DriverDashBoard() {
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [driver, setDriver] = useState([]);
  const [recentRides, setRecentRides] = useState([]); // Add state for recent rides

  const showDriver = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/driver/name`,
        {
          params: {
            driverUsername: localStorage.getItem("username"),
          },
        }
      );
      setDriver(response.data);

      // Fetch recent rides (last 5 rides)
      const ridesResponse = await axios.get(
        `http://localhost:8080/api/rentWithDriver/driver/${response.data.driverId}?page=0&size=5`
      );
      setRecentRides(ridesResponse.data.list);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    showDriver();
  }, []);

  const setSchedule = async (driverId) => {
    try {
      let obj = {
        availableFrom: startDate,
        availableTo: endDate,
      };

      await axios.post(
        `http://localhost:8080/api/driverSchedule/addOrUpdate/${driverId}`,
        obj,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      alert("Schedule updated successfully!");
    } catch (err) {
      console.log(err);
      alert("Error updating schedule");
    }
  };

  const toggleAvailability = async (isAvailable) => {
    try {
      await axios.put(
        `http://localhost:8080/api/driver/updateAvailablility/${
          driver.driverId
        }/${isAvailable ? "AVAILABLE" : "UNAVAILABLE"}`
      );
      alert(`You are now ${isAvailable ? "available" : "unavailable"}`);
    } catch (err) {
      console.log(err);
    }
  };

  // Function to format date
  const formatDate = (dateString) => {
    const options = { year: "numeric", month: "short", day: "numeric" };
    return new Date(dateString).toLocaleDateString(undefined, options);
  };

  // Function to get status badge style
  const getStatusBadge = (status) => {
    switch (status) {
      case "CONFIRMED":
        return "bg-success";
      case "WAITING_FOR_DRIVER":
        return "bg-warning";
      case "COMPLETED":
        return "bg-primary";
      case "REJECTED":
        return "bg-danger";
      default:
        return "bg-secondary";
    }
  };

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
                  to="/driverrides"
                  className="nav-link text-white text-decoration-none"
                >
                  My Rides
                </Link>
              </li>
              <li className="nav-item">
                <button
                  className="btn text-white fw-bold"
                  style={{ backgroundColor: "#00B86B" }}
                  onClick={() => toggleAvailability(true)}
                >
                  Go Online
                </button>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <div className="container">
        <div className="d-flex justify-content-between align-items-center m-5">
          <div>
            <h1 className="fw-bold" style={{ color: "#253343" }}>
              Dashboard
            </h1>
            <h3 className="text-muted">Welcome back, {driver.name}</h3>
          </div>
          <div className="d-flex gap-3">
            <div
              className="bg-white p-3 rounded shadow-sm text-center"
              style={{ minWidth: "120px" }}
            >
              <small className="text-muted">Driver ID</small>
              <h5 className="mb-0">{driver.driverId}</h5>
            </div>
            <div
              className="bg-white p-3 rounded shadow-sm text-center"
              style={{ minWidth: "120px" }}
            >
              <small className="text-muted">Rating</small>
              <h5 className="mb-0">{driver.rating || "N/A"}</h5>
            </div>
          </div>
        </div>

        <div className="row g-4 mb-5">
          <div className="col-lg-8">
            <div
              className="card border-0 shadow-sm h-100"
              style={{ backgroundColor: "#F8F9FA" }}
            >
              <div className="card-body">
                <h4 className="card-title mb-4">Today's Stats</h4>
                <div className="row">
                  <div className="col-md-4 mb-4">
                    <div
                      className="p-3 rounded"
                      style={{ backgroundColor: "#E8F5E9" }}
                    >
                      <small className="text-muted">Trips Completed</small>
                      <h3 className="mt-2" style={{ color: "#00B86B" }}>
                        {driver.tripCompleted}
                      </h3>
                    </div>
                  </div>
                  <div className="col-md-4 mb-4">
                    <div
                      className="p-3 rounded"
                      style={{ backgroundColor: "#E3F2FD" }}
                    >
                      <small className="text-muted">Earnings</small>
                      <h3 className="mt-2" style={{ color: "#2196F3" }}>
                        ₹{driver.totalEarnings}
                      </h3>
                    </div>
                  </div>
                  <div className="col-md-4 mb-4">
                    <div
                      className="p-3 rounded"
                      style={{ backgroundColor: "#FFF8E1" }}
                    >
                      <small className="text-muted">Rating</small>
                      <h3 className="mt-2" style={{ color: "#FFC107" }}>
                        {driver.rating} <i className="bi bi-star-fill"></i>
                      </h3>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div className="col-lg-4">
            <div className="card border-0 shadow-sm h-100">
              <div className="card-body position-relative">
                {" "}
                {/* Add position-relative here */}
                <h4 className="card-title mb-4">Quick Actions</h4>
                <div className="d-grid gap-3">
                  <button
                    className="btn btn-lg text-white"
                    style={{ backgroundColor: "#00B86B" }}
                    onClick={() => toggleAvailability(true)}
                  >
                    <i className="bi bi-check-circle me-2"></i> Go Online
                  </button>
                  <button
                    className="btn btn-lg btn-outline-danger"
                    onClick={() => toggleAvailability(false)}
                  >
                    <i className="bi bi-x-circle me-2"></i> Go Offline
                  </button>

                  <button
                    className="btn btn-lg btn-outline-primary"
                    data-bs-toggle="collapse"
                    data-bs-target="#setAvailable"
                  >
                    <i className="bi bi-calendar-plus me-2"></i> Set Schedule
                  </button>

                  <div
                    className="collapse position-absolute top-100 start-0 end-0 z-3 mt-1"
                    id="setAvailable"
                  >
                    <div className="card card-body border-0 shadow-sm mx-3">
                      <form
                        onSubmit={(e) => {
                          e.preventDefault();
                          setSchedule(driver.driverId);
                        }}
                      >
                        <div className="mb-3">
                          <label className="form-label">Start Date</label>
                          <input
                            type="date"
                            className="form-control"
                            value={startDate}
                            onChange={(e) => setStartDate(e.target.value)}
                            required
                          />
                        </div>
                        <div className="mb-3">
                          <label className="form-label">End Date</label>
                          <input
                            type="date"
                            className="form-control"
                            value={endDate}
                            onChange={(e) => setEndDate(e.target.value)}
                            required
                          />
                        </div>
                        <button
                          type="submit"
                          className="btn w-100 text-white"
                          style={{ backgroundColor: "#00B86B" }}
                        >
                          Save Schedule
                        </button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Recent Rides Section */}
        <div className="card border-0 shadow-sm mb-5">
          <div className="card-body">
            <div className="d-flex justify-content-between align-items-center mb-4">
              <h4 className="card-title mb-0">Recent Rides</h4>
              <Link
                to="/driverrides"
                className="btn btn-sm text-white"
                style={{ backgroundColor: "#00B86B" }}
              >
                View All <i className="bi bi-arrow-right"></i>
              </Link>
            </div>
            <div className="table-responsive">
              <table className="table table-hover">
                <thead>
                  <tr>
                    <th>Ride ID</th>
                    <th>Customer</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Amount</th>
                  </tr>
                </thead>
                <tbody>
                  {recentRides.length > 0 ? (
                    recentRides.map((ride) => (
                      <tr key={ride.id}>
                        <td>{ride.id}</td>
                        <td>{ride.user?.username || "N/A"}</td>
                        <td>{ride.car?.model || "N/A"}</td>
                        <td>{formatDate(ride.rentalStart)}</td>
                        <td>{formatDate(ride.rentalEnd)}</td>
                        <td>
                          <span
                            className={`badge ${getStatusBadge(
                              ride.rideStatus
                            )}`}
                          >
                            {ride.rideStatus}
                          </span>
                        </td>
                      </tr>
                    ))
                  ) : (
                    <tr>
                      <td colSpan="6" className="text-center py-4">
                        No recent rides found
                      </td>
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

export default DriverDashBoard;
