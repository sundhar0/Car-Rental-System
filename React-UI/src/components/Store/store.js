import { configureStore } from "@reduxjs/toolkit";
import driverAllSlice from './DriverStore/driverAllSlice'
import AllCarSlice from "./CarStore/AllCarSlice"


const store = configureStore({ //store holds the state
    reducer:{
        //assetAllocation - state by reducer        //assetAllocationSlice - the reducer exported in slice and is the reducer (handling the state logic) 
        driverAll: driverAllSlice,
        allCar: AllCarSlice
    }
})

export default store;