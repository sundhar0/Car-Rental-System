import axios from "axios";
import { setCarAll } from "../AllCarSlice";

const fetchAllCar = (page = 0, size = 8) => async (dispatch) => {
    let token = localStorage.getItem("token");
  
    let headers = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    
    try {
      const response = await axios.get(
        `http://localhost:8080/api/car/getAll?page=${page}&size=${size}`,
        headers
      );
  
  
      dispatch(setCarAll({ allCar: response.data.list }));
    } catch (error) {
      console.log("Error in car fetch action: ", error);
    }
  };
  
  export default fetchAllCar;
  