import {createSlice} from "@reduxjs/toolkit";
import {addToCartThunk, getCartThunk} from "./carts-thunk";

const booksReducer = createSlice({
        name: 'carts',
        initialState: {
            cartList: []
        },
        reducers:{
            resetCarts (state, action) {
                state.cartList = [];
            }
        },
        extraReducers: {
            [getCartThunk.fulfilled]: (state, action) => {
                state.cartList = action.payload;
            },
            [addToCartThunk.fulfilled]: (state, action) => {
                state.cartList.push(action.payload);
            }
        }
    }
)

export const {resetCarts} = booksReducer.actions;
export default booksReducer.reducer;