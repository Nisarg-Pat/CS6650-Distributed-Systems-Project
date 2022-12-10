import {createSlice} from "@reduxjs/toolkit";
import {addBookThunk, bookListThunk, searchBookThunk, sellBookThunk} from "./books-thunk";

const booksReducer = createSlice({
        name: 'books',
        initialState: {
            shelfList: [],
            sellList: [],
            searchList: []
        },
        reducers:{
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
            [searchBookThunk.fulfilled]: (state, action) => {
                state.searchList = action.payload;
            }
        }
    }
)

export default booksReducer.reducer;