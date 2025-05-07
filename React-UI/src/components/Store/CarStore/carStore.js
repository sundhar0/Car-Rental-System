import { configureStore } from "@reduxjs/toolkit";
import AllCarSlice from "./AllCarSlice"

const store = configureStore({
    reducer:{
        allCar: AllCarSlice
    } 
})

export default store;