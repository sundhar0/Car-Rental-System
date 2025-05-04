import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router";

function DriverDashBoard() {
  const [startDate, setStartDate] = useState();
  const [endDate, setEndDate] = useState();
  const [driver, setDriver] = useState([]);

  const showDriver = async () => {
    const response = await axios.get(`http://localhost:8080/api/driver/name`, {
      params: {
        driverUsername: localStorage.getItem("username"),
      },
    });
    setDriver(response.data);
  };

  useEffect(() => {
    showDriver();
  }, []);

  const setSchedule = async (driverId) => {
    let obj = {
      availableFrom: startDate,
      availableTo: endDate,
    };

    const response = await axios.post(
      `http://localhost:8080/api/driverSchedule/addOrUpdate/${driverId}`,
      obj,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      }
    );
    console.log(localStorage.getItem("token"));

    console.log(response);
  };

 

  return (
    <div className="d-flex" style={{ minHeight: "100vh" }}>
      <div className="d-flex" id="wrapper">
        <div
          className="text-white p-2 sidebar"
          id="sidebar"
          style={{ backgroundColor: "#1C2631" }}
        >
          <div className="text-center my-4">
            <span className="fs-4 fw-bold d-none d-md-inline">CarRent</span>
          </div>
          <ul className="nav flex-column gap-3 text-start">
            

            <li className="nav-item">
              <a href="#" className="nav-link text-white">
                <i className="bi bi-speedometer2"></i>
                <span className="ms-2 d-none d-md-inline">Dashboard</span>
              </a>
            </li>
            <li className="nav-item">
              <a href="#" className="nav-link text-white">
                <i className="bi bi-table"></i>
                <span className="ms-2 d-none d-md-inline">Rides</span>
              </a>
            </li>
          </ul>
        </div>
      </div>

      <div className="flex-grow-1 p-3">
        <div className="container mt-3">
          <h1 className="fw-bold" style={{ color: "#253343" }}>DashBoard</h1>
          <div className="m-5">
            <h3>{driver.name}</h3>
          </div>
          <div className="row gap-5 px-5">
            <div className="col-lg col-12 border p-5">
              <h4 className="">Today's stats</h4>
              <div className="row">
                <div className="col-6 p-3">
                  <small className="fw-light">Trips Completed</small>
                  <h4>8</h4>
                </div>
                <div className="col-6 p-3">
                  <small className="fw-light ">Earnings</small>
                  <h4>&#8377;2000</h4>
                </div>

                <div className="col-6 p-3">
                  <small className="fw-light">Ratings</small>
                  <h4>{driver.rating}</h4>
                </div>
              </div>
            </div>
            <div className="col-lg col-12 border p-5">
              <h4>Quick action</h4>
              <div className="d-flex flex-column gap-2">
                <button className="btn btn-success w-100 p-3 mt-2 text-white">
                  Set Unavailable/Busy
                </button>

                <button
                  className="btn custom-outline w-100 p-3 mt-2"
                  data-bs-toggle="collapse"
                  data-bs-target="#setAvailable"
                >
                  Schedule
                </button>
                <form
                  onSubmit={($event) => {
                    $event.preventDefault();
                    setSchedule(driver.driverId);
                  }}
                >
                  <div className="collapse mt-4" id="setAvailable">
                    <ul className="d-flex gap-4 align-items-center list-unstyled list-decoration-none">
                      <li>
                        <label>Start date</label>
                        <input
                          type="text"
                          className="form-control"
                          placeholder="yyyy-mm-dd"
                          onChange={($event) => {
                            setStartDate($event.target.value);
                          }}
                        />
                      </li>
                      <li>
                        <label> End date</label>
                        <input
                          type="text"
                          className="form-control"
                          placeholder="yyyy-mm-dd"
                          onChange={($event) => {
                            setEndDate($event.target.value);
                          }}
                        />
                      </li>
                      <li>
                        <button className="btn btn-success px-4" type="submit">
                          Set
                        </button>
                      </li>
                    </ul>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default DriverDashBoard;
