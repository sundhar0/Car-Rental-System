import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import profile from "../../assets/image.png";

function DriverList() {
  const [drivers, setDrivers] = useState([]);
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
  }, []);

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
            <ul className="navbar-nav d-flex justify-content-center align-items-center gap-5 ms-auto">
              <li className="nav-item">
                <Link
                  to="/becomedriver"
                  className="nav-link text-white text-decoration-none"
                >
                  Become a Driver
                </Link>
              </li>
              <li className="nav-item">
                <div className="input-group">
                  <input
                    type="text"
                    className="form-control border-0"
                    placeholder="search"
                    aria-label="search"
                    aria-describedby="basic-addon2"
                  />
                  <span
                    className="input-group-text border-0 text-white"
                    id="basic-addon2"
                    style={{ backgroundColor: "#00B86B" }}
                  >
                    <i className="bi bi-search"></i>
                  </span>
                </div>
              </li>
              <li className="nav-item">
                <Link to="/customerdashboard">
                  <img
                    src={profile}
                    alt="Profile"
                    className="rounded-circle mx-auto"
                    style={{
                      width: "40px",
                      height: "40px",
                      objectFit: "cover",
                    }}
                  />
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
        <h1 className="fw-500 p-4 text-black" style={{ fontSize: "1.5rem" }}>
          Available Drivers
        </h1>
        <div className="row">
          {drivers
            .filter((d) => d.driverAvailability == "AVAILABLE")
            .map((driv, index) => (
              <div className="col-lg-3 mb-4" key={index}>
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
                  <Link
                    to={`/driverbook/${driv.name}/${driv.rating}/${driv.shortDescription}/${driv.perDayCharge}/${driv.driverId}`}
                  >
                    <button className="btn btn-success">Book Driver</button>
                  </Link>
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

export default DriverList;
