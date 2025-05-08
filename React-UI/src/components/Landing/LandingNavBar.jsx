import { Link } from "react-router";

function BuyerNavbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-4 py-3">
      <a className="navbar-brand text-success fw-bold" href="#">
        RENTCARS
      </a>
      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
      <div
        className="collapse navbar-collapse justify-content-between"
        id="navbarNav"
      >
        <ul className="navbar-nav">
          <li className="nav-item mx-2">
            <a className="nav-link" href="#">
              Become a renter
            </a>
          </li>
          <li className="nav-item mx-2">
            <Link
              to="/becomedriver"
              className="nav-link text-decoration-none"
            >
              Become a Driver
            </Link>
          </li>
          <Link to="/activeseller" className="nav-link">
            Become a seller
          </Link>
          <li className="nav-item mx-2">
            <a className="nav-link" href="#">
              How it work
            </a>
          </li>
        </ul>
        <div>
          <Link to="/login" className="btn btn-outline-light me-2">
            Sign in
          </Link>
          <Link to="/signup" className="btn btn-success">
            Sign up
          </Link>
        </div>
      </div>
    </nav>
  );
}

export default BuyerNavbar;
