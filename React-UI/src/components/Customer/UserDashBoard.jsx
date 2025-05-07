import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

const UserDashboard = () => {
  const [activeTab, setActiveTab] = useState("rentals");
  const [customer, setCustomer] = useState({});
  const fetchCustomer=()=>{
    try{
      const response=axios.get(`http://localhost:8081/api/userLogin/userDetails`,{
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
  const deleteCar = async (complaintId) => {
          try {
              await axios.delete(`http://localhost:8081/api/complaints/${complaintId}`);
              setComplaints(prev => prev.filter(c => c.complaintId !== complaintId));
          } catch (err) {
              console.log(err);
          }
      };
    const updateCar = async (e, complaintId) => {
            e.preventDefault();
            try {
                const { responseText, status } = updates[complaintId] || {};
                const response = await axios.put(`http://localhost:8081/api/complaints/${complaintId}`, {
                    reponse: responseText,
                    status: status
                });
    
                const updatedComplaints = complaints.map(complaint =>
                    complaint.complaintId === complaintId
                        ? { ...complaint, reponse: responseText, status, updatedAt: new Date().toISOString() }
                        : complaint
                );
                setComplaints(updatedComplaints);
            } catch (err) {
                console.log(err);
            }
        };
        const fetchCars = async () => {
            try {
              const response = await axios.get(
                `http://localhost:8080/api/car/getById/${id}`
              );
              setCar(response.data);
            } catch (error) {
              console.error("Error fetching cars:", error);
            }
          };
        
          useEffect(() => {
            fetchCars();
          }, []);


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
