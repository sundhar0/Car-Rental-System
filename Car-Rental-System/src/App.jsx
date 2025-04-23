import { Route, Routes } from "react-router-dom";
import DriverList from "./components/Driver/DriverList";
import DriverBook from "./components/Driver/DriverBook";
import BecomeADriver from "./components/Driver/BecomeADriver";

function App() {
  return (
    <Routes>
      <Route path="" element={<DriverList />} />
      <Route
        path="driverbook/:name/:rating/:shortDescription/:perDayCharge"
        element={<DriverBook />}
      />
      <Route path="becomedriver" element={<BecomeADriver/>}/>
    </Routes>
    
  );
}

export default App;
