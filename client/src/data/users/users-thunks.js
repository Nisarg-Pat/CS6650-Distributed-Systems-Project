import {createAsyncThunk} from "@reduxjs/toolkit";
import {login, logout, signup} from "./user-service";

export const signupThunk = createAsyncThunk(
    'signup',
    async (par) => await signup(par)
)

export const loginThunk = createAsyncThunk(
    'login',
    async (par) => await login(par)
)

export const logoutThunk = createAsyncThunk(
    'logout',
    async (par) => await logout(par)
)