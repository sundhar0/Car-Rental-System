import { useEffect, useState } from "react";
import axios from "axios";

function SellHistory() {
  const [sellHistory, setSellHistory] = useState([]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(8);
  const [totalPages, setTotalPages] = useState(0);
  const [selectedDocs, setSelectedDocs] = useState([]);

  const getSellHistory = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/car/getAllCarsById/2?page=${page}&size=${size}`
      );
      setSellHistory(response.data.list);
      setTotalPages(response.data.totalPages);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getSellHistory();
  }, [page]);


  const deleteCar = async (carId) => {
    try {
    //   await axios.delete(`http://localhost:8080/api/car/delete/${carId}`);
      setSellHistory((prev) => prev.filter((s) => s.id !== carId));
    } catch (err) {
      console.log(err);
    }
  };


  return (
    <div className="" style={{ minHeight: "100vh" }}>

      <div className="container">   
        <div className="d-flex justify-content-between align-items-center ms-auto m-5">
          <h1>Rental History</h1>
          <div className="input-group w-auto mb-3">
            <input
              type="text"
              className="form-control form-control-sm"
              placeholder="Search"
              aria-label="Search"
              aria-describedby="button"
            />
            <button className="btn fw-bold text-white" type="button" id="button" style={{ backgroundColor: "#00B86B" }}>
              <i className="bi bi-search"></i>
            </button>
          </div>
        </div>

        <div className="">
          <table className="table text-center">
            <thead>
              <tr>
                <th scope="col">Car ID</th>
                <th scope="col">Car Make</th>
                <th scope="col">Car color</th>
                <th scope="col">Car status</th>
                <th scope="col">Car description</th>

              </tr>
            </thead>
            <tbody>
              {sellHistory
                .map((d, index) => (
                  <tr key={index}>
                    <th scope="row">{d.id}</th>
                    <td>{d.carMake}</td>
                    <td>{d.carModel}</td>
                    <td>{d.carColor}</td>
                    <td>{d.status}</td>
                    <td>{d.desription}</td>
                    <td>
                      <div className="d-flex justify-content-center gap-1">
                        
                        <button className="btn btn-danger" onClick={() => deleteCar(d.id)}>
                          Delete
                        </button>
                      </div>
                      
                    </td>
                    
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>

      <div className="d-flex justify-content-center mt-5">
        <nav aria-label="Page navigation example">
          <ul className="pagination">
            <li className="page-item">
              <a
                className="page-link"
                href="#"
                onClick={() => {
                  page === 0 ? setPage(0) : setPage(page - 1);
                }}
                style={{ color: "#00B86B" }}
              >
                Previous
              </a>
            </li>
            <li className="page-item">
              <a
                className="page-link"
                href="#"
                onClick={() => {
                  page === totalPages - 1 ? setPage(page) : setPage(page + 1);
                }}
                style={{ color: "#00B86B" }}
              >
                Next
              </a>
            </li>
          </ul>
        </nav>
      </div>

    </div>
  );
}

export default SellHistory;
