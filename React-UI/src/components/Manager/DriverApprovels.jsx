import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function DriverApprovels() {
  const [drivers, setDrivers] = useState([]);

  const getAllDrivers = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/driver/available"
      );
      setDrivers(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getAllDrivers();
  }, []);

  const handleApproveReject = (driverId, availablility) => {
    try{
      const resp = axios.put(`http://localhost:8080/api/driver/updateAvailablility/${driverId}/${availablility}`)
    }

    catch(err){
      console.log(err)
    }
  };

  const deleteStudents = (driverId) => {
    try {
      const resp = axios.delete(
        `http://localhost:8080/api/driver/delete/${driverId}`
      );
      let temp = [...drivers];
      temp = temp.filter((s) => s.driverId !== driverId);
      setDrivers(temp);
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
                <a href="#" className="nav-link">
                  <button
                    className="btn text-white fw-bold"
                    style={{ backgroundColor: "#00B86B" }}
                  >
                    Sell/Buy
                  </button>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div className="container">
        <div className="d-flex justify-content-between align-items-center ms-auto mb-5">
          <h1>Approvals</h1>
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
              Button
            </button>
          </div>
        </div>
        <div className="">
          <table className="table text-center">
            <thead>
              <tr>
                <th scope="col">DriverId</th>
                <th scope="col">UserId</th>
                <th scope="col">Experience</th>
                <th scope="col">Licence NO</th>
                <th scope="col">Per Day Charge</th>
                <th scope="col">Approve / Reject</th>
                <th scope="col">Documents</th>
              </tr>
            </thead>
            <tbody>
              {drivers
                .filter(
                  (unapprov) =>
                    unapprov.driverAvailability == "UNAVAILABLE"
                )
                // .sort((a, b) => a.driverId - b.driverId)
                .map((d, index) => (
                  <tr key={index}>
                    <th scope="row">{d.driverId}</th>
                    <td>{d.user.userId}</td>
                    <td>{d.experienceYears}</td>
                    <td>{d.licenseNo}</td>
                    <td>{d.perDayCharge}</td>
                    <td>
                      <div className="d-flex justify-content-center gap-1">
                        <button
                          className="btn text-white border-0"
                          style={{ backgroundColor: "#00B86B" }}
                          onClick={() => {
                            handleApproveReject(d.driverId, "AVAILABLE");
                          }}
                        >
                          Approve
                        </button>
                        <button
                          className="btn btn-danger"
                          onClick={() => {
                            deleteStudents(d.driverId)
                          }}
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
    </div>
  );
}

export default DriverApprovels;
