import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

const UserDashboard = () => {
  const [activeTab, setActiveTab] = useState("rentals");
  const [customer, setCustomer] = useState({});
  const fetchCustomer=()=>{
    try{
      const response=axios.get(`http://localhost:8080/api/userLogin/userDetails`,{
        headers:{
          Authorization:`Bearer ${localStorage.getItem("token")}`,
        },
      });
      setCustomer(response.data);
    }
    catch(err){
      console.error("Error fetching customer:",err)
    }
  };
  useEffect(()=>{
    fetchCustomer();
  },[])


  const renderContent = () => {
    switch (activeTab) {
      case "rentals":
        return <p className="text-muted">You have no rental history yet.</p>;
      case "buying":
        return <p className="text-muted">No cars purchased yet.</p>;
      case "selling":
        return (
          <>
            <button className="btn btn-success mb-3">Add New Car for Sale</button>
            <p className="text-muted">You haven’t listed any cars for sale.</p>
          </>
        );
      case "given":
        return (
          <>
            <button className="btn btn-primary mb-3">Add New Car for Rent</button>
            <p className="text-muted">No cars added for rental yet.</p>
          </>
        );
      default:
        return null;
    }
  };

  return (
    <div className="" style={{ minHeight: "100vh", backgroundColor: "#F4F6F9" }}>
      <div
        className="container py-5"
        style={{ maxWidth: "1000px" }}
      >
        <div className="flex-grow-1 p-3">
        <div className="container mt-3">
          <h1 className="fw-bold" style={{ color: "#253343" }}>Customer Dashboard</h1>
          <div className="m-5">
            <h3>Welcome, {customer.name}</h3>
          </div>

        <div className="row g-4 mb-4">
          <div className="col-md-4">
            <div className="bg-white p-4 rounded shadow-sm text-center">
              <small className="text-muted">Email</small>
              <h6 className="mt-2">john@example.com</h6>
            </div>
          </div>
          <div className="col-md-4">
            <div className="bg-white p-4 rounded shadow-sm text-center">
              <small className="text-muted">Phone</small>
              <h6 className="mt-2">9876543210</h6>
            </div>
          </div>
          <div className="col-md-4">
            <div className="bg-white p-4 rounded shadow-sm text-center">
              <small className="text-muted">Customer Since</small>
              <h6 className="mt-2">Jan 2024</h6>
            </div>
          </div>
        </div>
        </div>
        </div>

        <div className="card border-0 shadow-sm">
          <div className="card-body">
            <ul className="nav nav-tabs mb-4">
              {["rentals", "buying", "selling", "given"].map((tab) => (
                <li className="nav-item" key={tab}>
                  <button
                    className={`nav-link ${activeTab === tab ? "active fw-bold" : ""}`}
                    onClick={() => setActiveTab(tab)}
                  >
                    {tab.charAt(0).toUpperCase() + tab.slice(1)}
                  </button>
                </li>
              ))}
            </ul>
            <div>{renderContent()}</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserDashboard;
