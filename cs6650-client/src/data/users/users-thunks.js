import {createAsyncThunk} from "@reduxjs/toolkit";
import {login, logout, signup} from "./user-service";

export const signupThunk = createAsyncThunk(
    'signup',
    async (user) => await signup(user)
)

export const loginThunk = createAsyncThunk(
    'login',
    async (user) => await login(user)
)

export const logoutThunk = createAsyncThunk(
    'logout',
    async (user) => await logout(user)
)