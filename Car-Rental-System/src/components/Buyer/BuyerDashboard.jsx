function BuyerPage(){

    return (    
          <div className="container-fluid bg-light min-vh-100 py-5">
            <div className="row">
              {/* Filters */}
              <div className="col-md-3 bg-white rounded shadow p-4">
                <h5 className="fw-bold mb-4">Filter by</h5>
    
                <div className="mb-4">
                  <h6 className="fw-semibold">Model</h6>
                  {["SUV", "Hatchback", "Sedan", "Luxury"].map((model, i) => (
                    <div className="form-check" key={i}>
                      <input className="form-check-input" type="checkbox" id={`model${i}`} />
                      <label className="form-check-label" htmlFor={`model${i}`}>{model}</label>
                    </div>
                  ))}
                </div>
    
                <div className="mb-4">
                  <h6 className="fw-semibold">Year</h6>
                  {["2022", "2023", "2024"].map((year, i) => (
                    <div className="form-check" key={i}>
                      <input className="form-check-input" type="checkbox" id={`year${i}`} />
                      <label className="form-check-label" htmlFor={`year${i}`}>{year}</label>
                    </div>
                  ))}
                </div>
    
                <div className="mb-4">
                  <h6 className="fw-semibold">Fuel Type</h6>
                  {["Petrol", "Diesel", "Electric"].map((fuel, i) => (
                    <div className="form-check" key={i}>
                      <input className="form-check-input" type="checkbox" id={`fuel${i}`} />
                      <label className="form-check-label" htmlFor={`fuel${i}`}>{fuel}</label>
                    </div>
                  ))}
                </div>
    
                <div>
                  <h6 className="fw-semibold">Price</h6>
                  {["Less than $1000", "$1000 - $2000", "Above $2000"].map((price, i) => (
                    <div className="form-check" key={i}>
                      <input className="form-check-input" type="checkbox" id={`price${i}`} />
                      <label className="form-check-label" htmlFor={`price${i}`}>{price}</label>
                    </div>
                  ))}
                </div>
              </div>
    
              {/* Car Listings */}
              <div className="col-md-9">
                <h3 className="fw-bold text-dark mb-4">Available Cars for Buy</h3>
                <div className="row">
                  {[...Array(6)].map((_, idx) => (
                    <div className="col-md-4 mb-4" key={idx}>
                      <div className="card shadow-sm h-100">
                        <div className="card-body">
                          <h5 className="card-title fw-semibold">Jaguar XE L P250</h5>
                          <p className="text-muted mb-2">
                            <span className="text-warning">★ 4.8</span> (2,436 reviews)
                          </p>
                          <ul className="list-unstyled small text-muted">
                            <li>🚗 4 Passengers &nbsp; 🚪 4 Doors</li>
                            <li>⚙️ Auto &nbsp; ❄️ Air Conditioning</li>
                          </ul>
                          <div className="d-flex justify-content-between align-items-center mt-3">
                            <div>
                              <span className="fw-bold text-dark">$1,800</span>
                            </div>
                            <button className="btn btn-primary btn-sm">Buy Now →</button>
                          </div>
                        </div>
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          </div>
      );
}
export default BuyerPage