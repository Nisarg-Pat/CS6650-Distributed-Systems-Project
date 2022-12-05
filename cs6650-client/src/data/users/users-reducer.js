import {createSlice} from "@reduxjs/toolkit";
import {loginThunk, signupThunk} from "./users-thunks";

const usersReducer = createSlice({
    name: 'users',
    initialState: {
        currentUser: null
    },
    reducers:{
        logout: (state) => {
            state.currentUser = null;
        }
    },
    extraReducers: {
        [signupThunk.fulfilled]: (state, action) => {
            state.currentUser = action.payload
        },
        [loginThunk.fulfilled]: (state, action) => {
            state.currentUser = action.payload
        }
    }
})

export default usersReducer.reducer