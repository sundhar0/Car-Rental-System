import React, { useState } from "react";
import "./CarDetails.css";
import { Link, useNavigate } from "react-router";
import axios from "axios";

function SellCarVerification() {
  const [carMake, setCarMake] = useState("");
  const [carModel, setCarModel] = useState("");
  const [year, setYear] = useState("");
  const [licensePlateNumber, setLicensePlateNumber] = useState("");
  const [vehicleRegistrationNumber, setVehicleRegistrationNumber] =
    useState("");
  const [carColor, setCarColor] = useState("");
  const [price, setPrice] = useState("");
  const [brand, setBrand] = useState("");
  const [fuelType, setFuelType] = useState("");
  const [transmission, setTransmission] = useState("");
  const [mileage, setMileage] = useState("");
  const [showAlert, setShowAlert] = useState(false);
  const [msg, setMsg] = useState(null); // Message for validation errors
  const [pImage, setPImage] = useState(null); // Profile image
  const navigate = useNavigate();

  const addCar = async ($e) => {
    $e.preventDefault();

    // Validate required fields
    if (
      !carMake ||
      !carModel ||
      !year ||
      !licensePlateNumber ||
      !vehicleRegistrationNumber ||
      !carColor ||
      !price ||
      !brand ||
      !fuelType ||
      !transmission ||
      !mileage
    ) {
      setMsg("All fields are required");
      return;
    } else {
      setMsg(null);
    }

    const carData = {
      carMake,
      carModel,
      year,
      licensePlateNumber,
      vehicleRegistrationNumber,
      carColor,
      price,
      brand,
      fuelType,
      transmission,
      mileage,
    };

    const token = localStorage.getItem("token");
    if (!token) {
      alert("No token found!");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/api/car/add/2",
        carData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );
      console.log("Car added successfully:", response.data);
      setShowAlert(true);

      if (pImage) {
        await uploadCarImage(response.data.carId);
      }
    } catch (error) {
      console.error("Error adding car:", error);
      alert("Failed to submit car. Please try again.");
    }
  };

  const uploadCarImage = async (carId) => {
    if (!pImage) {
      alert("Image not selected");
      return;
    }

    const formData = new FormData();
    formData.append("file", pImage);
    const token = localStorage.getItem("token");

    try {
      const resp = await axios.post(
        `http://localhost:8080/api/car/image/upload/32`,
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log(resp);
      alert("Image uploaded...");
    } catch (err) {
      console.error(err);
      alert("Image upload failed.");
    }
  };

  const carAdd = () => {
    setShowAlert(false);
  };

  return (
    <div>
      <div>
        <nav
          className="navbar navbar-expand-lg p-3"
          style={{ backgroundColor: "#1C2631" }}
        >
          <div className="container">
            <a href="#" className="navbar-brand">
              <h3 className="text-white">CarRent</h3>
            </a>

            <button
              className="navbar-toggler border-0"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navmenu"
            >
              <i className="bi bi-list fs-2" style={{ color: "#00B86B" }}></i>
            </button>

            <div className="collapse navbar-collapse" id="navmenu">
              <ul className="navbar-nav d-flex align-items-center gap-4 ms-auto">
                <li className="nav-item">
                  <Link to={"/driverlist"}>
                    <a href="#" className="nav-link text-white">
                      Driver List
                    </a>
                  </Link>
                </li>
                <li className="nav-item">
                  <a href="#" className="nav-link text-white">
                    Help
                  </a>
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
      </div>
      <div className="d-flex justify-content-center align-items-center  mt-5">
        <div
          className="card p-5 registration-card w-75 overflow-auto"
          style={{ minHeight: "80vh" }}
        >
          <h3 className="mb-4 text-white">
            Sell Your Car - Document Verification
          </h3>
          <form className="row g-3" onSubmit={($e) => addCar($e)}>
            <div className="row mb-3">
              <div className="col-md-6 mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Car Make"
                  onChange={($event) => setCarMake($event.target.value)}
                />
              </div>
              <div className="col-md-6 mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Car Model"
                  onChange={($event) => setCarModel($event.target.value)}
                />
              </div>
              <div className="col-md-6 mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Year"
                  onChange={($event) => setYear($event.target.value)}
                />
              </div>
              <div className="col-md-6 mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Licence Plate Number"
                  onChange={($event) =>
                    setLicensePlateNumber($event.target.value)
                  }
                />
              </div>
              <div className="col-md-6 mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Vehicle Registration Number"
                  onChange={($event) =>
                    setVehicleRegistrationNumber($event.target.value)
                  }
                />
              </div>
              <div className="col-md-6 mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Car Color"
                  onChange={($event) => setCarColor($event.target.value)}
                />
              </div>
              <div className="col-md-6 mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Price"
                  onChange={($event) => setPrice($event.target.value)}
                />
              </div>
              <div className="col-md-6 mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Brand"
                  onChange={($event) => setBrand($event.target.value)}
                />
              </div>
              <div className="col-md-6 mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Fuel Type"
                  onChange={($event) => setFuelType($event.target.value)}
                />
              </div>
              <div className="col-md-6 mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Transmission"
                  onChange={($event) => setTransmission($event.target.value)}
                />
              </div>
              <div className="col-md-6 mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Mileage"
                  onChange={($event) => setMileage($event.target.value)}
                />
              </div>
            </div>

            <div className="my-3">
              <label className="form-label fst-italic">Upload Car Image</label>
              <input
                className="form-control border-0"
                type="file"
                id="formFileMultiple"
                multiple
                onChange={(e) => setPImage(e.target.files[0])}
              />
            </div>

            <small className="text-danger">{msg}</small>

            <button
              className="border-0 p-2 rounded px-3 text-white fw-bold"
              style={{ backgroundColor: "#00B86B" }}
              type="submit"
            >
              Submit
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default SellCarVerification;
