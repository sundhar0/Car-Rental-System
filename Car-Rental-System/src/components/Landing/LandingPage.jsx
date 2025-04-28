import { useNavigate } from "react-router";
import BuyerNavbar from "./LandingNavBar";

function LandingPage() {
    const navigate=useNavigate();
    const Login=()=>{
        navigate("/login")
    }
        return (
            <div className="bg-dark text-white min-vh-100">
              <BuyerNavbar />
        
              <div className="text-center mt-5 px-3">
                <h1 className="display-5 fw-bold">
                  Find, book and rent a car <span className="text-success">Easily</span>
                </h1>
                <p className="lead text-secondary mt-3">
                  Get a car wherever and whenever you <br /> need it with our platform.
                </p>
              </div>
        
              <div className="container mt-5">
                <div className="row bg-white rounded shadow p-4 align-items-center text-dark">
                  <div className="col-md-3 mb-3">
                    <label className="form-label fw-semibold">Location</label>
                    <input type="text" className="form-control" placeholder="Search your location" />
                  </div>
                  <div className="col-md-3 mb-3">
                    <label className="form-label fw-semibold">Pickup date</label>
                    <input type="datetime-local" className="form-control" />
                  </div>
                  <div className="col-md-3 mb-3">
                    <label className="form-label fw-semibold">Return date</label>
                    <input type="datetime-local" className="form-control" />
                  </div>
                  <div className="col-md-3 d-grid">
                    <button className="btn btn-success mt-4">Search</button>
                  </div>
                </div>
              </div>
        
              {/* New Section */}
              <div className="bg-light text-dark py-5 mt-5">
                <div className="text-center mb-4">
                  <button className="btn btn-outline-primary text-uppercase small fw-bold mb-2">How it work</button>
                  <h2 className="fw-bold">What are you in for?</h2>
                </div>
        
                <div className="container">
                  <div className="row text-center">
                    <div className="col-md-4 mb-4">
                      <div className="p-4">
                        <div className="bg-info bg-opacity-10 rounded mb-3" style={{ height: "80px" }}></div>
                        <h5 className="fw-bold">Buy a car</h5>
                      </div>
                    </div>
        
                    <div className="col-md-4 mb-4">
                      <div className="p-4">
                        <div className="bg-info bg-opacity-10 rounded mb-3" style={{ height: "80px" }}></div>
                        <h5 className="fw-bold">Rent a car</h5>
                      </div>
                    </div>
        
                    <div className="col-md-4 mb-4">
                      <div className="p-4">
                        <div className="bg-info bg-opacity-10 rounded mb-3" style={{ height: "80px" }}></div>
                        <h5 className="fw-bold">Customer support</h5>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              {/* End Section */}
            </div>
          );
}
export default LandingPage