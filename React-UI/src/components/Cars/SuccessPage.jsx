import React from "react";
import { useNavigate } from "react-router";

const AddingSuccess = () => {
    const navigate=useNavigate();
    const customerDashBoard=()=>{
        navigate("/customerDashBoard")
    }
  return (
    <div className="min-h-screen bg-[#f8f9fa] flex items-center justify-center px-4">
      <div className="bg-white max-w-lg w-full rounded-2xl shadow-md p-8 text-center">
        
      <h4>Request Accepted!</h4>
      <p className="mb-1">You will be notified within 1-2 business hours</p>
        <button className="bg-blue-600 hover:bg-blue-700 text-black font-medium px-5 py-2 rounded w-full" onClick={()=>{customerDashBoard()}}>
          Go to Dashboard
        </button>
      </div>
    </div>
  );
};

export default AddingSuccess;
