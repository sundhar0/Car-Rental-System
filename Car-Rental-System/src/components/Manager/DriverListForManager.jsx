import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function DriverListManager() {
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

  const deleteDriver = (driverId)=>{
    try{
        const response = axios.delete(`http://localhost:8080/api/driver/delete/${driverId}`)
        let temp = [...drivers]
        temp = temp.filter(d => d.driverId != driverId)
        setDrivers(temp)
    }
    catch(err){
        console.log(err)
    }
  }

  return (
    <div
      className=""
      style={{minHeight: "100vh" }}
    >
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
                  to="/becomedriver"
                  className="nav-link text-white text-decoration-none"
                >
                  Become a Driver
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
          {drivers.map((driv, index) => (
            <div className="col-md-4 mb-4" key={index}>
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
                <p className="fw-bold">
                &#8377;{driv.perDayCharge}/per day
                </p>
                <p className="fw-bold mt-2">⭐ {driv.rating}</p>
                <div className="d-flex gap-2 align-items-center justify-content-center"> 
                  <button className="btn btn-danger" onClick={()=>{deleteDriver(driv.driverId)}}>Delete</button>
                  <button className="btn text-white" style={{backgroundColor:'#00B86B'}}>Update</button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default DriverListManager;
