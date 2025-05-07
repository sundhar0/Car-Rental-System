import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import profile from "../../assets/image.png";
import { Link } from "react-router";

function BuyerPage() {
  const navigate = useNavigate();

  const [cars, setCars] = useState([]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(9);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(true);
  const [activeFilters, setActiveFilters] = useState({
    carMake: [],
    fuelType: [],
    year: [],
    priceRange: [],
  });

  const fetchCars = async () => {
    try {
      setLoading(true);
      const response = await axios.get(
        `http://localhost:8080/api/car/getAll?page=${page}&size=${size}`
      );
      // Use response.data.list instead of response.data.content
      setCars(response.data.list || []);
      setTotalPages(response.data.totalPages || 0);
    } catch (error) {
      console.error("Error fetching cars:", error);
      setCars([]); // Ensure cars is empty array on error
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCars();
  }, [page, size]);

  const handleFilterChange = (category, value) => {
    setActiveFilters((prev) => ({
      ...prev,
      [category]: prev[category].includes(value)
        ? prev[category].filter((item) => item !== value)
        : [...prev[category], value],
    }));
  };

  const applyFilters = (car) => {
    const { carMake, fuelType, year, priceRange } = activeFilters;

    // Convert all values to strings for case-insensitive comparison
    const carMakeStr = car.carMake?.toString().toLowerCase() || "";
    const fuelTypeStr = car.fuelType?.toString().toLowerCase() || "";
    const yearStr = car.year?.toString().toLowerCase() || "";

    if (
      carMake.length &&
      !carMake.some((m) => m.toLowerCase() === carMakeStr)
    ) {
      return false;
    }

    if (
      fuelType.length &&
      !fuelType.some((f) => f.toLowerCase() === fuelTypeStr)
    ) {
      return false;
    }

    if (year.length && !year.some((y) => y.toString() === yearStr)) {
      return false;
    }

    if (priceRange.length) {
      const price = Number(car.price) || 0;
      const isInRange = priceRange.some((range) => {
        switch (range) {
          case "lessThan1000":
            return price < 1000;
          case "1000to2000":
            return price >= 1000 && price <= 2000;
          case "above2000":
            return price > 2000;
          default:
            return true;
        }
      });
      if (!isInRange) return false;
    }

    return true;
  };

  const filteredCars = cars.filter(applyFilters);

  const viewCarDetails = (carId) => {
    navigate(`/car/${carId}`);
  };

  return (
    <div style={{ minHeight: "100vh" }}>
      <nav
        className="navbar navbar-expand-lg mb-4 p-3"
        style={{ backgroundColor: "#1C2631" }}
      >
        <div className="container">
          <a href="#" className="navbar-brand">
            <h3 className="text-white">CarRent</h3>
          </a>

          <button
            className="navbar-toggler"
            style={{
              boxShadow: "none",
              outline: "none",
              border: "none",
            }}
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navmenu"
          >
            <i className="bi bi-list fs-2" style={{ color: "#00B86B" }}></i>
          </button>

          <div className="collapse navbar-collapse" id="navmenu">
            <ul className="navbar-nav d-flex justify-content-center align-items-center gap-5 ms-auto">
              <li className="nav-item">
                <a
                  href="#"
                  className="nav-link text-white text-decoration-none"
                >
                  Browse Cars
                </a>
              </li>
              <li className="nav-item">
                <div className="input-group">
                  <input
                    type="text"
                    className="form-control border-0"
                    placeholder="Search cars..."
                    aria-label="search"
                    aria-describedby="basic-addon2"
                  />
                  <span
                    className="input-group-text border-0 text-white"
                    id="basic-addon2"
                    style={{ backgroundColor: "#00B86B" }}
                  >
                    <i className="bi bi-search"></i>
                  </span>
                </div>
              </li>
              <li className="nav-item">
                <a href="#">
                  <img
                    src={profile}
                    alt="Profile"
                    className="rounded-circle mx-auto"
                    style={{
                      width: "40px",
                      height: "40px",
                      objectFit: "cover",
                    }}
                  />
                </a>
              </li>
              <li className="nav-item">
                <a href="#" className="nav-link">
                  <button
                    className="btn text-white fw-bold"
                    style={{ backgroundColor: "#00B86B" }}
                  >
                    Sell Your Car
                  </button>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div className="container">
        <h1 className="fw-500 p-4 text-black" style={{ fontSize: "1.5rem" }}>
          Available Cars for Purchase
        </h1>

        {loading ? (
          <div className="text-center py-5">
            <div className="spinner-border text-success" role="status">
              <span className="visually-hidden">Loading...</span>
            </div>
          </div>
        ) : (
          <div className="row">
            {/* Filters */}
            <div className="col-md-3 mb-4">
              <div className="card shadow-lg p-4">
                <h5 className="fw-bold mb-4" style={{ color: "#1C2631" }}>
                  Filter by
                </h5>

                <div className="mb-4">
                  <h6 className="fw-semibold">Make</h6>
                  {["Toyota", "Honda", "BMW", "Mercedes"].map((make, i) => (
                    <div className="form-check" key={i}>
                      <input
                        className="form-check-input"
                        type="checkbox"
                        id={`make${i}`}
                        checked={activeFilters.carMake.includes(make)}
                        onChange={() => handleFilterChange("carMake", make)}
                        style={{ accentColor: "#00B86B" }}
                      />
                      <label className="form-check-label" htmlFor={`make${i}`}>
                        {make}
                      </label>
                    </div>
                  ))}
                </div>

                <div className="mb-4">
                  <h6 className="fw-semibold">Year</h6>
                  {["2020", "2021", "2022", "2023"].map((year, i) => (
                    <div className="form-check" key={i}>
                      <input
                        className="form-check-input"
                        type="checkbox"
                        id={`year${i}`}
                        checked={activeFilters.year.includes(year)}
                        onChange={() => handleFilterChange("year", year)}
                        style={{ accentColor: "#00B86B" }}
                      />
                      <label className="form-check-label" htmlFor={`year${i}`}>
                        {year}
                      </label>
                    </div>
                  ))}
                </div>

                <div className="mb-4">
                  <h6 className="fw-semibold">Fuel Type</h6>
                  {["Petrol", "Diesel", "Electric", "Hybrid"].map((fuel, i) => (
                    <div className="form-check" key={i}>
                      <input
                        className="form-check-input"
                        type="checkbox"
                        id={`fuel${i}`}
                        checked={activeFilters.fuelType.includes(fuel)}
                        onChange={() => handleFilterChange("fuelType", fuel)}
                        style={{ accentColor: "#00B86B" }}
                      />
                      <label className="form-check-label" htmlFor={`fuel${i}`}>
                        {fuel}
                      </label>
                    </div>
                  ))}
                </div>

                <div>
                  <h6 className="fw-semibold">Price Range</h6>
                  {[
                    { label: "Less than $1000", value: "lessThan1000" },
                    { label: "$1000 - $2000", value: "1000to2000" },
                    { label: "Above $2000", value: "above2000" },
                  ].map((price, i) => (
                    <div className="form-check" key={i}>
                      <input
                        className="form-check-input"
                        type="checkbox"
                        id={`price${i}`}
                        checked={activeFilters.priceRange.includes(price.value)}
                        onChange={() =>
                          handleFilterChange("priceRange", price.value)
                        }
                        style={{ accentColor: "#00B86B" }}
                      />
                      <label className="form-check-label" htmlFor={`price${i}`}>
                        {price.label}
                      </label>
                    </div>
                  ))}
                </div>
              </div>
            </div>

            {/* Car Listings */}
            <div className="col-md-9">
              <div className="row">
                {filteredCars.length > 0 ? (
                  filteredCars.map((car) => (
                    <div className="col-md-4 mb-4" key={car.id}>
                      <div className="card shadow-lg h-100">
                        <img
                          src={
                            car.carImage ||
                            "https://via.placeholder.com/300x200?text=Car+Image"
                          }
                          className="card-img-top"
                          alt={`${car.carMake} ${car.carModel}`}
                          style={{ height: "180px", objectFit: "cover" }}
                        />
                        <div className="card-body">
                          <h5 className="card-title fw-semibold">
                            {car.carMake} {car.carModel}
                          </h5>
                          <p className="text-muted mb-2">
                            <span className="text-warning">
                              ★ {car.rating || "4.5"}
                            </span>
                            ({car.reviewCount || "0"} reviews)
                          </p>
                          <ul className="list-unstyled small text-muted">
                            <li>
                              🚗 {car.numberOfSeats || "4"} Seats &nbsp; 🚪{" "}
                              {car.numberOfDoors || "4"} Doors
                            </li>
                            <li>
                              ⚙️ {car.transmission} &nbsp;{" "}
                              {car.airConditioning ? "❄️ Air Conditioning" : ""}
                            </li>
                            <li>
                              ⛽ {car.fuelType} &nbsp; 📅 {car.year}
                            </li>
                          </ul>
                          <div className="d-flex justify-content-between align-items-center mt-3">
                            <div>
                              <span className="fw-bold text-dark">
                                ${car.price.toLocaleString()}
                              </span>
                              <span className="text-muted d-block small">
                                Mileage: {car.mileage} km
                              </span>
                            </div>
                            <Link to={`/singlecar/${car.id}`}>
                              <button
                                type="button"
                                className="btn btn-sm"
                                onClick={() => viewCarDetails(car.id)}
                                style={{
                                  backgroundColor: "#00B86B",
                                  color: "white",
                                }}
                              >
                                View Details →
                              </button>
                            </Link>
                          </div>
                        </div>
                      </div>
                    </div>
                  ))
                ) : (
                  <div className="col-12 text-center py-5">
                    <h5>No cars found matching your filters</h5>
                    <button
                      className="btn text-white mt-3"
                      style={{ backgroundColor: "#00B86B" }}
                      onClick={() =>
                        setActiveFilters({
                          carMake: [],
                          fuelType: [],
                          year: [],
                          priceRange: [],
                        })
                      }
                    >
                      Clear Filters
                    </button>
                  </div>
                )}
              </div>

              {/* Pagination */}
              {filteredCars.length > 0 && (
                <div className="d-flex justify-content-center mt-5">
                  <nav aria-label="Page navigation example">
                    <ul className="pagination">
                      <li
                        className={`page-item ${page === 0 ? "disabled" : ""}`}
                      >
                        <button
                          className="page-link"
                          onClick={() => setPage((p) => Math.max(0, p - 1))}
                          style={{ color: "#00B86B" }}
                        >
                          Previous
                        </button>
                      </li>
                      {[...Array(totalPages).keys()].map((num) => (
                        <li
                          key={num}
                          className={`page-item ${
                            page === num ? "active" : ""
                          }`}
                        >
                          <button
                            className="page-link"
                            onClick={() => setPage(num)}
                            style={{
                              color: page === num ? "white" : "#00B86B",
                              backgroundColor:
                                page === num ? "#00B86B" : "transparent",
                            }}
                          >
                            {num + 1}
                          </button>
                        </li>
                      ))}
                      <li
                        className={`page-item ${
                          page >= totalPages - 1 ? "disabled" : ""
                        }`}
                      >
                        <button
                          className="page-link"
                          onClick={() =>
                            setPage((p) => Math.min(totalPages - 1, p + 1))
                          }
                          style={{ color: "#00B86B" }}
                        >
                          Next
                        </button>
                      </li>
                    </ul>
                  </nav>
                </div>
              )}
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default BuyerPage;
