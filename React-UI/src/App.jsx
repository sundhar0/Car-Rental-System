import { Route, Routes } from "react-router-dom";
import DriverList from "./components/Driver/DriverList";
import DriverBook from "./components/Driver/DriverBook";
import BecomeADriver from "./components/Driver/BecomeADriver";
import DriverListManager from "./components/Manager/DriverListForManager";
import LoginPage from "./components/Auth/Login";

function App() {
  return (
    <Routes>
      <Route path="" element={<LoginPage/>} />
      <Route path="/driverlistformanager" element={<DriverListManager />} />
      <Route
        path="driverbook/:name/:rating/:shortDescription/:perDayCharge"
        element={<DriverBook />}
      />
      <Route path="becomedriver" element={<BecomeADriver/>}/>
    </Routes>
    // <DriverListManager/>
    
  );
}

export default App;
