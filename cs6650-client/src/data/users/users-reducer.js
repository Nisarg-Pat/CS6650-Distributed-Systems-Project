import {createSlice} from "@reduxjs/toolkit";
import {loginThunk, signupThunk, logoutThunk} from "./users-thunks";

const usersReducer = createSlice({
    name: 'users',
    initialState: {
        currentUser: null,
        error: null
    },
    reducers:{
        setError (state, action) {
            state.error = action.payload;
        }
    },
    extraReducers: {
        [signupThunk.fulfilled]: (state, action) => {
            state.currentUser = action.payload
            state.error = null;
        },
        [signupThunk.rejected]: (state, action) => {
            if(action.error.message === "Request failed with status code 409") {
                state.error = "Username already exists!";
            } else {
                state.error = "Invalid request, please try again!";
            }
        },
        [loginThunk.fulfilled]: (state, action) => {
            state.currentUser = action.payload
            state.error = null;
        },
        [loginThunk.rejected]: (state, action) => {
            if(action.error.message === "Request failed with status code 401") {
                state.error = "Invalid username and password!!";
            } else {
                state.error = "Invalid request, please try again!";
            }
        },
        [logoutThunk.fulfilled]: (state, action) => {
            state.currentUser = null
            state.error = null;
        },
    }
})

export const {setError} = usersReducer.actions;
export default usersReducer.reducer;