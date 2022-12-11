import {createAsyncThunk} from "@reduxjs/toolkit";
import {addToCart, buyCart, getCart, removeFromCart} from "./cart-service";

export const getCartThunk = createAsyncThunk(
    'userCart',
    async (user) => await getCart(user)
)

export const addToCartThunk = createAsyncThunk(
    'addToCart',
    async (cart) => await addToCart(cart)
)

export const removeFromCartThunk = createAsyncThunk(
    'removeFromCart',
    async (cart) => await removeFromCart(cart)
)

export const buyCartThunk = createAsyncThunk(
    'buyCart',
    async (cart) => await buyCart(cart)
)