function BuyerNavbar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-4 py-3">
          <a className="navbar-brand text-success fw-bold" href="#">RENTCARS</a>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse justify-content-between" id="navbarNav">
            <ul className="navbar-nav">
              <li className="nav-item mx-2"><a className="nav-link" href="#">Become a renter</a></li>
              <li className="nav-item mx-2"><a className="nav-link" href="#">Become a driver</a></li>
              <li className="nav-item mx-2"><a className="nav-link" href="#">Become a seller</a></li>
              <li className="nav-item mx-2"><a className="nav-link" href="#">How it work</a></li>
            </ul>
            <div>
              <a className="btn btn-outline-light me-2" href="#">Sign in</a>
              <a className="btn btn-success" href="#">Sign up</a>
            </div>
          </div>
        </nav>
      );
}

export default BuyerNavbar;
