import flatpickr from "flatpickr";
import { useState } from "react";
import DatePicker from "react-datepicker";

function DriverDashBoard() {


  const [startDate,setStartDate] = useState(null)
  const [endDate,setEndDate] = useState(null)

  const addSchedule = async($event) => {
    $event.preventDefault();
    

    let obj = {
      'driver':driveId
    }
  }

  return (
    <div className="d-flex" style={{ minHeight: "100vh" }}>
      <div className="d-flex" id="wrapper">
        <div
          className="text-white p-2 sidebar"
          id="sidebar"
          style={{ backgroundColor: "#253343" }}
        >
          <div className="text-center my-4">
            <span className="fs-4 fw-bold d-none d-md-inline">CarRent</span>
          </div>
          <ul className="nav flex-column text-start gap-3">
            <li className="nav-item">
              <a href="#" className="nav-link text-white">
                <i className="bi bi-house-door"></i>
                <span className="ms-2 d-none d-md-inline">Home</span>
              </a>
            </li>
            <li className="nav-item">
              <a href="#" className="nav-link text-white">
                <i className="bi bi-speedometer2"></i>
                <span className="ms-2 d-none d-md-inline">Dashboard</span>
              </a>
            </li>
            <li className="nav-item">
              <a href="#" className="nav-link text-white">
                <i className="bi bi-table"></i>
                <span className="ms-2 d-none d-md-inline"></span>
              </a>
            </li>
          </ul>
        </div>
      </div>

      <div className="flex-grow-1 p-3">
        <div className="container mt-3">
          <h1 className="fw-bold">DashBoard</h1>
          <div className="m-5">
            <h3>Sundhar</h3>
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
                  <h4>4.6</h4>
                </div>
              </div>
            </div>
            <div className="col-lg col-12 border p-5">
              <h4>Quick action</h4>
              <div className="d-flex flex-column gap-2">
                <button className="btn btn-success w-100 p-3 mt-2 text-white">
                  Set Available/Busy
                </button>

                <button
                  className="btn custom-outline w-100 p-3 mt-2"
                  data-bs-toggle="collapse"
                  data-bs-target="#setAvailable"
                >
                  Schedule
                </button>
                <div className="collapse mt-4" id="setAvailable">
                  <form onSubmit={{}}>
                    <ul className="d-flex gap-4 align-items-center list-unstyled list-decoration-none">
                      <li>
                        <label> Start date</label>
                        <input
                          type="text"
                          className="form-control"
                          placeholder="yyyy-mm-dd"
                          onChange={($event)=>{setStartDate($event.target.value)}}
                        />
                      </li>
                      <li>
                        <label> End date</label>
                        <input
                          type="text"
                          className="form-control"
                          placeholder="yyyy-mm-dd"
                          onChange={($event)=>{setEndDate($event.target.value)}}
                        />
                      </li>
                      <li>
                        <button className="btn btn-success px-4">Set</button>
                      </li>
                    </ul>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default DriverDashBoard;
