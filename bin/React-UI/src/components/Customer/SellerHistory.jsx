import React, { useEffect, useState } from 'react';
import axios from 'axios';
import RentalChartDashboard from './RentalChartDashboard';
import SellerChartDashboard from './SellerChartDashboard';

const SellerHistory = () => {
  const [sales, setSales] = useState([]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(5);
  const [totalPages, setTotalPages] = useState(0);
  const [selectedSell, setSelectedSell] = useState(null);
  const [showInvoice, setShowInvoice] = useState(false);
  const [carTypes, setCarTypes] = useState([]); // State to hold car types
  const [selectedCarType, setSelectedCarType] = useState('All Types'); // Selected car type filter

  // Fetch rentals from the API with the selected car type filter
  const fetchSales = async () => {
    try {
      let url = `http://localhost:8081/api/rentals/allrentals?page=${page}&size=${size}`;
      if (selectedCarType !== 'All Types') {
        url += `&carModel=${selectedCarType}`; // Assuming the API accepts a carModel query parameter
      }

      const response = await axios.get(url);
      setSales(response.data.list);
      setTotalPages(response.data.totalPages);
    } catch (error) {
      console.error('Error fetching rental history:', error);
    }
  };

  // Fetch car models for the filter dropdown
  const fetchCarModels = async () => {
    try {
      const response = await axios.get('http://localhost:8081/api/cars/models');
      setCarTypes(response.data); // Assuming the response is an array of car models
    } catch (error) {
      console.error('Error fetching car models:', error);
    }
  };

  // Run fetch functions when the component mounts or when page, size, or selectedCarType changes
  useEffect(() => {
    fetchSales();
    fetchCarModels();
  }, [page, size, selectedCarType]);

  return (
    <div className="container py-4">
      <h3 className="fw-bold mb-4">Seller History</h3>

      {/* Summary Cards */}
      <div className="row mb-4">
        <div className="col-md-3">
          <div className="card text-center">
            <div className="card-body">
              <h6 className="text-muted">Total Sales</h6>
              <h4>{rentals.length}</h4>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card text-center">
            <div className="card-body">
              <h6 className="text-muted">Total Distance</h6>
              <h4>0 km</h4>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card text-center">
            <div className="card-body">
              <h6 className="text-muted">Average Rating</h6>
              <h4>0.0/5.0</h4>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card text-center">
            <div className="card-body">
              <h6 className="text-muted">Total Revenue</h6>
              <h4>$0</h4>
            </div>
          </div>
        </div>
      </div>

      {/* Filters */}
      <div className="row align-items-end mb-4">
        <div className="col-md-3">
          <label htmlFor="dateRange" className="form-label">Date Range</label>
          <select className="form-select" id="dateRange">
            <option>Last 30 days</option>
            <option>Last 60 days</option>
            <option>Last year</option>
          </select>
        </div>
        <div className="col-md-3">
          <label htmlFor="carType" className="form-label">Car Type</label>
          <select
            className="form-select"
            id="carType"
            value={selectedCarType} // Bind selected car type
            onChange={(e) => setSelectedCarType(e.target.value)} // Update selected car type
          >
            <option>All Types</option>
            {carTypes.map((type, index) => (
              <option key={index} value={type}>{type}</option>
            ))}
          </select>
        </div>
        <div className="col-md-3">
          <label htmlFor="status" className="form-label">Status</label>
          <select className="form-select" id="status">
            <option>All Status</option>
            <option>Completed</option>
            <option>Cancelled</option>
          </select>
        </div>
        <div className="col-md-3 d-grid">
          <button className="btn btn-success" onClick={() => fetchSales()}>Apply filters</button>
        </div>
      </div>

      <SellerChartDashboard sales={sales} />

      {/* Rentals Table */}
      <div className="card">
        <div className="table-responsive">
          <table className="table mb-0">
            <thead>
              <tr>
                <th>Car Details</th>
                <th>Cost</th>
                <th>Status</th>
                <th>Rating</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {sales.map((sell, index) => (
                <tr key={index}>
                  <td>
                    <strong>{sell.car?.carMake} {sell.car?.carModel}</strong><br />
                    <span className="text-muted">{sell.car?.vehicleRegistrationNumber}</span>
                  </td>
                  <td>{sell.startDate} - {sell.endDate}</td>
                  <td>${sell.cost.toFixed(2)}</td>
                  <td>
                    <span className={`badge ${sell.status === 'Completed' ? 'bg-success' : 'bg-warning'}`}>
                      {sell.status}
                    </span>
                  </td>
                  <td>{sell.rating}</td>
                  <td>
                    <button
                      className="btn btn-outline-primary btn-sm"
                      onClick={() => {
                        setSelectedSell(sell);
                        setShowInvoice(true);
                      }}
                    >
                      View Invoice
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {/* Pagination */}
      <div className="d-flex justify-content-center mt-4">
        <nav>
          <ul className="pagination">
            <li className={`page-item ${page === 0 ? 'disabled' : ''}`}>
              <button className="page-link" onClick={() => setPage(page - 1)}>Previous</button>
            </li>
            <li className="page-item disabled">
              <span className="page-link">Page {page + 1} of {totalPages}</span>
            </li>
            <li className={`page-item ${page === totalPages - 1 ? 'disabled' : ''}`}>
              <button className="page-link" onClick={() => setPage(page + 1)}>Next</button>
            </li>
          </ul>
        </nav>
      </div>

      {/* Invoice Modal */}
      {selectedRental && showInvoice && (
        <div className={`modal fade show`} style={{ display: 'block', backgroundColor: 'rgba(0,0,0,0.5)' }}>
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Sell Invoice</h5>
                <button type="button" className="btn-close" onClick={() => setShowInvoice(false)}></button>
              </div>
              <div className="modal-body">
                <p><strong>Car:</strong> {selectedSell.car?.carMake} {selectedSell.car?.carModel}</p>
                <p><strong>Cost:</strong> ${selectedSell.cost.toFixed(2)}</p>
                <p><strong>Status:</strong> {selectedSell.status}</p>
                <p><strong>Rating:</strong> {selectedSell.rating}</p>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" onClick={() => setShowInvoice(false)}>Close</button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default SellerHistory;
