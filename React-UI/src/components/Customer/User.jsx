import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import RentalHistory from "./RentalHistory";
import BuyerHistory from "./BuyerHistory";
import SellerHistory from "./SellerHistory";

function UserDashboard() {
  const [activeTab, setActiveTab] = useState("rentals");
  const [customer, setCustomer] = useState({});

  const fetchCustomer = () => {
    // Add your customer fetching logic here
    // Example:
    // const customerData = await fetchCustomerFromAPI();
    // setCustomer(customerData);
  };

  const renderContent = () => {
    switch (activeTab) {
      case "rentals":
        return <div><RentalHistory/></div>;
      case "buying":
        return <div><BuyerHistory/></div>;
      case "selling":
        return <div><SellerHistory/></div>;
      case "given":
        return <div>Given content</div>;
      default:
        return <div>Select a tab</div>;
    }
  };

  return (
    <div className="" style={{ minHeight: "100vh", backgroundColor: "#F4F6F9" }}>
      <div className="container py-5" style={{ maxWidth: "1000px" }}>
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
}

export default UserDashboard;