import { useEffect, useState } from "react";
import axios from "axios";

function SellingHistory() {
  const [sellingHistory, setsellingHistory] = useState([]);

  const getAllSellingHistory = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/booking/showAll/1`
      );
      setsellingHistory(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getAllSellingHistory();
  }, []);


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
                <th scope="col">Rental ID</th>
                <th scope="col">Customer ID</th>
                <th scope="col">completedDate</th>
              </tr>
            </thead>
            <tbody>
              {testDrive
                .map((d, index) => (
                  <tr key={index}>
                    <th scope="row">{d.id}</th>
                    <td>{d.user.username}</td>
                    <td>{d.bookingDate}</td>
                    <td>{d.time}</td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>
    
    </div>
  );
}

export default SellingHistory;
