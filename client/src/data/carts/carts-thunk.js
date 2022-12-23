import {createAsyncThunk} from "@reduxjs/toolkit";
import {addToCart, buyCart, getCart, removeFromCart} from "./cart-service";

export const getCartThunk = createAsyncThunk(
    'userCart',
    async (par) => await getCart(par)
)

export const addToCartThunk = createAsyncThunk(
    'addToCart',
    async (par) => await addToCart(par)
)

export const removeFromCartThunk = createAsyncThunk(
    'removeFromCart',
    async (par) => await removeFromCart(par)
)

export const buyCartThunk = createAsyncThunk(
    'buyCart',
    async (par) => await buyCart(par)
)