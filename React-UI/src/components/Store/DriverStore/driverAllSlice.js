import { createSlice } from "@reduxjs/toolkit"; 

//This initializes a new slice named assetAllocationSlice
const driverAllSlice = createSlice({ //defines state, actions, and reducers
   //Specifies the name of this slice.
    name:"driverAll",
    initialState:{
        driverAll:[] //defines  the default value of the state for this slice
    },
    reducers:{ // the reducers — pure functions to update the state
        //Reducers listen for dispatched actions and apply changes to the state accordingly
        //Defines a reducer function named setAssetAllocation
        setDriverAll(state, action){ //action creator function
          state.driverAll =action.payload.driverAll//here the reducer update the state with the dispatched data from action
        }
    }
})

//We are exporting setAssetAllocation here, 
//to enable dispatching that specific action from anywhere in the app
export const {setDriverAll} = driverAllSlice.actions; 
export default driverAllSlice.reducer;

// three param - name, intialState, reducers