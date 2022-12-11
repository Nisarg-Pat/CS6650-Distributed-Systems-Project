import {createSlice} from "@reduxjs/toolkit";
import {
    addBookThunk,
    bookListThunk,
    deleteBookThunk,
    searchBookThunk,
    sellBookThunk,
    shelfBookThunk
} from "./books-thunk";
import {addToCartThunk, buyCartThunk, getCartThunk, removeFromCartThunk} from "../carts/carts-thunk";

const booksReducer = createSlice({
        name: 'books',
        initialState: {
            shelfList: [],
            sellList: [],
            searchList: [],
            cartList: []
        },
        reducers:{
            resetBooks (state, action) {
                state.shelfList = [];
                state.sellList = [];
                state.searchList = [];
                state.cartList = [];
            }
        },
        extraReducers: {
            [addBookThunk.fulfilled]: (state, action) => {
                state.shelfList.push(action.payload)
            },
            [bookListThunk.fulfilled]: (state, action) => {
                state.shelfList = action.payload.filter((book) => book.status === "shelf" || book.status === "bought")
                state.sellList = action.payload.filter((book) => book.status === "sell" || book.status === "sold")
            },
            [sellBookThunk.fulfilled]: (state, action) => {
                state.shelfList = state.shelfList.filter((book) => book.id !== action.payload.id)
                state.sellList.push(action.payload)
            },
            [shelfBookThunk.fulfilled]: (state, action) => {
                state.sellList = state.sellList.filter((book) => book.id !== action.payload.id)
                state.shelfList.push(action.payload)
            },
            [deleteBookThunk.fulfilled]: (state, action) => {
                state.shelfList = state.shelfList.filter((book) => book.id !== action.payload)
            },
            [searchBookThunk.fulfilled]: (state, action) => {
                state.searchList = action.payload;
            },
            [getCartThunk.fulfilled]: (state, action) => {
                state.cartList = action.payload;
            },
            [addToCartThunk.fulfilled]: (state, action) => {
                state.cartList.push(action.payload);
            },
            [buyCartThunk.fulfilled]: (state, action) => {
                state.cartList = [];
                state.shelfList.concat(action.payload)
            },
            [removeFromCartThunk.fulfilled]: (state, action) => {
                console.log(action.payload);
                state.cartList = state.cartList.filter((cart) => cart.id !== action.payload)
            }
        }
    }
)

export const {resetBooks} = booksReducer.actions;
export default booksReducer.reducer;