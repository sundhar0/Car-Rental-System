import { Route, Routes } from "react-router-dom";
import DriverList from "./components/Driver/DriverList";
import DriverBook from "./components/Driver/DriverBook";
import BecomeADriver from "./components/Driver/BecomeADriver";
<<<<<<< HEAD:Car-Rental-System/src/App.jsx
import LandingPage from "./components/Landing/LandingPage";
import BuyerPage from "./components/Buyer/BuyerDashboard";
import CarOverview from "./components/Buyer/singleCar";
import SellCarVerification from "./components/Cars/uploadingCars";
import LoginPage from "./components/Auth/Login";
import SignupPage from "./components/Auth/signup";
import ScheduleTestDrive from "./components/Buyer/ScheduleTestDrive";
import BookingSuccess from "./components/Buyer/BookingSuccess";
import AddingSuccess from "./components/Cars/SuccessPage";
=======
import DriverListManager from "./components/Manager/DriverListForManager";
import LoginPage from "./components/Auth/Login";
>>>>>>> 16c5b59cbddf4b1391518943fca8e41da9cad4b4:React-UI/src/App.jsx

function App() {
  return (
    <Routes>
<<<<<<< HEAD:Car-Rental-System/src/App.jsx
      <Route path="" element={<LandingPage />} />
=======
      <Route path="" element={<LoginPage/>} />
      <Route path="/driverlistformanager" element={<DriverListManager />} />
>>>>>>> 16c5b59cbddf4b1391518943fca8e41da9cad4b4:React-UI/src/App.jsx
      <Route
        path="driverbook/:name/:rating/:shortDescription/:perDayCharge"
        element={<DriverBook />}
      />
      <Route path="becomeaSeller" element={<SellCarVerification/>}/>
      <Route path="login" element={<LoginPage/>}/>
      <Route path="signup" element={<SignupPage/>}/>
      <Route path="BuyerDashboard" element={<BuyerPage/>}/>
      <Route path="singleCar" element={<CarOverview/>}/>
      <Route path="testDrive" element={<ScheduleTestDrive/>}/>
      <Route path="booking" element={<BookingSuccess/>}/>
      <Route path="CarAdded" element={<AddingSuccess/>}/>
      <Route path="becomedriver" element={<BecomeADriver/>}/>
    </Routes>
<<<<<<< HEAD:Car-Rental-System/src/App.jsx
=======
    // <DriverListManager/>
    
>>>>>>> 16c5b59cbddf4b1391518943fca8e41da9cad4b4:React-UI/src/App.jsx
  );
}

export default App;
