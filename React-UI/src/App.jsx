import { Route, Routes } from "react-router-dom";
import DriverList from "./components/Driver/DriverList";
import DriverBook from "./components/Driver/DriverBook";
import BecomeADriver from "./components/Driver/BecomeADriver";
import LandingPage from "./components/Landing/LandingPage";
import BuyerPage from "./components/Buyer/BuyerDashboard";
import CarOverview from "./components/Buyer/singleCar";
import SellCarVerification from "./components/Cars/uploadingCars";
import LoginPage from "./components/Auth/Login";
import DriverApprovels from "./components/Manager/DriverApprovels";
import DriverDashBoard from "./components/Driver/DriverDashBoard";
import "./index.css";
import SignupPage from "./components/Auth/signup";
import ScheduleTestDrive from "./components/Buyer/ScheduleTestDrive";
import BookingSuccess from "./components/Buyer/BookingSuccess";
import AddingSuccess from "./components/Cars/SuccessPage";
import DriverListManager from "./components/Manager/DriverListForManager";
import CustomerDashboard from "./components/Customer/CustomerDashboard";
import DriverRides from "./components/Driver/DriverRides";
import UserDashboard from "./components/Customer/User";
import { useDispatch } from "react-redux";
import { useEffect } from "react";
import fetchAllCar from "./components/Store/CarStore/actions/AllCarActions";
import fetchDriverAll from "./components/Store/DriverStore/action/driverAllActions";
import ComplaintList from "./components/Manager/ComplaintList";

function App() {
  const dispatch = useDispatch()
  useEffect(() => {
    dispatch(fetchDriverAll())
    dispatch(fetchAllCar())
  }, [])
  return (
    <Routes>
      <Route path="" element={<LoginPage />} />
      <Route path="driverlistmanager" element={<DriverListManager />} />
      <Route path="landingpage" element={<LandingPage />} />
      <Route path="/driverlistformanager" element={<DriverListManager />} />
      <Route
        path="driverbook/:name/:rating/:shortDescription/:perDayCharge/:driverId"
        element={<DriverBook />}
      />
      <Route path="customerdashboard" element={<CustomerDashboard/>} />
      <Route path="driverrides" element={<DriverRides/>}/>
      <Route path="becomedriver" element={<BecomeADriver />} />
      <Route path="driverApproval" element={<DriverApprovels />} />
      <Route path="driverdashboard" element={<DriverDashBoard />} />
      <Route path="driverlist" element={<DriverList />} />
      <Route path="becomeaSeller" element={<SellCarVerification />} />
      <Route path="login" element={<LoginPage />} />
      <Route path="signup" element={<SignupPage />} />
      <Route path="BuyerDashboard" element={<BuyerPage />} />
      <Route path="singleCar/:id" element={<CarOverview />} />
      <Route path="testDrive/:id" element={<ScheduleTestDrive />} />
      <Route path="booking" element={<BookingSuccess />} />
      <Route path="CarAdded" element={<AddingSuccess />} />
      <Route path="becomedriver" element={<BecomeADriver />} />
      <Route path="complaintlist" element={<ComplaintList/>}/>
      <Route path="userdashboard" element={<UserDashboard/>}/>
    </Routes>
  );
}

export default App;
