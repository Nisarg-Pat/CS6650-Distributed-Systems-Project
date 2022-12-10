import {createSlice} from "@reduxjs/toolkit";
import {loginThunk, signupThunk, logoutThunk} from "./users-thunks";

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
        },
        [logoutThunk.fulfilled]: (state, action) => {
            state.currentUser = null
        },
    }
})

export default usersReducer.reducer