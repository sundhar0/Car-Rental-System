import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function DriverListManager() {
  const [drivers, setDrivers] = useState([]);
  const [experienceYears, setExperience] = useState(null);
  const [perDayCharge, setPerDayCharge] = useState(null);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(8);
  const [totalPages, setTotalPages] = useState(0);

  const getAllDrivers = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/driver/getAll?page=${page}&size=${size}`
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

  const deleteDriver = async (driverId) => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/api/driver/delete/${driverId}`
      );
      let temp = [...drivers];
      temp = temp.filter((d) => d.driverId != driverId);
      setDrivers(temp);
    } catch (err) {
      console.log(err);
    }
  };

  const updateDriver = async ($e, driverId) => {
    $e.preventDefault();
    try {
      const response = await axios.put(
        `http://localhost:8080/api/driver/update/${driverId}?experienceYears=${experienceYears}&perDayCharge=${perDayCharge}`
      );

      const updatedDrivers = drivers.map((driver) =>
        driver.driverId === driverId
          ? { ...driver, experienceYears, perDayCharge }
          : driver
      );
      setDrivers(updatedDrivers);
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
            <ul className="navbar-nav d-flex align-items-center gap-5 ms-auto">
              
              <li className="nav-item">
                <Link
                  to="/driverApproval"
                  className="nav-link text-white text-decoration-none"
                >
                  Approvals
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
              <div className="input-group" style={{ width: "250px" }}>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search"
                  aria-label="Search"
                />
                <button
                  className="btn text-white"
                  type="button"
                  style={{ backgroundColor: "#00B86B" }}
                >
                  <i className="bi bi-search"></i>
                </button>
              </div>
            </ul>
          </div>
        </div>
      </nav>
      <div className="container">
        <h1 className="fw-500 p-4 text-black" style={{ fontSize: "1.5rem" }}>
          Drivers
        </h1>
        <div className="row">
          {drivers
            .filter((d) => d.driverAvailability == "AVAILABLE")
            .map((driv, index) => (
              <div className="col-md-3 mb-4" key={index}>
                <div className="card text-center p-3 shadow-lg">
                  <img
                    src={driv.profilePic || "https://via.placeholder.com/100"}
                    alt="Profile"
                    className="rounded-circle mx-auto mb-3"
                    style={{
                      width: "100px",
                      height: "100px",
                      objectFit: "cover",
                    }}
                  />
                  <h6 className="fw-bold">{driv.name}</h6>
                  <p className="fw-light">
                    {driv.experienceYears} years experience
                  </p>
                  <p className="fw-bold">&#8377;{driv.perDayCharge}/per day</p>
                  <p className="fw-bold mt-2">⭐ {driv.rating}</p>
                  <div className="d-flex gap-2 align-items-center justify-content-center">
                    <button
                      className="btn btn-danger"
                      onClick={() => {
                        deleteDriver(driv.driverId);
                      }}
                    >
                      Delete
                    </button>
                    <button
                      className="btn text-white"
                      style={{ backgroundColor: "#00B86B" }}
                      data-bs-toggle="modal"
                      data-bs-target={`#update-${driv.driverId}`}
                    >
                      Update
                    </button>
                  </div>
                  <div
                    className="modal fade"
                    id={`update-${driv.driverId}`}
                    aria-labelledby="exampleModalLabel"
                    aria-hidden="true"
                  >
                    <div className="modal-dialog modal-dialog-centered">
                      <div className="modal-content">
                        <div className="modal-header">
                          <h4 className="modal-title" id="exampleModalLabel">
                            Update the Driver Record
                          </h4>
                          <button
                            type="button"
                            className="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                          ></button>
                        </div>
                        <div className="modal-body">
                          <form
                            onSubmit={($e) => {
                              updateDriver($e, driv.driverId);
                            }}
                          >
                            <div className="mb-4 text-start">
                              <label className="fs-4 mb-3">
                                Enter the experience
                              </label>
                              <input
                                type="text"
                                placeholder={driv.experienceYears}
                                className="form-control"
                                onChange={($event) => {
                                  setExperience($event.target.value);
                                }}
                              />
                            </div>
                            <div className="mb-4 text-start">
                              <label className="fs-5 mb-3">
                                Change the per day charge
                              </label>
                              <input
                                type="text"
                                placeholder={driv.perDayCharge}
                                className="form-control"
                                onChange={($event) => {
                                  setPerDayCharge($event.target.value);
                                }}
                              />
                            </div>
                            <div className="mb-4">
                              <input
                                type="submit"
                                value="Commit the changes"
                                className="btn fw-bold text-white"
                                style={{ backgroundColor: "#00B86B" }}
                                data-bs-dismiss="modal"
                              />
                            </div>
                          </form>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            ))}
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

export default DriverListManager;
