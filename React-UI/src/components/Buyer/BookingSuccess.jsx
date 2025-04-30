import React from "react";
import LandingPage from "../Landing/LandingPage";
import { useNavigate } from "react-router";

const BookingSuccess = () => {
    const navigate=useNavigate();
    const landingPage=()=>{
        navigate("/")
    }
  return (
    <div className="min-h-screen bg-[#f8f9fa] flex items-center justify-center px-4">
      <div className="bg-white max-w-lg w-full rounded-2xl shadow-md p-8 text-center">
        
        {/* Success Icon */}
        {/* <div className="flex justify-center mb-4">
          <div className="bg-green-100 p-3 rounded-full">
            <svg
              className="w-6 h-6 text-green-600"
              fill="none"
              stroke="currentColor"
              strokeWidth="2"
              viewBox="0 0 24 24"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M5 13l4 4L19 7"
              />
            </svg>
          </div>
        </div> */}

        {/* Title */}
        <h2 className="text-xl font-semibold mb-2">Booking Successful!</h2>

        {/* Subtitle */}
        <p className="text-gray-600 mb-6">
          Your booking has been completed successfully. The ownership transfer process will begin shortly.
        </p>

        {/* Transaction Box
        <div className="bg-[#f1f3f5] p-4 rounded-md mb-6 text-sm text-left">
          <p className="font-medium text-center mb-2">Transaction Details</p>
          <p className="text-center">Transaction ID: <span className="font-medium">#TRX123456</span></p>
          <p className="text-center">Date: February 20, 2024</p>
        </div> */}

        {/* Download Button */}
        <button className="bg-blue-600 hover:bg-blue-700 text-black font-medium px-5 py-2 rounded w-full" onClick={()=>landingPage()}>
          Go to Home
        </button>
      </div>
    </div>
  );
};

export default BookingSuccess;
