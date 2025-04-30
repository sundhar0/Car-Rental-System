import { Link, useParams } from "react-router-dom";
import LeftSideImage from "../../assets/image.png";
import { useState } from "react";
import axios from "axios";

function DriverBook() {
  const { name, rating, shortDescription, perDayCharge } = useParams();
  const [rentalStart, setRentalStart] = useState("2025-04-29");
  const [rentalEnd, setRentalEnd] = useState("2025-05-29");

  const bookTheRide = async ($event) => {
    $event.preventDefault();
    let obj = {
      rentalStart: rentalStart,
      rentalEnd: rentalEnd
    };

    try {
      const response = await axios.post(
        `http://localhost:8080/api/rentWithDriver/rent/13/5/4`,
        obj
      );
      console.log("Booking Successful:", response.data);
    } catch (error) {
      console.error("Booking Failed:", error);
      alert("Booking Failed");
    }
  };

  return (
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
            <ul className="navbar-nav d-flex align-items-center gap-4 ms-auto ">
              <li className="nav-item ">
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

      <div
        style={{
          backgroundColor: "#253343",
          minHeight: "88.3vh",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <div className="col-md-10">
          <div className="d-flex rounded shadow-lg overflow-hidden">
            <div
              className="col-md-6 card-left d-flex flex-column justify-content-between text-center"
              style={{ backgroundColor: "#00B86B" }}
            >
              <div className="text-white p-5 text-start">
                <h4>Assigning a driver of your preference</h4>
                <p>We believe in people's preference.</p>
              </div>
              <div>
                <img
                  src={LeftSideImage}
                  alt="driver illustration"
                  className="img-fluid mt-5"
                />
              </div>
            </div>

            <div className="col-md-6 card-right bg-white">
              <div className="d-flex justify-content-between align-items-start p-3">
                <div>
                  <h5>
                    <strong>{name}</strong>
                  </h5>
                  <div>
                    ⭐ <small className="text-muted">{rating}</small>
                  </div>
                </div>
                <div>❤️</div>
              </div>
              <p className="mt-3 text-muted px-3">{shortDescription}</p>

              <div className="row mt-3 p-3">
                <div className="col-6">
                  <strong>Type Car</strong>
                  <br />
                  Sport
                </div>
                <div className="col-6">
                  <strong>Total Rides</strong>
                  <br />
                  2 Person
                </div>
                <div className="col-6 mt-2">
                  <strong>Steering</strong>
                  <br />
                  Manual
                </div>
              </div>

              <div className="mt-4 d-flex justify-content-between align-items-center p-4">
                <div>
                  <span className="fw-bold">&#8377;{perDayCharge}</span>
                  <span className="text-muted"> / days</span>
                  <br />
                  <span className="text-muted">
                    <del>&#8377;2000.00</del>
                  </span>
                </div>
                <form onSubmit={($event)=>bookTheRide($event)}>
                <button
                  className="border p-2 btn btn-primary"
                  type="submit"
                >
                  Confirm
                </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default DriverBook;
