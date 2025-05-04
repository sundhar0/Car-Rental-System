import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router';

const CustomerDashboard = () => {
  const [activeTab, setActiveTab] = useState('rentals');
  const navigate=useNavigate()
  const AddCars=()=>{
    navigate("/becomeaSeller")
  }

  const renderContent = () => {
    switch (activeTab) {
      case 'rentals':
        return <p>Not done anything yet.</p>;
      case 'buying':
        return <p>Not done anything yet.</p>;
      case 'selling':
        return (
          <>
            <button className="btn btn-success mb-3" onClick={()=>{AddCars()}}>Add New Car for Sale</button>
            <p>Not done anything yet.</p>
          </>
        );
      case 'given':
        return (
          <>
            <button className="btn btn-primary mb-3">Add New Car for Rent</button>
            <p>Not done anything yet.</p>
          </>
        );
      default:
        return null;
    }
  };

  return (
    <div className="container mt-5">
      <div className="card shadow p-4">
        <h3>Customer Dashboard</h3>
        <p><strong>Name:</strong> John Doe</p>
        <p><strong>Email:</strong> john@example.com</p>
        <p><strong>Phone:</strong> 9876543210</p>

        <ul className="nav nav-tabs my-3">
          <li className="nav-item">
            <button
              className={`nav-link ${activeTab === 'rentals' ? 'active' : ''}`}
              onClick={() => setActiveTab('rentals')}
            >
              Rentals
            </button>
          </li>
          <li className="nav-item">
            <button
              className={`nav-link ${activeTab === 'buying' ? 'active' : ''}`}
              onClick={() => setActiveTab('buying')}
            >
              Buying
            </button>
          </li>
          <li className="nav-item">
            <button
              className={`nav-link ${activeTab === 'selling' ? 'active' : ''}`}
              onClick={() => setActiveTab('selling')}
            >
              Selling
            </button>
          </li>
          <li className="nav-item">
            <button
              className={`nav-link ${activeTab === 'given' ? 'active' : ''}`}
              onClick={() => setActiveTab('given')}
            >
              Given For Rent
            </button>
          </li>
        </ul>

        <div className="tab-content">
          {renderContent()}
        </div>
      </div>
    </div>
  );
};

export default CustomerDashboard;
