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
        
        <h2 className="text-xl font-semibold mb-2">Car Added Successful!</h2>
        <p className="text-gray-600 mb-6">
          Your Car added successfully!!
        </p>
        <button className="bg-blue-600 hover:bg-blue-700 text-black font-medium px-5 py-2 rounded w-full" >
          Go to Dashboard
        </button>
      </div>
    </div>
  );
};

export default AddingSuccess;
