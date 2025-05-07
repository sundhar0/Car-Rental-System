import axios from "axios";
import { setDriverAll } from "../driverAllSlice";

const fetchDriverAll = (page = 0, size = 8) => async (dispatch) => {
    let token = localStorage.getItem("token");
  
    let headers = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
  
    try {
      const response = await axios.get(
        `http://localhost:8080/api/driver/getAll?page=${page}&size=${size}`,
        headers
      );
  
  
      dispatch(setDriverAll({ driverAll: response.data.list }));
    } catch (error) {
      console.log("Error in driver fetch action: ", error);
    }
  };
  
  export default fetchDriverAll;
  