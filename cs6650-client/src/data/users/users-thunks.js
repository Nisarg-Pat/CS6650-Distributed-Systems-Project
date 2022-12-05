import {createAsyncThunk} from "@reduxjs/toolkit";
import {signup} from "./user-service";

export const signupThunk = createAsyncThunk(
    'signup',
    async (user) => await signup(user)
)