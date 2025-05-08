import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function BecomeADriver() {
  // Driver info states
  const [perDayCharge, setPerDayCharge] = useState(0);
  const [name, setName] = useState("");
  const [experience, setExperience] = useState(0);
  const [shortDescription, setShortDescription] = useState("");
  const [licenceNo, setLicenceNo] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [msg, setMsg] = useState("");
  const [pImage, setPImage] = useState(null);


  const validateStep1 = () => {
    if (!name.trim()) {
      setMsg("Name cannot be empty");
      return false;
    }
    if (!licenceNo.trim()) {
      setMsg("Licence No cannot be empty");
      return false;
    }
    if (!username.trim()) {
      setMsg("Username cannot be empty");
      return false;
    }
    if (!password.trim()) {
      setMsg("Password cannot be empty");
      return false;
    }
    setMsg("");
    return true;
  };


  const submitDriverInfo = async (e) => {
    e.preventDefault();
    if (!validateStep1()) return;

    const driverData = {
      name: name,
      licenseNo: licenceNo,
      experienceYears: experience,
      shortDescription: shortDescription,
      perDayCharge: perDayCharge,
      user: {
        username: username,
        password: password,
      },
    };

    try {
      // Step 1: Register driver
      const response = await axios.post(
        `http://localhost:8080/api/driver/add`,
        driverData
      );
      setDriverId(response.data.driverId);

      // Step 2: Upload profile image if exists
      if (pImage) {
        await uploadProfileImage(response.data.driverId);
      }

    } catch (err) {
      console.error(err);
      setMsg("Registration failed. Please try again.");
    }
  };

  const uploadProfileImage = async (driverId) => {
    const formData = new FormData();
    formData.append("file", pImage);
    const token = localStorage.getItem("token");

    try {
      await axios.post(
        `http://localhost:8080/api/driver/image/upload/${driverId}`,
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
    } catch (err) {
      console.error("Profile image upload failed:", err);
    }
  };

  return (
    <div style={{ backgroundColor: "#253343", minHeight: "100vh" }}>
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
                <Link
                  to="/customerdashboard"
                  className="nav-link text-white text-decoration-none"
                >
                  Help
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
        {
          // Step 1: Driver Information Form
          <form onSubmit={submitDriverInfo}>
            <div
              className="d-flex justify-content-center align-items-center"
              style={{ height: "85vh" }}
            >
              <div
                className="card shadow-lg mt-5"
                style={{ backgroundColor: "#1C2631", width: "30rem" }}
              >
                <div className="card-body text-white d-flex flex-column justify-content-center align-items-center gap-2">
                  <h5 className="card-title px-4 py-2 rounded">
                    Become a <span style={{ color: "#00B86B" }}>Driver</span>
                  </h5>
                  <small
                    className="fw-lighter text-danger"
                    style={{ fontSize: "12px" }}
                  >
                    {msg}
                  </small>
                  <div
                    className="d-flex flex-column justify-content-center align-items-center gap-4"
                    style={{ fontSize: "12px" }}
                  >
                    <div className="input-groups">
                      <input
                        type="text"
                        className="rounded shadow-lg border-0 p-2 px-2 text-white"
                        style={{
                          boxShadow: "none",
                          outline: "none",
                          backgroundColor: "#30445B",
                          width: "20rem",
                        }}
                        placeholder="Enter your Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                      />
                    </div>
                    <div className="input-groups">
                      <input
                        type="text"
                        className="rounded shadow-lg border-0 p-2 px-2 text-white"
                        style={{
                          boxShadow: "none",
                          outline: "none",
                          backgroundColor: "#30445B",
                          width: "20rem",
                        }}
                        placeholder="Licence No"
                        value={licenceNo}
                        onChange={(e) => setLicenceNo(e.target.value)}
                      />
                    </div>
                    <div className="input-groups">
                      <input
                        type="number"
                        className="rounded shadow-lg border-0 p-2 px-2 text-white"
                        style={{
                          boxShadow: "none",
                          outline: "none",
                          backgroundColor: "#30445B",
                          width: "20rem",
                        }}
                        placeholder="Experience (years)"
                        value={experience}
                        onChange={(e) => setExperience(e.target.value)}
                      />
                    </div>
                    <div className="input-groups">
                      <input
                        type="text"
                        className="rounded shadow-lg border-0 p-2 px-2 text-white"
                        style={{
                          boxShadow: "none",
                          outline: "none",
                          backgroundColor: "#30445B",
                          width: "20rem",
                        }}
                        placeholder="Short Description"
                        value={shortDescription}
                        onChange={(e) => setShortDescription(e.target.value)}
                      />
                    </div>
                    <div className="input-groups">
                      <input
                        type="text"
                        className="rounded shadow-lg border-0 p-2 px-2 text-white"
                        style={{
                          boxShadow: "none",
                          outline: "none",
                          backgroundColor: "#30445B",
                          width: "20rem",
                        }}
                        placeholder="Username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                      />
                    </div>
                    <div className="input-groups">
                      <input
                        type="password"
                        className="rounded shadow-lg border-0 p-2 px-2 text-white"
                        style={{
                          boxShadow: "none",
                          outline: "none",
                          backgroundColor: "#30445B",
                          width: "20rem",
                        }}
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                      />
                    </div>

                    <label className="form-label mt-2">
                      Per Day Charge : {perDayCharge}
                    </label>
                    <input
                      type="range"
                      className="form-range"
                      value={perDayCharge}
                      min="0"
                      max="2000"
                      onChange={(e) => setPerDayCharge(e.target.value)}
                      style={{ accentColor: "#00B86B" }}
                    />

                    <div className="my-3">
                      <label className="form-label fst-italic">
                        Profile picture
                      </label>
                      <input
                        className="form-control border-0"
                        type="file"
                        style={{ background: "#00B86B" }}
                        onChange={(e) => setPImage(e.target.files[0])}
                      />
                      <br />
                      <button
                        className="border-0 p-2 rounded px-3 text-white fw-bold"
                        style={{ backgroundColor: "#00B86B" }}
                        type="submit"
                      >
                        <Link
                          to="/driverdashboard"
                          className="nav-link text-white text-decoration-none"
                        >
                          Continue
                        </Link>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </form>
        
          
        }
      </div>
    </div>
  );
}

export default BecomeADriver;
