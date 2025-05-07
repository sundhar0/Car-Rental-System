import { createSlice } from "@reduxjs/toolkit"; 

//This initializes a new slice named assetAllocationSlice
const AllCarSlice = createSlice({ //defines state, actions, and reducers
   //Specifies the name of this slice.
    name:"allCar",
    initialState:{
        allCar:[] //defines  the default value of the state for this slice
    },
    reducers:{ // the reducers — pure functions to update the state
        //Reducers listen for dispatched actions and apply changes to the state accordingly
        //Defines a reducer function named setAssetAllocation
        setCarAll(state, action){ //action creator function
          state.allCar = action.payload.allCar//here the reducer update the state with the dispatched data from action
        }
    }
})

//We are exporting setAssetAllocation here, 
//to enable dispatching that specific action from anywhere in the app
export const {setCarAll} = AllCarSlice.actions; 
export default AllCarSlice.reducer;

// three param - name, intialState, reducers