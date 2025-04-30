import axios from "axios";
import { useState } from "react";

function BecomeADriver() {
  const [perDayCharge, setPerDayCharge] = useState(0);
  const [name, setName] = useState(null);
  const [experience, setExperience] = useState(0);
  const [shortDescription, setShortDescription] = useState(null);
  const [licenceNo, setLicenceNo] = useState(null);
  const [username, setUsername] = useState(null);
  const [password, setPassword] = useState(null);
  const [msg, setMsg] = useState(null);


  const addDriver = async ($e) => {
    $e.preventDefault();
    if(name == null || name == ""){
      return setMsg("Name cannot be empty")
    }
    else{
      setMsg(null)
    }
    
    if(licenceNo == null || licenceNo == ""){
      return setMsg("Licence No cannot be empty")
    }
    else{
      setMsg(null)
    }
    if(username == null || username == "" || username === undefined){
      return setMsg("Username cannot be empty")
    }
    else{
      setMsg(null)
    }
    if(password == null || password == "" || password == undefined){
      return setMsg("Password cannot be empty")
    }
    else{
      setMsg(null)
    }


    let obj = {
      name: name,
      licenseNo: licenceNo,
      experienceYears: experience,
      shortDescription: shortDescription,
      perDayCharge: perDayCharge,
      user: {
        username: username,
        password: password
      }
    };

    try{
        let response = await axios.post(
            `http://localhost:8080/api/driver/add`,
            obj
          );
          console.log(response);
    }
    catch(err){
        console.log(err)
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
                <a href="#" className="nav-link text-white">
                  Home
                </a>
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
      <div className="container">
        <form onSubmit={($event) => addDriver($event)}>
          <div
            className="d-flex justify-content-center align-items-center"
            style={{ height: "85vh" }}
          >
            <div
              className="card shadow-lg mt-3"
              style={{ backgroundColor: "#1C2631", width: "25rem" }}  
            >
              <div className="card-body text-white d-flex flex-column justify-content-center align-items-center gap-2">
                <h5 className="card-title px-4 py-2 rounded">
                  Become a <span style={{ color: "#00B86B" }}>Driver</span>
                </h5>
                <small
                  className="fw-lighter text-danger"  
                  style={{ fontSize: "12px" }}
                >{msg}</small>
                <div
                  className="d-flex flex-column justify-content-center align-items-center gap-2 mb-3"
                  style={{ fontSize: "12px" }}
                >
                  <div className="input-groups">
                    <input
                      type="text"
                      name=""
                      id=""
                      className="rounded shadow-lg border-0 p-2 px-2 text-white"
                      style={{
                        boxShadow: "none",
                        outline: "none",
                        backgroundColor: "#30445B",
                        width: "20rem",
                      }}
                      placeholder="Enter your Name"
                      onChange={($event) => {
                        setName($event.target.value);
                      }}
                    />
                  </div>
                  <div className="input-groups">
                    <input
                      type="text"
                      name=""
                      id=""
                      className="rounded shadow-lg border-0 p-2 px-2 text-white"
                      style={{
                        boxShadow: "none",
                        outline: "none",
                        backgroundColor: "#30445B",
                        width: "20rem",
                      }}
                      placeholder="Licence No"
                      onChange={($event) => {
                        setLicenceNo($event.target.value);
                      }}
                    />
                  </div>
                  <div className="input-groups">
                    <input
                      type="text"
                      name=""
                      id=""
                      className="rounded shadow-lg border-0 p-2 px-2 text-white"
                      style={{
                        boxShadow: "none",
                        outline: "none",
                        backgroundColor: "#30445B",
                        width: "20rem",
                      }}
                      placeholder="Experience"
                      onChange={($event) => {
                        setExperience($event.target.value);
                      }}
                    />
                  </div>
                  <div className="input-groups">
                    <input
                      type="text"
                      name=""
                      id=""
                      className="rounded shadow-lg border-0 p-2 px-2 text-white"
                      style={{
                        boxShadow: "none",
                        outline: "none",
                        backgroundColor: "#30445B",
                        width: "20rem",
                      }}
                      placeholder="Short Description"
                      onChange={($event) => {
                        setShortDescription($event.target.value);
                      }}
                    />
                  </div>
                  <div className="input-groups">
                    <input
                      type="text"
                      name=""
                      id=""
                      className="rounded shadow-lg border-0 p-2 px-2 text-white"
                      style={{
                        boxShadow: "none",
                        outline: "none",
                        backgroundColor: "#30445B",
                        width: "20rem",
                      }}
                      placeholder="Username"
                      onChange={($event) => {
                        setUsername($event.target.value);
                      }}
                    />
                  </div>
                  <div className="input-groups">
                    <input
                      type="password"
                      name="password"
                      id="password"
                      className="rounded shadow-lg border-0 p-2 px-2 text-white"
                      style={{
                        boxShadow: "none",
                        outline: "none",
                        backgroundColor: "#30445B",
                        width: "20rem",
                      }}
                      placeholder="Password"
                      onChange={($event) => {
                        setPassword($event.target.value);
                      }}
                    />
                  </div>

                  <label form="customRange2" className="form-label mt-2">
                    Per Day Charge : {perDayCharge}
                  </label>
                  <input
                    type="range"
                    className="form-range"
                    value={perDayCharge}
                    min="0"
                    max="2000"
                    id="customRange2"
                    onChange={($e) => {
                      setPerDayCharge($e.target.value);
                    }}
                    style={{ accentColor: "#00B86B" }}
                  />
                  <div className="my-3">
                    <label form="formFileMultiple" className="form-label fst-italic">
                      Aadhaar Card/Driving Licence/AddressProof
                    </label>
                    <input
                      className="form-control border-0"
                      type="file"
                      id="formFileMultiple"
                      multiple
                      style={{ background: "#00B86B" }}
                    />
                  </div>
                </div>
                <button
                  className="border-0 p-2 rounded px-3 text-white fw-bold"
                  style={{ backgroundColor: "#00B86B" }}
                  type="submit"
                >
                  Continue
                </button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default BecomeADriver;
