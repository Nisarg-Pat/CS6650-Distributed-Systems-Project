import {createAsyncThunk} from "@reduxjs/toolkit";
import {login, signup} from "./user-service";

export const signupThunk = createAsyncThunk(
    'signup',
    async (user) => await signup(user)
)

export const loginThunk = createAsyncThunk(
    'login',
    async (user) => await login(user)
)