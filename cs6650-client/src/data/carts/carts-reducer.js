import {createSlice} from "@reduxjs/toolkit";
import {addToCartThunk, getCartThunk} from "./carts-thunk";

const booksReducer = createSlice({
        name: 'carts',
        initialState: {
            userCart: []
        },
        reducers:{
        },
        extraReducers: {
            [getCartThunk.fulfilled]: (state, action) => {
                state.userCart = action.payload;
            },
            [addToCartThunk.fulfilled]: (state, action) => {
                state.userCart.push(action.payload);
            }
        }
    }
)

export default booksReducer.reducer;