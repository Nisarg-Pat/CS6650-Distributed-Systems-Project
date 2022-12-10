import {createSlice} from "@reduxjs/toolkit";
import {
    addBookThunk,
    bookListThunk,
    deleteBookThunk,
    searchBookThunk,
    sellBookThunk,
    shelfBookThunk
} from "./books-thunk";

const booksReducer = createSlice({
        name: 'books',
        initialState: {
            shelfList: [],
            sellList: [],
            searchList: []
        },
        reducers:{
            initSearch(state, action) {
                state.searchList = []
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
            }
        }
    }
)

export const {initSearch} = booksReducer.actions;
export default booksReducer.reducer;