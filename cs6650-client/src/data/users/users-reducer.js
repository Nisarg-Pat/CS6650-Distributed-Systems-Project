import {createSlice} from "@reduxjs/toolkit";
import {signupThunk} from "./users-thunks";

const usersReducer = createSlice({
    name: 'users',
    initialState: {
        currentUser: null
    },
    reducers:{

    },
    extraReducers: {
        [signupThunk.fulfilled]: (state, action) => {
            state.currentUser = action.payload

        }
    }
})

export default usersReducer.reducer