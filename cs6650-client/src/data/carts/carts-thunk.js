import {createAsyncThunk} from "@reduxjs/toolkit";
import {addToCart, buyCart, getCart} from "./cart-service";

export const getCartThunk = createAsyncThunk(
    'userCart',
    async (user) => await getCart(user)
)

export const addToCartThunk = createAsyncThunk(
    'addToCart',
    async (cart) => await addToCart(cart)
)

export const buyCartThunk = createAsyncThunk(
    'buyCart',
    async (cart) => await buyCart(cart)
)